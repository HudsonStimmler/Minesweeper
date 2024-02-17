/**
 * 
 * @version 
 * @author 
 */

import java.util.*;
public class Mines{
    private static String[][] HiddenBoard;
    private static String[][] GameBoard;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static void main(String[] args){
        boolean activated;
        Scanner in0 = new Scanner(System.in);
        System.out.println("Play minesweeper? (y/n): ");
        String temp1 = in0.next();
        if(temp1.equals("y")){
            activated = true;
        }
        while(activated=true){
            HiddenBoard = new String [9][9];
            for(int r = 0; r < 9; r++){
                for(int c = 0; c < 9; c++){
                    HiddenBoard[r][c] = "x";
                }
            }
            //builds the Actual Game Board
            
            boolean a = true;
            int count = 0;
            while(a){
                count = 0;
                for(int r = 0; r < 9; r++){
                    for(int c = 0; c < 9; c++){
                        HiddenBoard[r][c] = "x";
                    }
                }
                for(int r = 0; r < 9; r++){
                    for(int c = 0; c < 9; c++){
                        if(Math.random()<0.12345679012){
                            HiddenBoard[r][c] = "*";
                            count ++;
                        }
                    }
                }
                if (count == 10){
                    a = false;
                }
            }
            //sets the mines onto the Actual Game Board
    
            GameBoard = new String [9][9];
            for(int r = 0; r < 9; r++){
                for(int c = 0; c < 9; c++){
                    GameBoard[r][c] = "x";
                }
            }
            //builds the Visible Board with Xs on every Square
            
            System.out.println("Preliminary Info:");
            printSquare(HiddenBoard);
            CalculateSquare(HiddenBoard);
            printSquare(HiddenBoard);
            //completes number calculations with on the actual board and prints it
            System.out.println();
    
            System.out.println("How To Play:");
            System.out.println("Begin by picking an interior square (one not lying along an edge). The numbers on the board represent how many bombs are adjacent to a square. Continue picking squares without hitting bombs and win. If not it's game over! To unflag squares simply enter the square again without selecting to flag the square.");
            System.out.println();
    
    
            System.out.println("And The Game Begins...");
            printSquare(GameBoard);
            
            int turn = 0;
            boolean playing = true;
            while(playing){
                if(isVictory()){
                    break;
                }
                System.out.println("Guess #"+(turn+1)+":");
                Scanner in2 = new Scanner(System.in);
                System.out.println("Column(1 to 9): ");
                int co = in2.nextInt();
                int column = co;
    
                Scanner in = new Scanner(System.in);
                System.out.println("Row (1 to 9): ");
                int ro = in.nextInt();
                int row = ro;
    
                Scanner in3 = new Scanner(System.in);
                System.out.println("Flag (y/n): ");
                String flag = in3.next();
    
                if(row>9 || row<1||column>9||column<1){
                    System.out.println("Invalid guess. Please guess again!");
                }
                else if(turn==0 && flag.equals("y")){
                    System.out.println("Cannot place a flag on opening turn.");
                }
                else if(flag.equals("y")){
                    changeSquare(row-1, column-1);
                    printSquare(GameBoard);
                    turn++;
                }
                else if(!(HiddenBoard[row-1][column-1].equals("*"))){
                    revealSquare(row-1, column-1, turn);
                    printSquare(GameBoard);
                    turn++;
                }
                else{
                    playing = false;
                }
            }
            if(isVictory()){
                System.out.println("Congratulations! You won!");
            }
            else{
                System.out.println("Game Over. Thanks for playing!");
            }
            Scanner in5 = new Scanner(System.in);
            System.out.println("Play again? (y/n): ");
            String temp2 = in5.next();
            if(!(temp2.equals("y"))){
                break;
            }
        }
        
    }

    public static void CalculateSquare(String[][] a){
        for(int r = 1; r < 8; r++){
            for(int c = 1; c < 8; c++){
                if(!(a[r][c].equals("*"))){
                int count = 0;
                if(a[r-1][c-1].equals("*")){
                    count++;
                }
                if(a[r-1][c].equals("*")){
                    count++;
                }
                if(a[r-1][c+1].equals("*")){
                    count++;
                }

                if(a[r][c-1].equals("*")){
                    count++;
                }
                if(a[r][c].equals("*")){
                    count++;
                }
                if(a[r][c+1].equals("*")){
                    count++;
                }

                if(a[r+1][c-1].equals("*")){
                    count++;
                }
                if(a[r+1][c].equals("*")){
                    count++;
                }
                if(a[r+1][c+1].equals("*")){
                    count++;
                }
                changeSquare(count, r, c);
            }
        }
        }
        //goes through Case #1
        for(int c = 1; c<8; c++){
            if(!(a[0][c].equals("*"))){
            int count = 0;
                if(a[0][c-1].equals("*")){
                    count++;
                }
                if(a[0][c].equals("*")){
                    count++;
                }
                if(a[0][c+1].equals("*")){
                    count++;
                }

                if(a[1][c-1].equals("*")){
                    count++;
                }
                if(a[1][c].equals("*")){
                    count++;
                }
                if(a[1][c+1].equals("*")){
                    count++;
                }
                changeSquare(count, 0, c);
            }
        }
        //goes through Case #7
        for(int c = 1; c<8; c++){
            if(!(a[8][c].equals("*"))){
            int count = 0;
                if(a[8][c-1].equals("*")){
                    count++;
                }
                if(a[8][c].equals("*")){
                    count++;
                }
                if(a[8][c+1].equals("*")){
                    count++;
                }

                if(a[7][c-1].equals("*")){
                    count++;
                }
                if(a[7][c].equals("*")){
                    count++;
                }
                if(a[7][c+1].equals("*")){
                    count++;
                }
                changeSquare(count, 8, c);
            }
        }
        //goes through Case #9
        for(int r=1; r<8; r++){
            if(!(a[r][0].equals("*"))){
                int count = 0;
                if(a[r-1][0].equals("*")){
                    count++;
                }
                if(a[r-1][1].equals("*")){
                    count++;
                }
                if(a[r][0].equals("*")){
                    count++;
                }
                if(a[r][1].equals("*")){
                    count++;
                }
                if(a[r+1][0].equals("*")){
                    count++;
                }
                if(a[r+1][1].equals("*")){
                    count++;
                }
                changeSquare(count, r, 0);
            }
    }
        //goes through Case #8
        for(int r=1; r<8; r++){
            if(!(a[r][8].equals("*"))){
                int count = 0;
                if(a[r-1][7].equals("*")){
                    count++;
                }
                if(a[r-1][8].equals("*")){
                    count++;
                }
                if(a[r][7].equals("*")){
                    count++;
                }
                if(a[r][8].equals("*")){
                    count++;
                }
                if(a[r+1][7].equals("*")){
                    count++;
                }
                if(a[r+1][8].equals("*")){
                    count++;
                }
                changeSquare(count, r, 8);
            }
        }
        //goes through Case #2
        for(int r= 0; r<1; r++){
            if(!(a[0][0].equals("*"))){
                int count = 0;
                if(a[0][1].equals("*")){
                    count++;
                }
                if(a[1][1].equals("*")){
                    count++;
                }
                if(a[1][0].equals("*")){
                    count++;
                }
                changeSquare(count, 0, 0);
            }
        }
        //goes through Case #4
        for(int r= 0; r<1; r++){
            if(!(a[0][8].equals("*"))){
                int count = 0;
                if(a[0][7].equals("*")){
                    count++;
                }
                if(a[1][7].equals("*")){
                    count++;
                }
                if(a[1][8].equals("*")){
                    count++;
                }
                changeSquare(count, 0, 8);
            }
        }
        //goes through Case #3
        for(int c= 0; c<1; c++){
            if(!(a[8][0].equals("*"))){
                int count = 0;
                if(a[7][0].equals("*")){
                    count++;
                }
                if(a[7][1].equals("*")){
                    count++;
                }
                if(a[8][1].equals("*")){
                    count++;
                }
                changeSquare(count, 8, 0);
            }  
        }
        //goes through Case #5
        for(int c= 0; c<1; c++){
            if(!(a[8][8].equals("*"))){
                int count = 0;
                if(a[7][8].equals("*")){
                    count++;
                }
                if(a[7][7].equals("*")){
                    count++;
                }
                if(a[8][7].equals("*")){
                    count++;
                }
                changeSquare(count, 8, 8);
            }  
        }
        //goes through Case #6
    } 

    public static void revealSquare(int r, int c, int num){
        if (num==0){
            GameBoard[r-1][c-1]=HiddenBoard[r-1][c-1];
            GameBoard[r-1][c]=HiddenBoard[r-1][c];
            GameBoard[r-1][c+1]=HiddenBoard[r-1][c+1];

            GameBoard[r][c-1]=HiddenBoard[r][c-1];
            GameBoard[r][c]=HiddenBoard[r][c];
            GameBoard[r][c+1]=HiddenBoard[r][c+1];

            GameBoard[r+1][c-1]=HiddenBoard[r+1][c-1];
            GameBoard[r+1][c]=HiddenBoard[r+1][c];
            GameBoard[r+1][c+1]=HiddenBoard[r+1][c+1];
        }
        else{
            if(HiddenBoard[r][c].equals("0")){
                GameBoard[r][c]=HiddenBoard[r][c];
                LinkZeros(r, c, "a");
            }
            else{
                GameBoard[r][c]=HiddenBoard[r][c];
            }
        }
    }

    public static void printSquare(String[][] board){
        System.out.println("  1 2 3 4 5 6 7 8 9");
        System.out.println(" --------------------");
        System.out.print("1|");
        for(int r = 0; r <= board.length-1; r++){
            for(int c = 0; c <= board.length-1; c++){
                System.out.print(""+ board[r][c] + " ");
            }
            System.out.print("|");
            System.out.println("");
            if(r+2<10){
                System.out.print(r+2 + "|");
            }
        }
        System.out.println(" --------------------");
    }

    public static boolean isVictory(){
        boolean result = true;
        String[][] tempBoard = new String[9][9];
        for(int a = 0; a<9; a++){
            for(int b = 0; b<9; b++){
                if(GameBoard[a][b].equals(ANSI_YELLOW+"F"+ANSI_RESET)){
                    tempBoard[a][b] = "*";
                }
                else{
                    tempBoard[a][b] = GameBoard[a][b];
                }
            }
        }
        for(int r = 0; r < 9; r++){
            for(int c = 0; c < 9; c++){
                if(!(HiddenBoard[r][c].equals(tempBoard[r][c]))){
                    result = false;
                }
            }
        }
        return result;
    }

    public static void changeSquare(int r, int c){
        GameBoard[r][c] = ANSI_YELLOW+"F"+ANSI_RESET;;
    }

    public static void LinkZeros(int r, int c, String a){
        //middle chunk
        if((r<8&&r>0)&&(c<8&&c>0)){
            if((HiddenBoard[r+1][c].equals(ANSI_BLACK+"0"+ANSI_RESET))&&!(GameBoard[r+1][c].equals(ANSI_BLACK+"0"+ANSI_RESET))&&(!(a.equals("t")))){
                GameBoard[r+1][c]=HiddenBoard[r+1][c];
                LinkZeros(r+1,c, "b");
            }
            if(HiddenBoard[r][c+1].equals(ANSI_BLACK+"0"+ANSI_RESET)&&!(GameBoard[r][c+1].equals(ANSI_BLACK+"0"+ANSI_RESET))&&(!(a.equals("l")))){
                GameBoard[r][c+1]=HiddenBoard[r][c+1];
                LinkZeros(r,c+1, "r");
            }
            if(HiddenBoard[r][c-1].equals(ANSI_BLACK+"0"+ANSI_RESET)&&!(GameBoard[r][c-1].equals(ANSI_BLACK+"0"+ANSI_RESET))&&(!(a.equals("r")))){
                GameBoard[r][c-1]=HiddenBoard[r][c-1];
                LinkZeros(r,c-1, "l");
            }
            if(HiddenBoard[r-1][c].equals(ANSI_BLACK+"0"+ANSI_RESET)&&!(GameBoard[r-1][c].equals(ANSI_BLACK+"0"+ANSI_RESET))&&(!(a.equals("b")))){
                GameBoard[r-1][c]=HiddenBoard[r-1][c];
                LinkZeros(r-1,c,"t");
            }
        }
        //bottom middle
        else if(r==8&&c!=0&&c!=8){
            if(HiddenBoard[r][c+1].equals(ANSI_BLACK+"0"+ANSI_RESET)&&!(GameBoard[r][c+1].equals(ANSI_BLACK+"0"+ANSI_RESET))&&(!(a.equals("l")))){
                GameBoard[r][c+1]=HiddenBoard[r][c+1];
                LinkZeros(r,c+1, "r");
            }
            if(HiddenBoard[r][c-1].equals(ANSI_BLACK+"0"+ANSI_RESET)&&!(GameBoard[r][c-1].equals(ANSI_BLACK+"0"+ANSI_RESET))&&(!(a.equals("r")))){
                GameBoard[r][c-1]=HiddenBoard[r][c-1];
                LinkZeros(r,c-1, "l");
            }
            if(HiddenBoard[r-1][c].equals(ANSI_BLACK+"0"+ANSI_RESET)&&!(GameBoard[r-1][c].equals(ANSI_BLACK+"0"+ANSI_RESET))&&(!(a.equals("b")))){
                GameBoard[r-1][c]=HiddenBoard[r-1][c];
                LinkZeros(r-1,c,"t");
            }
        }
        //top middle
        else if(r==0&&c!=0&&c!=8){
            if((HiddenBoard[r+1][c].equals(ANSI_BLACK+"0"+ANSI_RESET))&&!(GameBoard[r+1][c].equals(ANSI_BLACK+"0"+ANSI_RESET))&&(!(a.equals("t")))){
                GameBoard[r+1][c]=HiddenBoard[r+1][c];
                LinkZeros(r+1,c, "b");
            }
            if(HiddenBoard[r][c+1].equals(ANSI_BLACK+"0"+ANSI_RESET)&&!(GameBoard[r][c+1].equals(ANSI_BLACK+"0"+ANSI_RESET))&&(!(a.equals("l")))){
                GameBoard[r][c+1]=HiddenBoard[r][c+1];
                LinkZeros(r,c+1, "r");
            }
            if(HiddenBoard[r][c-1].equals(ANSI_BLACK+"0"+ANSI_RESET)&&!(GameBoard[r][c-1].equals(ANSI_BLACK+"0"+ANSI_RESET))&&(!(a.equals("r")))){
                GameBoard[r][c-1]=HiddenBoard[r][c-1];
                LinkZeros(r,c-1, "l");
            }
        }
        //left middle
        else if(c==0&&r!=0&&r!=8){
            if((HiddenBoard[r+1][c].equals(ANSI_BLACK+"0"+ANSI_RESET))&&!(GameBoard[r+1][c].equals(ANSI_BLACK+"0"+ANSI_RESET))&&(!(a.equals("t")))){
                GameBoard[r+1][c]=HiddenBoard[r+1][c];
                LinkZeros(r+1,c, "b");
            }
            if(HiddenBoard[r][c+1].equals(ANSI_BLACK+"0"+ANSI_RESET)&&!(GameBoard[r][c+1].equals(ANSI_BLACK+"0"+ANSI_RESET))&&(!(a.equals("l")))){
                GameBoard[r][c+1]=HiddenBoard[r][c+1];
                LinkZeros(r,c+1, "r");
            }
            if(HiddenBoard[r-1][c].equals(ANSI_BLACK+"0"+ANSI_RESET)&&!(GameBoard[r-1][c].equals(ANSI_BLACK+"0"+ANSI_RESET))&&(!(a.equals("b")))){
                GameBoard[r-1][c]=HiddenBoard[r-1][c];
                LinkZeros(r-1,c,"t");
            }
        }
        //right middle
        else if(c==8&&r!=0&&r!=8){
            if(HiddenBoard[r][c-1].equals(ANSI_BLACK+"0"+ANSI_RESET)&&!(GameBoard[r][c-1].equals(ANSI_BLACK+"0"+ANSI_RESET))&&(!(a.equals("r")))){
                GameBoard[r][c-1]=HiddenBoard[r][c-1];
                LinkZeros(r,c-1, "l");
            }
            if(HiddenBoard[r-1][c].equals(ANSI_BLACK+"0"+ANSI_RESET)&&!(GameBoard[r-1][c].equals(ANSI_BLACK+"0"+ANSI_RESET))&&(!(a.equals("b")))){
                GameBoard[r-1][c]=HiddenBoard[r-1][c];
                LinkZeros(r-1,c,"t");
            }
            if((HiddenBoard[r+1][c].equals(ANSI_BLACK+"0"+ANSI_RESET))&&!(GameBoard[r+1][c].equals(ANSI_BLACK+"0"+ANSI_RESET))&&(!(a.equals("t")))){
                GameBoard[r+1][c]=HiddenBoard[r+1][c];
                LinkZeros(r+1,c, "b");
            }
        }
    }

    public static void changeSquare(int count, int a, int b){
        if(count==0){
            HiddenBoard[a][b] = ANSI_BLACK+"0"+ANSI_RESET;
        }
        if(count==1){
            HiddenBoard[a][b] = ANSI_BLUE+"1"+ANSI_RESET;
        }
        if(count==2){
            HiddenBoard[a][b] = ANSI_GREEN+"2"+ANSI_RESET;
        }
        if(count==3){
            HiddenBoard[a][b] = ANSI_GREEN+"3"+ANSI_RESET;
        }
        if(count==4){
            HiddenBoard[a][b] = ANSI_PURPLE+"4"+ANSI_RESET;
        }
        if(count==5){
            HiddenBoard[a][b] = ANSI_RED+"5"+ANSI_RESET;
        }
        if(count==6){
            HiddenBoard[a][b] = ANSI_CYAN+"6"+ANSI_RESET;
        }
        if(count==7){
            HiddenBoard[a][b] = ANSI_BLACK+"7"+ANSI_RESET;
        }
        if(count==8){
            HiddenBoard[a][b] = "8";
        }
        if(count==9){
            HiddenBoard[a][b] = "9";
        }
    }
}