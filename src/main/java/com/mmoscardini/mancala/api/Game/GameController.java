package com.mmoscardini.mancala.api.Game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GameController {

    private GameService gameService;

    @Autowired
    public void GameplayController(GameService gameService) {
        this.gameService = gameService;
    }

    @Value("${spring.application.name}")
    String appName;

    @GetMapping("/game/")
    public String menuPage(Model model) {
        return "menu";
    }

    @GetMapping("/game/{gameId}")
    public String gamePlayPage(@PathVariable String gameId, Model model) {
        gameService.setupGame(gameId);

        return gameService.buildHtml(gameId, model);
    }

    @PostMapping("/game/{gameId}/makeMove/{pitId}")
    public String makeMove(@PathVariable String gameId, @PathVariable Integer pitId, Model model) {

        gameService.makeMove(gameId, pitId);

        return gameService.buildHtml(gameId, model);
    }
}
