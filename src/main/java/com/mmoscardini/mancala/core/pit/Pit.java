package com.mmoscardini.mancala.core.pit;

import com.mmoscardini.mancala.core.player.Player;

public class Pit {
    private Integer stones;
    private Player player;
    private boolean isBigPit;
    private Pit nextPit;
    private Pit opposite;

    public Pit(boolean isBigPit) {
        this.stones = 6;
        this.isBigPit = isBigPit;
    }

    public Pit(Integer stones, Player player, boolean isBigPit, Pit nextPit, Pit opposite) {
        this.stones = stones;
        this.player = player;
        this.isBigPit = isBigPit;
        this.nextPit = nextPit;
        this.opposite = opposite;
    }

    public Pit getNextPit() {
        return nextPit;
    }

    public void setNextPit(Pit nextPit) {
        this.nextPit = nextPit;
    }

    public Pit getOpposite() {
        return opposite;
    }

    public void setOpposite(Pit opposite) {
        this.opposite = opposite;
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

    public boolean isBigPit() {
        return isBigPit;
    }

    public Integer addStones(Integer stones) {
        this.stones += stones;
        return this.stones;
    }

}
