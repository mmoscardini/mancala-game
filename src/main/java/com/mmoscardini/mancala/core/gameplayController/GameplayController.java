package com.mmoscardini.mancala.core.gameplayController;

import com.mmoscardini.mancala.core.board.Board;
import com.mmoscardini.mancala.core.player.Player;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.Random;

@Service
public class GameplayController {
    private String gameId;
    private Board board;
    private Player playerOne;
    private Player playerTwo;
    private Player currentPlayer;

    public GameplayController(String gameId) {
        this.gameId = gameId;
    }

    public void setup() {
        playerOne = generateRandomPlayer();
        playerTwo = generateRandomPlayer();

        board = new Board(playerOne, playerTwo);
    }

    private Player generateRandomPlayer() {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));

        String playerName = "player_" + generatedString;

        return new Player(playerName);
    }
}
