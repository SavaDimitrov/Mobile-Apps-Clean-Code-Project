package com.example.tic_tac_toe;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * PlayerTest - tests the method in the class Player
 */
public class PlayerTest {

    @Test
    public void setAndGetPlayerPointsTest() {
        Player testPlayer = new Player();

        testPlayer.setPlayerPoints(2);

        Assert.assertEquals(2, testPlayer.getPoints());
    }

    @Test
    public void increasePointsIfWinsTest() {
        Player testPlayer = new Player();

        testPlayer.increasePointsIfWins();

        Assert.assertEquals(1, testPlayer.getPoints());
    }
}