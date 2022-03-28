package com.mmoscardini.mancala.core.gameplayController;

import com.mmoscardini.mancala.core.board.Board;
import com.mmoscardini.mancala.core.pit.Pit;
import com.mmoscardini.mancala.core.player.Player;
import com.sun.tools.jconsole.JConsoleContext;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

@Service
public class GameplayController {
    private String gameId;
    private Board board;
    private Player playerOne;
    private Player playerTwo;
    private Player currentPlayer;
    private boolean setupCompleted = false;

    public GameplayController() {
    }

    public GameplayController(String gameId) {
        this.gameId = gameId;
    }

    public void setup(Player playerOne, Player playerTwo) {
        currentPlayer = playerOne;
        board = new Board(playerOne, playerTwo);

        System.out.println(board.getPit(1));

        setupCompleted = true;
    }

    public void setup() {
        if(setupCompleted) {
            return;
        }

        playerOne = generateRandomPlayer();
        playerTwo = generateRandomPlayer();
        Integer[] pitIdsP1 = {1,2,3,4,5,6};
        Integer[] pitIdsP2 = {7,8,9,10,11,12};
        playerOne.setOwnedPits(new ArrayList<>(Arrays.asList(pitIdsP1)));
        playerTwo.setOwnedPits(new ArrayList<>(Arrays.asList(pitIdsP2)));

        setup(playerOne, playerTwo);
    }

    public Board makeMove(Integer pitId) {
         if (!currentPlayer.getOwnedPits().contains(pitId)){
            System.out.println("It's not this players turn");
            return board;
        }

        Pit initialPit = board.getPit(pitId);
        Integer stones = board.pickUpStones(initialPit);

        if (stones == 0) {
            System.out.println("Cannot pick stones from empty pit");
            return board;
        }

        Pit currentPit = initialPit;

        while (stones > 0) {
            currentPit = currentPit.getNextPit();

            if (currentPit.isBigPit() && currentPit.getPlayer() != currentPlayer) {
                //skip enemies big pit
                continue;
            }

            currentPit.addStones(1);
            stones--;
        }

        if (currentPit.getStones() == 1 && !currentPit.isBigPit()) {
            System.out.println("STEALING STONES IN PIT " + currentPit.getId());
            board.stealOpositeStones(currentPlayer, currentPit);
        }

        if (!currentPlayer.getBigPit().equals(currentPit)) {
            passTheTurn();
        }

        return board;
    }

    private void passTheTurn() {
        if (currentPlayer == playerOne) {
            currentPlayer = playerTwo;
            return;
        }

        currentPlayer = playerOne;

    }

    private Player generateRandomPlayer() {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));

        String playerName = "player_" + generatedString;

        return new Player(playerName);
    }

    public Board getBoard() {
        return board;
    }
}
