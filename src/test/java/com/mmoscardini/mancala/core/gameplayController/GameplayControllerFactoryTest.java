package com.mmoscardini.mancala.core.gameplayController;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameplayControllerFactoryTest {

    String GAME_ID = "gameId";

    @Test
    void shouldGenerateSingleton() {
        GameplayController gameplayController1 = GameplayControllerFactory.getGameplayController(GAME_ID);
        GameplayController gameplayController2 = GameplayControllerFactory.getGameplayController(GAME_ID);

        assertTrue(gameplayController1.equals(gameplayController2));
    }

    @Test
    void shouldSetupGameController() {
        GameplayController gameplayController = GameplayControllerFactory.getGameplayController(GAME_ID);
        assertFalse(gameplayController.getBoard().equals(null));
    }

}