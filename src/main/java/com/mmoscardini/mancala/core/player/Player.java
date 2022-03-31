package com.mmoscardini.mancala.core.player;

import com.mmoscardini.mancala.core.pit.Pit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {
    private Integer id;
    private String name;
    private List<Integer> ownedPits;
    private Pit bigPit;

    public Player(Integer id, String name) {
        this.id = id;
        if (id == 1) {
            this.ownedPits = new ArrayList<>(Arrays.asList(1,2,3,4,5,6));
        } else if (id == 2) {
            this.ownedPits = new ArrayList<>(Arrays.asList(7,8,9,10,11,12));
        }
        this.name = name;
    }

    public List<Integer> getOwnedPits() {
        return ownedPits;
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

    public Integer getStones() {
        return this.bigPit.getStones();
    }

    public Integer getId() {
        return id;
    }
}
