import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class GUI extends JFrame {
    private JPanel gridPanel = new JPanel(new GridLayout(4,4));
    private JPanel bottomPanel = new JPanel();
    private int[][] squareGrid = new int[4][4];
    private JButton[][] buttonsForGrid = new JButton[4][4];
    private JButton newGameButton = new JButton("New Game");
    ArrayList<Integer> num = new ArrayList<>();

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
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    private void initiateGrid(){
        for (int i = 0; i < squareGrid.length; i++){
            for (int j = 0; j < squareGrid[i].length; j++){
                JButton newButton = new JButton();
                int finalI = i;
                int finalJ = j;
                newButton.addActionListener(e-> swapButtons(finalI,finalJ));
                buttonsForGrid[i][j] = newButton;
                gridPanel.add(newButton);
            }
        }
        updateGrid();
    }

    private void swapButtons(int finalI, int finalJ) {
        Point emptyPosition = findEmptyPosition();
        if (emptyPosition != null) {
            if (isAdjacent(finalI,finalJ,emptyPosition)){
                squareGrid[emptyPosition.x][emptyPosition.y] = squareGrid[finalI][finalJ];
                squareGrid[finalI][finalJ] = 0;
                updateGrid();
            }
        }
    }

    private boolean isAdjacent(int finalI, int finalJ, Point emptyPosition) {
        return Math.abs(emptyPosition.x-finalI) + Math.abs(emptyPosition.y-finalJ) ==1;
    }

    private Point findEmptyPosition() {
        for (int i = 0; i < squareGrid.length; i++) {
            for (int j = 0; j < squareGrid[i].length; j++) {
                if(squareGrid[i][j] == 0){
                    return new Point(i,j);
                }
                
            }
            
        }
        return null;
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
