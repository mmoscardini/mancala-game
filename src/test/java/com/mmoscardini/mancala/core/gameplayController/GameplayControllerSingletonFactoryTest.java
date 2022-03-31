package com.mmoscardini.mancala.core.gameplayController;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameplayControllerSingletonFactoryTest {

    String GAME_ID = "gameId";

    @Test
    void shouldGenerateSingleton() {
        GameplayController gameplayController1 = GameplayControllerSingletonFactory.getGameplayController(GAME_ID);
        GameplayController gameplayController2 = GameplayControllerSingletonFactory.getGameplayController(GAME_ID);

        assertTrue(gameplayController1.equals(gameplayController2));
    }

    @Test
    void shouldSetupGameController() {
        GameplayController gameplayController = GameplayControllerSingletonFactory.getGameplayController(GAME_ID);
        assertFalse(gameplayController.getBoard().equals(null));
    }

}