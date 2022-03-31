package com.mmoscardini.mancala.core.gameplayController;

import com.mmoscardini.mancala.core.board.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;
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
    void playerOnedPits() {

    }
}