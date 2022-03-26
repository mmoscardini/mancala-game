package com.mmoscardini.mancala.api.controller;

import com.mmoscardini.mancala.core.pit.Pit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
        Pit pit = new Pit(6);
        Pit bigpit = new Pit(0);
        Pit[] pits = {pit, pit, pit, pit, pit, pit};

        model.addAttribute("p1_pits", pits);
        model.addAttribute("p2_pits", pits);
        model.addAttribute("p1_bigpit", bigpit);
        model.addAttribute("p2_bigpit", bigpit);
        return "game";
    }
}
