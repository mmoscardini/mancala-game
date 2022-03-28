package com.mmoscardini.mancala.api.controller;

import com.mmoscardini.mancala.core.board.Board;
import com.mmoscardini.mancala.core.gameplayController.GameplayController;
import com.mmoscardini.mancala.core.gameplayController.GameplayControllerFactory;
import com.mmoscardini.mancala.core.pit.Pit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class GameController {

    @Value("${spring.application.name}")
    String appName;

    @GetMapping("/")
    public String menuPage(Model model) {
        return "menu";
    }

    @GetMapping("/game/{gameId}")
    public String gamePlayPage(@PathVariable String gameId, Model model) {
        model.addAttribute("gameId", gameId);


        GameplayController gameplayController = GameplayControllerFactory.getGameplayController(gameId);
        gameplayController.setup();

        Board board = gameplayController.getBoard();
        Pit[] p1Pits = {board.getPit(1), board.getPit(2), board.getPit(3), board.getPit(4), board.getPit(5), board.getPit(6)};
        Pit[] p2Pits = {board.getPit(7), board.getPit(8), board.getPit(9), board.getPit(10), board.getPit(11), board.getPit(12)};

        System.out.println("pit1 " + board.getBigPit(1));


        model.addAttribute("p1_pits", p1Pits);
        model.addAttribute("p2_pits", p2Pits);
        model.addAttribute("p1_bigpit", board.getBigPit(1));
        model.addAttribute("p2_bigpit", board.getBigPit(2));
        return "game";
    }

    @PutMapping("/game/{gameId}/{pitId}")
    public String makeMove(@PathVariable String gameId, @PathVariable Integer pitId, Model model) {
        GameplayController gameplayController = GameplayControllerFactory.getGameplayController(gameId);
        gameplayController.setup();

        Board board = gameplayController.getBoard();
        Pit[] p1Pits = {board.getPit(1), board.getPit(2), board.getPit(3), board.getPit(4), board.getPit(5), board.getPit(6)};
        Pit[] p2Pits = {board.getPit(7), board.getPit(8), board.getPit(9), board.getPit(10), board.getPit(11), board.getPit(12)};

        model.addAttribute("p1_pits", p1Pits);
        model.addAttribute("p2_pits", p2Pits);
        model.addAttribute("p1_bigpit", board.getBigPit(1));
        model.addAttribute("p2_bigpit", board.getBigPit(2));
        return "game";
    }
}
