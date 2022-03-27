package com.mmoscardini.mancala.core.gameplayController;

import java.util.HashMap;

public class GamePlayControllerFactory {
    private static HashMap<String, GameplayController> gameplayControllers;

    public static GameplayController getGameplayControler(String gameId) {
        if (gameplayControllers.containsKey(gameId)) {
            return gameplayControllers.get(gameId);
        }

        GameplayController gameplayController = new GameplayController(gameId);
        gameplayController.setup();

        gameplayControllers.put(gameId, gameplayController);
        return gameplayController;
    }
}
