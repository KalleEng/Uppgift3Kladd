import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class GUI extends JFrame {
    private final JPanel gridPanel = new JPanel(new GridLayout(4, 4));
    private JPanel bottomPanel = new JPanel();
    private final int[][] squareGrid = new int[4][4];
    private final JButton[][] buttonsForGrid = new JButton[4][4];
    private JButton newGameButton = new JButton("New Game");
    private JButton testWinButton = new JButton("Test win");
    ArrayList<Integer> numberList = new ArrayList<>();

    public GUI() {
        //Målar upp startbrädet
        randomizerGrid();
        initiateGrid();

        //Kopplar newGameButton till random-funktion
        newGameButton.addActionListener(e -> {
            randomizerGrid();
            updateGrid();
        });

        bottomPanel.add(newGameButton);
        bottomPanel.add(testWinButton);
        add(bottomPanel, BorderLayout.SOUTH);

        add(gridPanel);

        testWinButton.addActionListener(e -> {
            testWin();
            updateGrid();
        });

        setSize(600, 600);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    //Itererar över brädets grid och lägger in knappar på dess platser
    private void initiateGrid() {
        for (int i = 0; i < squareGrid.length; i++) {
            for (int j = 0; j < squareGrid[i].length; j++) {
                JButton newButton = new JButton();
                newButton.setFont(new Font("Arial", Font.BOLD, 18));
                int buttonPosRow = i;
                int buttonPosCol = j;
                newButton.addActionListener(e -> swapButtons(buttonPosRow, buttonPosCol));
                buttonsForGrid[i][j] = newButton;
                gridPanel.add(newButton);
            }
        }
        updateGrid();
    }

    //Byter plats på klickad knapp om rutan bredvid är tom
    private void swapButtons(int buttonPosRow, int buttonPosCol) {
        Point emptyPosition = findEmptyPosition();
        if (emptyPosition != null) {
            if (isAdjacent(buttonPosRow, buttonPosCol, emptyPosition)) {
                //tar position x+y från emptyButton och byter plats på klickad knapp
                squareGrid[emptyPosition.x][emptyPosition.y] = squareGrid[buttonPosRow][buttonPosCol];
                //Sätter värdet på den klickade knappen till 0, vilket är värdet av emptyButton
                squareGrid[buttonPosRow][buttonPosCol] = 0;
                updateGrid();
                //Kollar efter varje klickad knapp om spelet är vunnet
                if (checkWin()) {
                    JOptionPane.showMessageDialog(null, "You won!");
                }
            }
        }
    }


    private boolean checkWin() {

        int count = 1;
        for (int i = 0; i < squareGrid.length; i++) {
            for (int j = 0; j < squareGrid[i].length; j++) {
                if (squareGrid[i][j] != count && count != 16) {
                    return false;
                }
                count++;
            }
        }

        return squareGrid[3][3] == 0;
    }

    private boolean isAdjacent(int buttonPosRow, int buttonPosCol, Point emptyPosition) {
        //Kollar ifall den klickade knappen är på samma rad och om kolumnen ligger bredvid
        boolean sameRow = buttonPosRow == emptyPosition.x && (buttonPosCol == emptyPosition.y - 1 || buttonPosCol == emptyPosition.y + 1);

        //Kollar ifall den klickade knappen är på samma kolumn och om raden ligger bredvid
        boolean sameCol = buttonPosCol == emptyPosition.y && (buttonPosRow == emptyPosition.x - 1 || buttonPosRow == emptyPosition.x + 1);

        return sameRow || sameCol;
    }

    private Point findEmptyPosition() {
        //Itererar över gridet och tar ut positionen på knappen med värdet 0 (emptyButton)
        for (int i = 0; i < squareGrid.length; i++) {
            for (int j = 0; j < squareGrid[i].length; j++) {
                if (squareGrid[i][j] == 0) {
                    return new Point(i, j);
                }

            }

        }
        return null;
    }

    //Bestämmer var knappar med siffror ska sättas in och var emptyButton ska sättas in.
    private void updateGrid() {
        for (int i = 0; i < squareGrid.length; i++) {
            for (int j = 0; j < squareGrid[i].length; j++) {
                buttonsForGrid[i][j].setBackground(null);
                buttonsForGrid[i][j].setOpaque(true);
                if (squareGrid[i][j] != 0) {
                    buttonsForGrid[i][j].setText(String.valueOf(squareGrid[i][j]));
                } else {
                    buttonsForGrid[i][j].setText("");
                    if (checkWin()){
                        buttonsForGrid[i][j].setBackground(new Color(51, 255, 51));
                    }else {
                        buttonsForGrid[i][j].setBackground(new Color(255, 51, 51));
                    }
                }
            }
        }
    }


    private void randomizerGrid() {
        numberList.clear();
        for (int i = 1; i <= 15; i++) {
            numberList.add(i);
        }
        numberList.add(0);

        Collections.shuffle(numberList);

        int index = 0;
        for (int i = 0; i < squareGrid.length; i++) {
            for (int j = 0; j < squareGrid[i].length; j++) {
                squareGrid[i][j] = numberList.get(index++);
            }
        }
    }


    private void testWin() {
        numberList.clear();
        for (int i = 1; i <= 15; i++) {
            numberList.add(i);
        }
        numberList.add(0);

        int index = 0;
        for (int i = 0; i < squareGrid.length; i++) {
            for (int j = 0; j < squareGrid[i].length; j++) {
                squareGrid[i][j] = numberList.get(index++);
            }
        }

    }
}
