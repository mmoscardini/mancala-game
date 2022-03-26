package com.mmoscardini.mancala.core.pit;

import com.mmoscardini.mancala.core.player.Player;

import java.nio.charset.Charset;
import java.util.Random;

public class Pit {
    private Integer stones;
    private Player player;

    public Pit(Integer stones) {
        this.stones = stones;
        this.player = generateRandomPlayer();
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Integer getStones() {
        return stones;
    }

    public void setStones(Integer stones) {
        this.stones = stones;
    }

    private Player generateRandomPlayer() {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));

        String playerName = "player_" + generatedString;

        return new Player(playerName);
    }
}
