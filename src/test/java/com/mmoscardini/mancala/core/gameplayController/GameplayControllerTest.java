package com.mmoscardini.mancala.core.gameplayController;

import com.mmoscardini.mancala.core.board.Board;
import com.mmoscardini.mancala.core.pit.Pit;
import com.mmoscardini.mancala.core.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class GameplayControllerTest {

    GameplayController gameplayController;
    @BeforeEach
    void setupGameplayController() {
        this.gameplayController = GameplayControllerSingletonFactory.getGameplayController(UUID.randomUUID().toString());
    }

    @Test
    void boardContainsAllElements() {
        //when default setup is called
        this.gameplayController.defaultSetup();

        //then all 14 pits should exist
        Board board = gameplayController.getBoard();
        assertTrue(Objects.equals(board.getPit(0), null));
        for(int i = 1; i<=12;i++) {
            assertFalse(Objects.equals(board.getPit(i), null));
        }
        assertTrue(Objects.equals(board.getPit(13), null));


        assertTrue(Objects.equals(board.getBigPit(0), null));
        assertFalse(Objects.equals(board.getBigPit(1), null));
        assertFalse(Objects.equals(board.getBigPit(2), null));
        assertTrue(Objects.equals(board.getBigPit(3), null));

    }

    @Test
    void boardContainsOnlyNecessaryElements() {
        //when default setup is called
        this.gameplayController.defaultSetup();

        //then no extra pits should be created
        Board board = gameplayController.getBoard();
        assertTrue(Objects.equals(board.getPit(0), null));
        assertTrue(Objects.equals(board.getPit(13), null));
        assertTrue(Objects.equals(board.getBigPit(0), null));
        assertTrue(Objects.equals(board.getBigPit(3), null));
    }

    @Test
    void checkBaseCaseEndGame() {
        //when empty board is created
        Player p1 = new Player(1, "p1");
        Player p2 = new Player(2, "p2");
        gameplayController.setup(p1, p2, 0);

        //then game will end
        assertTrue(gameplayController.hasGameEnded());
    }

    @Test
    void playerCannotMoveEnemyPiecesOnFirstMove() {
        //when default board is setup and player two try to move first
        gameplayController.defaultSetup();
        gameplayController.makeMove(10);

        //then no changes are made to the board
        Pit pit = gameplayController.getBoard().getPit(10);
        assertEquals(6, pit.getStones());

    }

    @Test
    void playerCannotMoveEnemyPiecesOnNMove() {
        //when default board is setup and after n moves are made
        gameplayController.defaultSetup();

        Random random = new Random();
        Integer moveCount = random.ints(5, 10).findAny().getAsInt();
        while (moveCount > 0) {
            if (moveCount % 2 == 0) {
                Integer pitId = new Random().ints(7, 12).findAny().getAsInt();
                gameplayController.makeMove(pitId);
            } else {
                Integer pitId = new Random().ints(1, 6).findAny().getAsInt();
                gameplayController.makeMove(pitId);
            }
            moveCount--;
        }

        //then no changes are made to the board
        Pit pit = gameplayController.getBoard().getPit(10);
        assertEquals(6, pit.getStones());

    }
}