package com.mmoscardini.mancala.core.gameplayController;

import com.mmoscardini.mancala.core.board.Board;
import com.mmoscardini.mancala.core.pit.Pit;
import com.mmoscardini.mancala.core.player.Player;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class GameplayController {
    private String gameId;
    private Board board;
    private Player playerOne;
    private Player playerTwo;
    private Player currentPlayer;
    private boolean setupCompleted = false;
    private List<String> actionsLog;

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

        playerOne = new Player(1, "Player 1");
        playerTwo = new Player(2, "Player 2");
        actionsLog = new ArrayList<>();

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
            actionsLog.add(0, currentPlayer.getName().concat(" is stealing stones from pit").concat(pitId.toString()));
            board.stealOpositeStones(currentPlayer, currentPit);
        }

        if (!isCurrentPlayerBigPit(currentPit)) {
            passTheTurn();
        } else {
            actionsLog.add(0, currentPlayer.getName().concat(" last stone was placed on his big pit! Play again!"));
        }

        return board;
    }

    public Board getBoard() {
        return board;
    }

    public List<String> getActionsLog() {
        return actionsLog;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean hasGameEnded() {
        Pit initialPit = board.getPit(1);
        Pit currentPit = initialPit;
        do {
            if (!currentPit.isBigPit() && currentPit.getStones() != 0) {
                System.out.println(currentPit.getId());
                return false;
            }
            currentPit = currentPit.getNextPit();
        } while (!Objects.equals(currentPit, initialPit));


        System.out.println("GAME HAS ENDED");
        board.collectRemainingStones();
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

    private boolean isValidPit(Integer pitId) {
        if (!currentPlayer.getOwnedPits().contains(pitId)) {
            actionsLog.add(0, "Current player do not own this pit. Please choose another one.");
            return false;
        }

        Integer stones = board.getPit(pitId).getStones();

        if (stones == 0) {
            actionsLog.add(0, "Cannot pick stones from empty pit. Please choose another one.");
            return false;
        }

        return true;
    }

    public Player getWinner() {
        if (playerOne.getStonesCount() == playerTwo.getStonesCount()) {
            return  null;
        } else if (playerOne.getStonesCount() > playerTwo.getStonesCount()) {
            return playerOne;
        }
        return playerTwo;
    }
}
