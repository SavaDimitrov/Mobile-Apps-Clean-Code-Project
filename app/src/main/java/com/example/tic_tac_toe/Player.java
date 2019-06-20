package com.example.tic_tac_toe;

class Player {

    private int points;

    void setPlayerPoints(int newPoints) {
        if (newPoints <= 0) {
            points = 0;
            return;
        }

        points = newPoints;
    }

    int getPoints() {
        return points;
    }

    void increasePointsIfWins() {
        points++;
    }
}
