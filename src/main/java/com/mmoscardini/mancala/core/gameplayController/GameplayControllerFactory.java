package com.mmoscardini.mancala.core.gameplayController;

import java.util.HashMap;

public class GameplayControllerFactory {
    private static HashMap<String, GameplayController> gameplayControllers = new HashMap<>();

    public static GameplayController getGameplayController(String gameId) {
        if (gameplayControllers.containsKey(gameId)) {
            return gameplayControllers.get(gameId);
        }

        GameplayController gameplayController = new GameplayController(gameId);
        gameplayController.setup();

        gameplayControllers.put(gameId, gameplayController);
        return gameplayController;
    }
}
