package starter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Scanner;

public class Main extends JPanel {

    final int BF_WIDTH = 576;
    final int BF_HEIGHT = 576;

    int score = 0;
    int countSteps = 50;
    String gameStatus = "Play Game :)";

    ArrayMap arrayMap = new ArrayMap(6,7);


    void moveUp() throws Exception {

        arrayMap.data[arrayMap.y][arrayMap.x] = "G";
        arrayMap.y--;
        arrayMap.data[arrayMap.y][arrayMap.x] = "P";
        countSteps--;
        drawTable();
        Thread.sleep(1000);

    }

    void move(int direction) throws Exception {

   arrayMap.data[arrayMap.y][arrayMap.x] = "G";
        switch (direction) {
            case 1:
                arrayMap.y--;
                break;
            case 2:
                arrayMap.y++;
                break;
            case 3:
                arrayMap.x--;
                break;
            case 4:
                arrayMap.x++;
                break;
        }
        arrayMap.data[arrayMap.y][arrayMap.x] = "P";
        countSteps--;
        Thread.sleep(800);
   drawTable();
    }


    void moveRandom() throws Exception {       //Игрок просто ходит
        int max = 4;
        int min = 1;
        int direction = (int) (Math.random() * ((max - min) + 1)) + min;

        switch (direction) {
                case 1:
                    while (arrayMap.y < 11) {
                        if (arrayMap.data[arrayMap.y+1][arrayMap.x].equals("G") |
                                arrayMap.data[arrayMap.y+1][arrayMap.x].equals("GG")
                                | arrayMap.data[arrayMap.y+1][arrayMap.x].equals("M")) {
                        arrayMap.data[arrayMap.y][arrayMap.x] = "G";
                            arrayMap.y++;
                        arrayMap.data[arrayMap.y][arrayMap.x] = "P";
                        arrayMap.data[arrayMap.y - 1][arrayMap.x] = "G";
                        countSteps--;
                        drawTable();
                        Thread.sleep(600);
                        moveRandom();}
                        else moveRandom();
                    }
                case 2:
                    while (arrayMap.x < 9) {
                        if (arrayMap.data[arrayMap.y][arrayMap.x+1].equals("G") |
                                arrayMap.data[arrayMap.y][arrayMap.x+1].equals("GG")
                                | arrayMap.data[arrayMap.y][arrayMap.x+1].equals("M")) {
                        arrayMap.data[arrayMap.y][arrayMap.x] = "G";
                            arrayMap.x++;
                        arrayMap.data[arrayMap.y][arrayMap.x] = "P";
                        arrayMap.data[arrayMap.y][arrayMap.x - 1] = "G";
                        countSteps--;
                        drawTable();
                        Thread.sleep(600);
                        moveRandom();}
                        else moveRandom();
                    }

                case 3:
                    while (arrayMap.y > 0) {
                        if (arrayMap.data[arrayMap.y-1][arrayMap.x].equals("G") |
                                arrayMap.data[arrayMap.y-1][arrayMap.x].equals("GG")
                                | arrayMap.data[arrayMap.y-1][arrayMap.x].equals("M")) {
                        arrayMap.data[arrayMap.y][arrayMap.x] = "G";
                            arrayMap.y--;
                            arrayMap.data[arrayMap.y][arrayMap.x] = "P";
                            arrayMap.data[arrayMap.y + 1][arrayMap.x] = "G";
                        countSteps--;
                        drawTable();
                        Thread.sleep(600);
                        moveRandom();}
                        else moveRandom();
                    }
                case 4:
                    while (arrayMap.x > 0) {
                        if (arrayMap.data[arrayMap.y][arrayMap.x-1].equals("G") |
                                arrayMap.data[arrayMap.y][arrayMap.x-1].equals("GG")
                                | arrayMap.data[arrayMap.y][arrayMap.x-1].equals("M")) {
                            arrayMap.data[arrayMap.y][arrayMap.x] = "G";
                            arrayMap.x--;
                            arrayMap.data[arrayMap.y][arrayMap.x] = "P";
                            arrayMap.data[arrayMap.y][arrayMap.x + 1] = "G";
                        countSteps--;
                        drawTable();
                        Thread.sleep(600);
                        moveRandom();}
                        else moveRandom();
                    }
            }
    }

    void runTheGame() throws Exception {

        System.out.println("Введите направление движения: ");
        System.out.println("1 - вверх, 2 - вниз, 3 - влево, 4 - вправо");
        Scanner sc = new Scanner(System.in);
        int direction = sc.nextInt();

        switch (direction) {          // Игрок убивает монстров и собирает золото только в выбранном направлении
            case 1:
                while (arrayMap.y>0) {
                   if (arrayMap.data[arrayMap.y-1][arrayMap.x].equals("G") |
                           arrayMap.data[arrayMap.y-1][arrayMap.x].equals("GG") |
                           arrayMap.data[arrayMap.y-1][arrayMap.x].equals("M")) {     // Игрок убивает монстров и собирает золото
                   //         if (data[y-1][x].equals("G")) {                                                   // Игрок ходит только, когда земля
                        move(direction);
                    }
                    else break;
                }
                gameStatus = "Game Over!!!";
                drawTable();
                break;
            case 2:
                while (arrayMap.y<11) {
                    if (arrayMap.data[arrayMap.y+1][arrayMap.x].equals("G") |
                            arrayMap.data[arrayMap.y+1][arrayMap.x].equals("GG")|
                            arrayMap.data[arrayMap.y+1][arrayMap.x].equals("M")) {    // Игрок убивает монстров и собирает золото
                    //        if (data[y+1][x].equals("G")) {                                                        // Игрок ходит только, когда земля
                        move(direction);
                    }
                    else break;
                }
                gameStatus = "Game Over!!!";
                drawTable();
                break;
            case 3:
                while (arrayMap.x>0) {
                    if (arrayMap.data[arrayMap.y][arrayMap.x-1].equals("G") |
                            arrayMap.data[arrayMap.y][arrayMap.x-1].equals("GG") |
                            arrayMap.data[arrayMap.y][arrayMap.x-1].equals("M") ) {   // Игрок убивает монстров и собирает золото
                      //        if (data[y][x-1].equals("G")) {                                                         // Игрок ходит только, когда земля
                        move(direction);
                    }
                    else break;
                }
                gameStatus = "Game Over!!!";
                drawTable();
                break;
            case 4:
                while (arrayMap.x<10) {
                    if (arrayMap.data[arrayMap.y][arrayMap.x+1].equals("G") |
                            arrayMap.data[arrayMap.y][arrayMap.x+1].equals("GG") |
                            arrayMap.data[arrayMap.y][arrayMap.x+1].equals("M") ) {      // Игрок убивает монстров и собирает золото
                     //     if (data[y][x+1].equals("G")) {                                                             // Игрок ходит только, когда земля
                        move(direction);
                    }
                    else break;
                }
                gameStatus = "Game Over!!!";
                drawTable();
                break;
        }
        System.out.println("Небольшая передышка!");
        Thread.sleep(2000);                       // Задержка перед следующим методом
        gameStatus = "Game Start !!!";
        moveRandom();                                   // Игрок просто ходит
    }



    // Не смотрите код, что написан ниже. Со временем Вы будете понимать этот код.


    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.runTheGame();

    }


    JTable table;
    String[] column = new String[11];
    JLabel labelScore = new JLabel();
    JLabel labelSteps = new JLabel();
    JLabel labelGameStatus = new JLabel();


    public Main() {
        JFrame frame = new JFrame("Goldman");

        table=new JTable();

        table.setTableHeader(null);
        table.setEnabled(false);
        table.setSize(new Dimension(300, 300));
        table.setRowHeight(26);
        table.setRowSelectionAllowed(false);
        table.setShowHorizontalLines(false);
        table.setShowVerticalLines(false);
        table.setUpdateSelectionOnSort(false);
        table.setVerifyInputWhenFocusTarget(false);


        for (int i = 0; i < column.length; i++) {
            column[i] = "";
        }

        drawTable();

        add(table);
        add(labelScore);
        add(labelSteps);
        add(labelGameStatus);
        frame.setMinimumSize(new Dimension(BF_WIDTH,BF_HEIGHT + 22));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.setVisible(true);
    }

    private void score() {
        labelScore.setText("Score: " + score);
    }

    private void countSteps() {
        labelSteps.setText("Count steps: " + countSteps);
    }

    private void gameStatus() {
        labelGameStatus.setText(gameStatus);
    }

    private void drawTable() {


        table.setModel(new DefaultTableModel(arrayMap.data, column));

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(new ImageRenderer_DONT_TOUCH_THIS_FILE());
            TableColumn a = table.getColumnModel().getColumn(i);
            a.setPreferredWidth(26);
        }

        score();
        countSteps();
        gameStatus();

    }

}
