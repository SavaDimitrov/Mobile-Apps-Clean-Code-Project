package com.example.tic_tac_toe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PlayerVSPlayerSameDevice extends AppCompatActivity implements View.OnClickListener {
    private static final int PLAYER_ONE_NUMBER = 1;
    private static final int PLAYER_TWO_NUMBER = 2;
    private static final int MAX_GAME_ROUNDS = 9;
    private static final int MIN_ROUNDS_ELAPSED_FOR_WIN = 5;

    private GameBoard board = new GameBoard();

    private Player player1 = new Player();
    private Player player2 = new Player();

    private TextView textViewPointsPlayer1;
    private TextView textViewPointsPlayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewPointsPlayer1 = findViewById(R.id.text_view_p1);
        textViewPointsPlayer2 = findViewById(R.id.text_view_p2);

        for (int i = 0; i < GameBoard.MAX_SQUARE_BOARD_SIZE; i++) {
            for (int j = 0; j < GameBoard.MAX_SQUARE_BOARD_SIZE; j++) {

                String buttonID = "button_" + i + j;
                int resourceID = getResources().getIdentifier(buttonID, "id", getPackageName());

                Button storeViewById = findViewById(resourceID);
                board.setButtonViaIndex(i, j, storeViewById);
                board.getButtonByIndex(i, j).setOnClickListener(this);
            }
        }

        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }

        if (board.getPlayer1Turn()) {
            ((Button) v).setText("X");
        } else {
            ((Button) v).setText("O");
        }

        int changeRound = board.getRoundCount() + 1;
        board.setRoundCount(changeRound);

        if (board.getRoundCount() >= MIN_ROUNDS_ELAPSED_FOR_WIN) {
            announceWinnerOrChangeTurn();
        } else {
            board.setPlayer1Turn(!board.getPlayer1Turn());
        }
    }

    private void announceWinnerOrChangeTurn() {

        if (board.checkForWin()) {
            if (board.getPlayer1Turn()) {
                playerWon(PLAYER_ONE_NUMBER);
            } else {
                playerWon(PLAYER_TWO_NUMBER);
            }
        } else if (board.getRoundCount() == MAX_GAME_ROUNDS) {
            draw();
        } else {
            board.setPlayer1Turn(!board.getPlayer1Turn());
        }
    }

    private void playerWon(int winningPlayerNumber) {
        if (winningPlayerNumber == PLAYER_ONE_NUMBER) {
            player1.increasePointsIfWins();

        } else {
            player2.increasePointsIfWins();
        }

        String toastText = "Player " + winningPlayerNumber + " wins!";
        Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show();

        updatePointsText();
        board.resetBoard();
    }

    private void draw() {
        Toast.makeText(this,"Draw!", Toast.LENGTH_SHORT).show();
        board.resetBoard();
    }

    private void resetGame() {
        player1.setPlayerPoints(0);
        player2.setPlayerPoints(0);
        updatePointsText();

        board.resetBoard();
    }

    private void updatePointsText() {
        String updatePlayer1Points = "Player 1: " + player1.getPoints();
        textViewPointsPlayer1.setText(updatePlayer1Points);
        String updatePlayer2Points = "Player 2: " + player2.getPoints();
        textViewPointsPlayer2.setText(updatePlayer2Points);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCount", board.getRoundCount());
        outState.putInt("player1Points", player1.getPoints());
        outState.putInt("player2Points", player2.getPoints());
        outState.putBoolean("player1Turn", board.getPlayer1Turn());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        board.setRoundCount(savedInstanceState.getInt("roundCount"));
        player1.setPlayerPoints(savedInstanceState.getInt("player1Points"));
        player2.setPlayerPoints(savedInstanceState.getInt("player2Points"));
        board.setPlayer1Turn(savedInstanceState.getBoolean("player1Turn"));
    }
}
