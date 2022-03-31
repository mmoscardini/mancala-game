package com.mmoscardini.mancala.core.pit;

import com.mmoscardini.mancala.core.player.Player;

public class Pit {
    private Integer id;
    private Integer stones;
    private Player owner;
    private boolean isBigPit;
    private Pit nextPit;
    private Pit opposite;

    public Pit(Integer id, boolean isBigPit) {
        this.id = id;
        this.isBigPit = isBigPit;
    }

    public Integer getId() {
        return id;
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

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
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
