import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
//Jakobs branch
public class GUI extends JFrame {
    private JPanel gridPanel = new JPanel(new GridLayout(4,4));
    private JPanel bottomPanel = new JPanel();
    private int[][] squareGrid = new int[4][4];
    private JButton[][] buttonsForGrid = new JButton[4][4];
    private JButton newGameButton = new JButton("New Game");

    public GUI(){
        randomizerGrid();
        initiateGrid();
        updateGrid();
        newGameButton.addActionListener(e ->{
            randomizerGrid();
            updateGrid();
        });

        bottomPanel.add(newGameButton);
        add(bottomPanel, BorderLayout.SOUTH);

        add(gridPanel);

        setSize(600,600);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    private void initiateGrid(){
        for (int i = 0; i < squareGrid.length; i++){
            for (int j = 0; j < squareGrid[i].length; j++){
                JButton button = new JButton();
                buttonsForGrid[i][j] = button;
                gridPanel.add(button);
            }
        }
        updateGrid();
    }
    private void updateGrid(){
        for (int i = 0; i < squareGrid.length; i++){
            for (int j = 0; j < squareGrid[i].length; j++){
                if (squareGrid[i][j] != 0){
                    buttonsForGrid[i][j].setText(String.valueOf(squareGrid[i][j]));
                }
                else{
                    buttonsForGrid[i][j].setText("");
                }
            }
        }
    }


    private void randomizerGrid(){
        ArrayList<Integer> num = new ArrayList<>();
        for (int i = 1; i <= 15; i++){
            num.add(i);
        }
        num.add(0);

        Collections.shuffle(num);

        int index = 0;
        for (int i = 0; i < squareGrid.length; i++){
            for (int j = 0; j < squareGrid[i].length; j++){
                squareGrid[i][j] = num.get(index++);
            }
        }
    }

}
