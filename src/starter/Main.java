package starter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Scanner;

public class Main extends JPanel {

    final int BF_WIDTH = 576;
    final int BF_HEIGHT = 576;

    int y = 6;
    int x = 7;


    int score = 0;
    int countSteps = 50;
    String gameStatus = "Play Game :)";

// B - Brick, GG - Gold, P - Player, G - Ground, E - Exit
    String[][] data = {
            {"B", "G", "B", "B", "B", "B", "B", "G", "B", "B", "G"},
            {"GG", "G", "G", "GG", "GG", "GG", "E", "B", "B", "B", "G"},
            {"G", "G", "B", "B", "G", "B", "B", "G", "B", "B", "G"},
            {"G", "G", "B", "B", "M", "B", "B", "M", "B", "B", "G"},
            {"GG", "G", "G", "B", "B", "B", "B", "G", "B", "B", "G"},
            {"G", "M", "B", "B", "B", "B", "B", "G", "B", "B", "G"},
            {"G", "B", "G", "G", "G", "G", "G", "P", "B", "B", "G"},
            {"G", "G", "G", "B", "B", "GG", "G", "GG", "B", "B", "G"},
            {"M", "B", "B", "B", "B", "B", "B", "B", "B", "B", "G"},
            {"M", "B", "G", "G", "G", "G", "G", "G", "B", "B", "G"},
            {"G", "G", "G", "B", "B", "G", "G", "G", "B", "B", "G"},
            {"G", "M", "B", "B", "B", "B", "B", "G", "B", "B", "G"}
    };


    void moveUp() throws Exception {

        data[y][x] = "G";
        y--;
        data[y][x] = "P";
        countSteps--;
        drawTable();
        Thread.sleep(1000);

    }

    void move(int direction) throws Exception {

   data[y][x] = "G";
        switch (direction) {
            case 1:
               y--;
                break;
            case 2:
                y++;
                break;
            case 3:
                x--;
                break;
            case 4:
                x++;
                break;
        }
        data[y][x] = "P";
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
                    while (y < 11) {
                        if (data[y+1][x].equals("G") | data[y+1][x].equals("GG") | data[y+1][x].equals("M")) {
                        data[y][x] = "G";
                        y++;
                        data[y][x] = "P";
                        data[y - 1][x] = "G";
                        countSteps--;
                        drawTable();
                        Thread.sleep(600);
                        moveRandom();}
                        else moveRandom();
                    }
                case 2:
                    while (x < 9) {
                        if (data[y][x+1].equals("G") | data[y][x+1].equals("GG") | data[y][x+1].equals("M")) {
                        data[y][x] = "G";
                        x++;
                        data[y][x] = "P";
                        data[y][x - 1] = "G";
                        countSteps--;
                        drawTable();
                        Thread.sleep(600);
                        moveRandom();}
                        else moveRandom();
                    }

                case 3:
                    while (y > 0) {
                        if (data[y-1][x].equals("G") | data[y-1][x].equals("GG") | data[y-1][x].equals("M")) {
                        data[y][x] = "G";
                        y--;
                        data[y][x] = "P";
                        data[y + 1][x] = "G";
                        countSteps--;
                        drawTable();
                        Thread.sleep(600);
                        moveRandom();}
                        else moveRandom();
                    }
                case 4:
                    while (x > 0) {
                        if (data[y][x-1].equals("G") | data[y][x-1].equals("GG") | data[y][x-1].equals("M")) {
                        data[y][x] = "G";
                        x--;
                        data[y][x] = "P";
                        data[y][x + 1] = "G";
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
                while (y>0) {
                   if (data[y-1][x].equals("G") | data[y-1][x].equals("GG") | data[y-1][x].equals("M")) {     // Игрок убивает монстров и собирает золото
                   //         if (data[y-1][x].equals("G")) {                                                   // Игрок ходит только, когда земля
                        move(direction);
                    }
                    else break;
                }
                gameStatus = "Game Over!!!";
                drawTable();
                break;
            case 2:
                while (y<11) {
                    if (data[y+1][x].equals("G") | data[y+1][x].equals("GG")| data[y+1][x].equals("M")) {    // Игрок убивает монстров и собирает золото
                    //        if (data[y+1][x].equals("G")) {                                                        // Игрок ходит только, когда земля
                        move(direction);
                    }
                    else break;
                }
                gameStatus = "Game Over!!!";
                drawTable();
                break;
            case 3:
                while (x>0) {
                    if (data[y][x-1].equals("G") | data[y][x-1].equals("GG") | data[y][x-1].equals("M") ) {   // Игрок убивает монстров и собирает золото
                      //        if (data[y][x-1].equals("G")) {                                                         // Игрок ходит только, когда земля
                        move(direction);
                    }
                    else break;
                }
                gameStatus = "Game Over!!!";
                drawTable();
                break;
            case 4:
                while (x<10) {
                    if (data[y][x+1].equals("G") | data[y][x+1].equals("GG") | data[y][x+1].equals("M") ) {      // Игрок убивает монстров и собирает золото
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


        table.setModel(new DefaultTableModel(data, column));

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
