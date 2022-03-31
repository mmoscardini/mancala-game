package com.mmoscardini.mancala.core.board;

import com.mmoscardini.mancala.core.pit.Pit;
import com.mmoscardini.mancala.core.player.Player;

public class Board {
    private Pit pit1 = new Pit(1,false);
    private Pit pit2 = new Pit(2, false);
    private Pit pit3 = new Pit(3, false);
    private Pit pit4 = new Pit(4, false);
    private Pit pit5 = new Pit(5, false);
    private Pit pit6 = new Pit(6, false);
    private Pit pit7 = new Pit(7, false);
    private Pit pit8 = new Pit(8, false);
    private Pit pit9 = new Pit(9, false);
    private Pit pit10 = new Pit(10, false);
    private Pit pit11 = new Pit(11, false);
    private Pit pit12 = new Pit(12, false);

    private Pit bigPit1  = new Pit(91, true);
    private Pit bigPit2 = new Pit(92, true);

    public Board(Player player1, Player player2, Integer stones) {
        //set stones
        bigPit1.setStones(0);
        bigPit2.setStones(0);
        pit1.setStones(stones);
        pit2.setStones(stones);
        pit3.setStones(stones);
        pit4.setStones(stones);
        pit5.setStones(stones);
        pit6.setStones(stones);
        pit7.setStones(stones);
        pit8.setStones(stones);
        pit9.setStones(stones);
        pit10.setStones(stones);
        pit11.setStones(stones);
        pit12.setStones(stones);

        //set players
        bigPit1.setOwner(player1);
        pit1.setOwner(player1);
        pit2.setOwner(player1);
        pit3.setOwner(player1);
        pit4.setOwner(player1);
        pit5.setOwner(player1);
        pit6.setOwner(player1);

        pit7.setOwner(player2);
        pit8.setOwner(player2);
        pit9.setOwner(player2);
        pit10.setOwner(player2);
        pit11.setOwner(player2);
        pit12.setOwner(player2);
        bigPit2.setOwner(player2);

        //set players big pit
        player1.setBigPit(bigPit1);
        player2.setBigPit(bigPit2);

        //set next pit
        pit1.setNextPit(pit2);
        pit2.setNextPit(pit3);
        pit3.setNextPit(pit4);
        pit4.setNextPit(pit5);
        pit5.setNextPit(pit6);
        pit6.setNextPit(bigPit1);
        bigPit1.setNextPit(pit7);
        pit7.setNextPit(pit8);
        pit8.setNextPit(pit9);
        pit9.setNextPit(pit10);
        pit10.setNextPit(pit11);
        pit11.setNextPit(pit12);
        pit12.setNextPit(bigPit2);
        bigPit2.setNextPit(pit1);

        //set opposite pit
        bigPit1.setOpposite(bigPit2);
        pit1.setOpposite(pit12);
        pit2.setOpposite(pit11);
        pit3.setOpposite(pit10);
        pit4.setOpposite(pit9);
        pit5.setOpposite(pit8);
        pit6.setOpposite(pit7);
        bigPit2.setOpposite(bigPit1);
        pit7.setOpposite(pit6);
        pit8.setOpposite(pit5);
        pit9.setOpposite(pit4);
        pit10.setOpposite(pit3);
        pit11.setOpposite(pit2);
        pit12.setOpposite(pit1);

    }

    public Pit getPit(Integer id) {
        try {
            return (Pit) Board.class.getDeclaredField("pit"+id.toString()).get(this);
        } catch (NoSuchFieldException |  IllegalAccessException e) {
            System.out.println("NO PIT WITH ID " + id + "/n" + e.getMessage());
            return  null;
        }
    }

    public Pit getBigPit(Integer id) {
        try {
            return (Pit) Board.class.getDeclaredField("bigPit"+id.toString()).get(this);
        } catch (NoSuchFieldException |  IllegalAccessException e) {
            System.out.println("NO BIG PIT WITH ID " + id + "/n" + e.getMessage());
            return  null;
        }
    }

    public Integer pickUpStones(Pit pit) {
        Integer stonesCopy = pit.getStones();
        pit.setStones(0);
        return stonesCopy;
    }

    public Integer stealOpositeStones(Player player, Pit pit) {
        Integer myStones = this.pickUpStones(pit);
        Integer opponentStones = this.pickUpStones(pit.getOpposite());

        player.getBigPit().addStones(myStones);
        player.getBigPit().addStones(opponentStones);

        return player.getBigPit().getStones();
    }


}
