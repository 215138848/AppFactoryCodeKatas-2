package za.co.optisolutions;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import za.co.optisolutions.MyGameOfLife;

/**
 * Grant Hendricks
 * Unit test for Game of Life App.
 */

@TestMethodOrder(MethodOrderer.MethodName.class)
public class MyGameOfLifeTest{

    String [][] board = new String[5][5];
    MyGameOfLife gameTester = new MyGameOfLife();

    @Test
    public void a_FillBoardGameTest() {
        System.out.println( "Test 1: Filling board" );
        board = gameTester.newLBoard();
        assertNotNull(board[1][2]);
        assertNotNull(board[2][3]);
        assertNotNull(board[3][1]);
        assertNotNull(board[3][2]);
        assertNotNull(board[3][3]);
        System.out.println("The board has been filled\n" + gameTester.PrintBoard(board));
    }

    @Test
    public void b_Neighbors(){
        board = gameTester.newLBoard();
        System.out.println( "Test 2: Counting of neighbors" );
        assertEquals(gameTester.neighbourCounter(board,3,2), 3);
        System.out.println("The board has been filled\n" + gameTester.PrintBoard(board));
        System.out.println( "position [3][2] has " + 5 + " neighbours" );
    }

    @Test
    public void c_PrintBoardGameTest() {
        System.out.println( "Test 3: Counting of neighbors" );
        board = gameTester.newLBoard();
        assertNotNull(gameTester.PrintBoard(board));
        System.out.println("this is the board printed\n" + gameTester.PrintBoard(board));
    }

    @Test
    public void d_LifeGameTest() {
        System.out.println( "Test 4: Executing Life" );
        board = gameTester.newLBoard();
        String [][] originalBoard = board;
        board = gameTester.life(board);
        assertEquals(originalBoard[3][3], board[2][3] );
        System.out.println("Game of life is successfull\n" + gameTester.PrintBoard(originalBoard) + "\n\nGeneration 2 Board: \n" + gameTester.PrintBoard(board) );
    }

}
