package com.mmoscardini.mancala.api.Game;

import com.mmoscardini.mancala.core.board.Board;
import com.mmoscardini.mancala.core.gameplayController.GameplayController;
import com.mmoscardini.mancala.core.gameplayController.GameplayControllerSingletonFactory;
import com.mmoscardini.mancala.core.pit.Pit;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class GameService {

    public void makeMove(String gameId, Integer pitId) {
        GameplayController gameplayController = GameplayControllerSingletonFactory.getGameplayController(gameId);

        gameplayController.makeMove(pitId);
    }

    public String buildHtml(String gameId, Model model) {
        GameplayController gameplayController = GameplayControllerSingletonFactory.getGameplayController(gameId);
        model.addAttribute("gameId", gameId);

        if (gameplayController.hasGameEnded()) {
            buildEngGameModel(gameplayController, model);
            return "end";
        }

        buildGameplayModel(gameplayController, model);
        return "game";
    }

    private void buildGameplayModel(GameplayController gameplayController, Model model){
        Board board = gameplayController.getBoard();
        Pit[] p1Pits = {board.getPit(1), board.getPit(2), board.getPit(3), board.getPit(4), board.getPit(5), board.getPit(6)};
        Pit[] p2Pits = {board.getPit(12), board.getPit(11), board.getPit(10), board.getPit(9), board.getPit(8), board.getPit(7)};

        model.addAttribute("p1_pits", p1Pits);
        model.addAttribute("p2_pits", p2Pits);
        model.addAttribute("p1_bigpit", board.getBigPit(1));
        model.addAttribute("p2_bigpit", board.getBigPit(2));

        model.addAttribute("current_player", gameplayController.getCurrentPlayer());

        model.addAttribute("actions_log", gameplayController.getActionsLog());
    }

    private void buildEngGameModel(GameplayController gameplayController, Model model) {
        model.addAttribute("winner", "player1");
        model.addAttribute("player1", gameplayController.getPlayer(1));
        model.addAttribute("player2", gameplayController.getPlayer(2));
        model.addAttribute("player1_count", gameplayController.getPlayer(1).getBigPit().getStones());
        model.addAttribute("player2_count", gameplayController.getPlayer(2).getBigPit().getStones());
    }

    public void setupGame(String gameId) {
        GameplayController gameplayController = GameplayControllerSingletonFactory.getGameplayController(gameId);
        gameplayController.defaultSetup();
    }

}
