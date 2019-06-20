package com.example.tic_tac_toe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * GameBoardTest - test some of the basic functionality of the game board
 */
public class GameBoardTest {

    @Test(expected = NullPointerException.class)
    public void winOnMainDiagonalTest() {
        boolean expectedResult = true;

        GameBoard testBoard = new GameBoard();

        testBoard.getButtonByIndex(0,0).setText("X");
        testBoard.getButtonByIndex(0,1).setText("O");
        testBoard.getButtonByIndex(0,2).setText("X");
        testBoard.getButtonByIndex(1,0).setText("O");
        testBoard.getButtonByIndex(1,1).setText("X");
        testBoard.getButtonByIndex(1,2).setText("O");
        testBoard.getButtonByIndex(2,0).setText("X");
        testBoard.getButtonByIndex(2,1).setText("O");
        testBoard.getButtonByIndex(2,2).setText("X");

        Assert.assertEquals("Expected win on the main diagonal", expectedResult, testBoard.checkForWin());
    }

    @Test(expected = NullPointerException.class)
    public void winOnRowTest() {
        boolean expectedResult = true;

        GameBoard testBoard = new GameBoard();

        testBoard.getButtonByIndex(0,0).setText("X");
        testBoard.getButtonByIndex(0,1).setText("X");
        testBoard.getButtonByIndex(0,2).setText("X");

        Assert.assertEquals("Expected row win", expectedResult, testBoard.checkForWin());
    }

    @Test(expected = NullPointerException.class)
    public void winOnColumnTest() {
        boolean expectedResult = true;

        GameBoard testBoard = new GameBoard();

        testBoard.getButtonByIndex(0,1).setText("X");
        testBoard.getButtonByIndex(1,1).setText("X");
        testBoard.getButtonByIndex(2,1).setText("X");

        Assert.assertEquals("Expected row win", expectedResult, testBoard.checkForWin());
    }

    @Test(expected = NullPointerException.class)
    public void resetBoardTest() {
        boolean expectedResult = true;

        GameBoard testBoard = new GameBoard();

        testBoard.getButtonByIndex(0,1).setText("X");
        testBoard.getButtonByIndex(1,1).setText("O");
        testBoard.getButtonByIndex(1,2).setText("x");

        testBoard.setRoundCount(2);
        testBoard.setPlayer1Turn(false);

        testBoard.resetBoard();

        boolean resetResult = true;

        if (testBoard.getRoundCount() != 0) {
            resetResult = false;
            Assert.assertEquals("Board is not reset. Round count not reset.", expectedResult, resetResult);
        }

        if (testBoard.getPlayer1Turn()) {
            resetResult = false;
            Assert.assertEquals("Board is not reset. Player turn not reset.", expectedResult, resetResult);
        }

        for (int i = 0; i < GameBoard.MAX_SQUARE_BOARD_SIZE; i++) {
            for (int j = 0; j < GameBoard.MAX_SQUARE_BOARD_SIZE; j++) {
                if (testBoard.getButtonByIndex(i, j).getText() != "") {
                    resetResult = false;
                    Assert.assertEquals("Board is not reset. Board cells for X/O not reset.", expectedResult, resetResult);
                }
            }
        }

    }
}
