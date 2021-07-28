package za.co.optisolutions;

/**
 * Grant Hendricks
 * Conway Game of life
 */

import java.util.Scanner;

public class MyGameOfLife {

    public static void main( String[] args )    {
        System.out.println( "Welcome to Conway's Game of Life." );
        GameIntro();
    }

    public static void GameIntro(){

        Scanner scan = new Scanner(System.in);

        System.out.println( "What type of game would you like to play: " );

        System.out.println( "1: Random Generated board" );
        System.out.println( "2: L Shaped board" );
        System.out.println( "3: M Shaped board" );
        System.out.println( "4: Manual input of board" );
        System.out.println( "5: Exit" );

        System.out.println( "Please input the number for the game you would like to play. " );
        int gameType = scan.nextInt();

            switch (gameType) {
                case 1: Game1Random();
                    break;
                case 2: GameLBoard();
                    break;
                case 3: GameMBoard();
                    break;
                case 4: Game4Manual();
                    break;
                default:    System.out.println( "       " );
                            System.out.println( "          GAME OVER       " );
                            System.out.println( "       " );
                            System.out.println( "    Thank You for Playing    " );
            }
    }

    public static void Game1Random(){

        Scanner scan = new Scanner(System.in);
        boolean continue1 = true;

        while (continue1){
            System.out.println( "Please enter the size of the board?" );
            System.out.print( "Number of Rows: " );
            int rowSize = scan.nextInt();
            System.out.print( "Number of Columns: " );
            int columnSize = scan.nextInt();

            String [][] board = GenNewBoard(rowSize,columnSize);

            System.out.println( "How many generations do you want to see?" );
            System.out.print( "Number of Generations: " );
            int generations = scan.nextInt();

            for(int i=0; i<=generations; i++){
                System.out.println("Generation " + i + ":");
                //printing board
                System.out.println(PrintBoard(board));
                board = life(board);
            }

            System.out.println( "Play Random-Board Again? y/n" );
            String ans = scan.next();
            if(ans.equalsIgnoreCase("y")){
                continue1=true;
            }
            else {
                continue1=false;
            }
        }
        GameIntro();
    }

    public static void GameLBoard(){

        String [][] LBoard = newLBoard();

        for(int i=0; i<=5; i++){
            System.out.println("Generation " + i + ":");
            //printing board
            System.out.println(PrintBoard(LBoard));
            LBoard = life(LBoard);
        }
        GameIntro();
    }

    public static String[][] newLBoard(){
        String [][] newBoard = new String [][] {{"_","_","_","_","_"}, {"_","_","#","_","_"}, {"_","_","_","#","_"}, {"_","#","#","#","_"}, {"_","_","_","_","_"} };
        return newBoard;
    }

    public static void GameMBoard(){

        String [][] MBoard = new String [][] {{"#","_","_","_","#"}, {"#","#","_","#","#"}, {"#","_","#","_","#"}, {"#","_","_","_","#"}, {"#","_","_","_","#"} };
        int rowSize = MBoard.length;
        int columnSize = MBoard[0].length;

        for(int i=0; i<=4; i++){
            System.out.println("Generation " + i + ":");
            //printing board
            System.out.println(PrintBoard(MBoard));
            MBoard = life(MBoard);
        }
        GameIntro();
    }

    public static void Game4Manual(){

        Scanner scan = new Scanner(System.in);
        boolean continue1 = true;

        while (continue1){
            System.out.println( "Welcome to Manual-board Game of Life " );
            System.out.println( "Please enter the details of the board? the board is 5x5 in size." );

            String [][] board = new String[5][5];

            for (int r=0; r<5; r++){
                for (int c=0; c<5; c++){
                    //enter the details of each cell
                    System.out.println( "Please enter 1 for Alive and 0 for dead?" + "\nCurrent cell is x:" + r + ", y: "+ c );
                    String temp = scan.next();

                    if (temp.isEmpty()){
                        board[r][c] = "_";
                    }
                    else {
                        if (temp.equals("1")){
                            board[r][c] = "#";
                        }
                        if (temp.equals("0")){
                            board[r][c] = "_";
                        }
                        if (!temp.equals("1") && !temp.equals("0")){
                            board[r][c] = "#";
                        }
                    }
                }//end of c
            }//end of r

            System.out.println( "Manual Board is done: Generation 0" );
            System.out.println(PrintBoard(board));
            System.out.println("Generation 1:");
            board = life(board);
            System.out.println(PrintBoard(board));



            System.out.println( "Play Manual-Board Again? y/n" );
            String ans = scan.next();
            if(ans.equalsIgnoreCase("y")){
                continue1=true;
            }
            else {
                continue1=false;
            }
        }
        GameIntro();
    }

    public static String[][] life(String [][] board){

        int rowSize = board.length;
        int columnSize = board[0].length;
        String [][] newBoard = new String[rowSize][columnSize];

        for (int row =0; row< rowSize; row++){

            for (int i=0; i<columnSize; i++ ){ /*i = columns*/

                if(board[row][i].equals("#")){
                    //if space is populated
                    int neighbourCount = neighbourCounter(board, row, i);
                    if(neighbourCount<=1){
                        newBoard[row][i] = "_"; //is dead
                    }
                    if(neighbourCount==2 || neighbourCount==3 ){
                        newBoard[row][i] = "#"; //is alive
                    }
                    if(neighbourCount>=4 ){
                        newBoard[row][i] = "_"; //is dead
                    }
                }
                if(board[row][i].equals("_")){
                    //if space is unpopulated
                    int neighbourCount = neighbourCounter(board, row, i);
                    if(neighbourCount==3 ){
                        newBoard[row][i] = "#"; //is alive
                    }
                    else {
                        newBoard[row][i] = "_"; //is dead
                    }

                }

            }
        }

        return newBoard;
    }

    public static String PrintBoard(String [][] board){
        int rowSize = board.length;
        int columnSize = board[0].length;
        String map = "";

        //printing board
        for (int rows =0; rows<rowSize; rows++){
            for (int columns=0; columns<columnSize; columns++){
                map += (board[rows][columns] + " ");
            }
            map += ("\n");
        }

        return map;
    }

    public static String [][] GenNewBoard(int rowSize, int columnSize){
        //String [][] setBoard = new String [][] {{"_","#","#","_","_"}, {"_","#","_","#","_"}, {"_","#","_","#","_"}, {"_","#","_","#","_"}, {"_","#","#","_","#"} };

        String [][] newBoard = new String [rowSize][columnSize] ;

        for (int rows =0; rows<rowSize; rows++){
            for (int columns=0; columns<columnSize; columns++){
                if ((Math.random()*10)>4){
                    newBoard[rows][columns] = "#";
                }
                else {
                    newBoard[rows][columns] = "_";
                }
            }
        }
        return newBoard;
    }

    public static int neighbourCounter(String [][] board, int x, int y){
        int rowSize = board.length;
        int columnSize = board[0].length;

        int neighbourCount = 0;
        if(y!=0){
            if(x!=0){
                if(board[x-1][y-1].equals("#")){neighbourCount++;}           //Start at bottom left
            }
            if(board[x][y-1].equals("#")){neighbourCount++;}
            if(x!=rowSize-1){
                if(board[x+1][y-1].equals("#")){neighbourCount++;}
            }
        }
        if(x!=0){
            if(board[x-1][y].equals("#")){neighbourCount++;}
        }
        //if(board[x][y].equals("#")){neighbourCount++;}     must not count the original
        if(x!=rowSize-1){
            if(board[x+1][y].equals("#")){neighbourCount++;}
        }
        if(y!=columnSize-1){
            if(x!=0){
                if(board[x-1][y+1].equals("#")){neighbourCount++;}
            }
            if(board[x][y+1].equals("#")){neighbourCount++;}
            if(x!=rowSize-1){
                if(board[x+1][y+1].equals("#")){neighbourCount++;}           //End at top right
            }
        }

        return neighbourCount;
    }
}
