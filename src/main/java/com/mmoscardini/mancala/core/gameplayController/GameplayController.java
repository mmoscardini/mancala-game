package com.mmoscardini.mancala.core.gameplayController;

import com.mmoscardini.mancala.core.board.Board;
import com.mmoscardini.mancala.core.pit.Pit;
import com.mmoscardini.mancala.core.player.Player;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.Objects;
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

    public  void setup(Player playerOne, Player playerTwo, Integer stones) {
        currentPlayer = playerOne;
        board = new Board(playerOne, playerTwo, stones);

        setupCompleted = true;
    }

    public void defaultSetup() {
        if(setupCompleted) {
            return;
        }

        playerOne = generateRandomPlayer(1);
        playerTwo = generateRandomPlayer(2);

        setup(playerOne, playerTwo, 6);
    }

    public Board makeMove(Integer pitId) {
         if (!isValidPit(pitId)){
            return board;
        }

        Pit initialPit = board.getPit(pitId);
        Integer stones = board.pickUpStones(initialPit);
        Pit currentPit = initialPit;

        while (stones > 0) {
            currentPit = currentPit.getNextPit();

            if (isEnemyBigPit(currentPit)) {
                continue;
            }

            currentPit.addStones(1);
            stones--;
        }

        if (shouldStealStones(currentPit)) {
            System.out.println("STEALING STONES IN PIT " + currentPit.getId());
            board.stealOpositeStones(currentPlayer, currentPit);
        }

        if (!isCurrentPlayerBigPit(currentPit)) {
            passTheTurn();
        }

        return board;
    }

    public Board getBoard() {
        return board;
    }

    public boolean hasGameEnded() {
        Pit initialPit = board.getPit(1);
        Pit currentPit = initialPit;
        do {
            if (!currentPit.isBigPit() && currentPit.getStones() != 0) {
                return false;
            }
            currentPit = currentPit.getNextPit();
        } while (!Objects.equals(currentPit, initialPit));

        return true;
    }

    public Player getPlayer(Integer playerId) {
        if (playerId == 1) {
            return playerOne;
        } else if (playerId == 2) {
            return playerTwo;
        }

        return null;
    }

    private void passTheTurn() {
        if (currentPlayer == playerOne) {
            currentPlayer = playerTwo;
            return;
        }

        currentPlayer = playerOne;

    }

    private boolean shouldStealStones(Pit pit) {
        return pit.getStones() == 1 && !pit.isBigPit();
    }

    private boolean isCurrentPlayerBigPit(Pit pit) {
        return pit.isBigPit() && pit.getOwner() == currentPlayer;
    }

    private boolean isEnemyBigPit(Pit pit) {
        return pit.isBigPit() && pit.getOwner() != currentPlayer;
    }

    private boolean isValidPit(Integer pitId){
        if (!currentPlayer.getOwnedPits().contains(pitId)){
            System.out.println("Player ".concat(currentPlayer.getName()).concat("Cannot choose this pit"));
            return false;
        }

        Integer stones = board.getPit(pitId).getStones();

        if (stones == 0) {
            System.out.println("Cannot pick stones from empty pit");
            return false;
        }

        return true;
    }

    private Player generateRandomPlayer(Integer playerId) {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));

        String playerName = "player_" + generatedString;

        return new Player(playerId, playerName);
    }
}
