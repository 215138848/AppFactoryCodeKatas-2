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
    private MyGameOfLife gameTester;

    @Before
    public void setUp(){
        this.gameTester = new MyGameOfLife();
    }

    @Test
    public void a_FillBoardGameTest() {
        System.out.println( "Test 1: Filling board");
        board = gameTester.newLBoard();
        assertNotNull(board[1][2]);
        assertNotNull(board[2][3]);
        assertNotNull(board[3][1]);
        assertNotNull(board[3][2]);
        assertNotNull(board[3][3]);
        System.out.println("The board has been filled\n" + gameTester.PrintBoard(board));
    }

    @Test
    public void b_PrintBoardGameTest() {
        System.out.println( "Test 2: Printing Board");
        board = gameTester.newLBoard();
        String map = gameTester.PrintBoard(board);
        assertNotNull(map);
        System.out.println("this is the board printed\n" + map);
    }

    @Test
    public void c_Neighbors(){
        board = gameTester.newLBoard();
        System.out.println( "Test 3: Counting of neighbors");
        int num = gameTester.neighbourCounter(board,2,2);
        assertEquals(num, 5);
        board [2][2] = "@";
        System.out.println( gameTester.PrintBoard(board));
        System.out.println( "position [2][2] = @ and has " + num + " neighbours\n" );
    }

    @Test
    public void d_GenNewBoardTest() {
        System.out.println( "Test 4: Generate New Random Board");
        board = gameTester.GenNewBoard(6,6);

        assertEquals(board.length, 6 );
        System.out.println("Random Board is successful \nGeneration 1 Board: \n" + gameTester.PrintBoard(board) );
    }

    @Test
    public void e_LifeGameTest() {
        System.out.println( "Test 5: Executing Life");
        board = gameTester.newLBoard();
        String [][] originalBoard = board;
        board = gameTester.life(board);
        assertNotEquals(originalBoard[1][2], board[1][2] );
        System.out.println("Game of life is successful \nGeneration 1 Board: \n" + gameTester.PrintBoard(originalBoard) + "\nGeneration 2 Board: \n" + gameTester.PrintBoard(board) );
    }

}
