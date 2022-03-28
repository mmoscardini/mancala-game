package com.mmoscardini.mancala.core.player;

import com.mmoscardini.mancala.core.pit.Pit;

import java.util.List;

public class Player {
    private String name;
    private List<Integer> ownedPits;
    private Pit bigPit;

    public List<Integer> getOwnedPits() {
        return ownedPits;
    }

    public void setOwnedPits(List<Integer> ownedPits) {
        this.ownedPits = ownedPits;
    }

    public Player(String name) {
        this.name = name;
    }

    public Pit getBigPit() {
        return bigPit;
    }

    public void setBigPit(Pit bigPit) {
        this.bigPit = bigPit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
