package com.mmoscardini.mancala.core.services;

import com.mmoscardini.mancala.core.entities.board.Board;
import com.mmoscardini.mancala.core.entities.player.Player;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.Random;

@Service
public class GameplayController {
    private Board board;
    private Player playerOne;
    private Player playerTwo;
    private Player currentPlayer;



    private Player generateRandomPlayer() {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));

        String playerName = "player_" + generatedString;

        return new Player(playerName);
    }
}
