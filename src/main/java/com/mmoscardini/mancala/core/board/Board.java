package com.mmoscardini.mancala.core.board;

import com.mmoscardini.mancala.core.pit.Pit;
import com.mmoscardini.mancala.core.player.Player;

public class Board {
    private Pit pit1 = new Pit(false);
    private Pit pit2 = new Pit(false);
    private Pit pit3 = new Pit(false);
    private Pit pit4 = new Pit(false);
    private Pit pit5 = new Pit(false);
    private Pit pit6 = new Pit(false);
    private Pit pit7 = new Pit(false);
    private Pit pit8 = new Pit(false);
    private Pit pit9 = new Pit(false);
    private Pit pit10 = new Pit(false);
    private Pit pit11 = new Pit(false);
    private Pit pit12 = new Pit(false);

    private Pit bigPit1  = new Pit(true);
    private Pit bigPit2 = new Pit(true);

    public Board(Player player1, Player player2) {
        //set players
        bigPit1.setPlayer(player1);
        pit1.setPlayer(player1);
        pit2.setPlayer(player1);
        pit3.setPlayer(player1);
        pit4.setPlayer(player1);
        pit5.setPlayer(player1);
        pit6.setPlayer(player1);
        pit7.setPlayer(player2);
        pit8.setPlayer(player2);
        pit9.setPlayer(player2);
        pit10.setPlayer(player2);
        pit11.setPlayer(player2);
        pit12.setPlayer(player2);
        bigPit2.setPlayer(player2);

        //set next pit
        bigPit1.setNextPit(pit1);
        pit1.setNextPit(pit2);
        pit2.setNextPit(pit3);
        pit3.setNextPit(pit4);
        pit4.setNextPit(pit5);
        pit5.setNextPit(pit6);
        pit6.setNextPit(bigPit2);
        bigPit2.setNextPit(pit7);
        pit7.setNextPit(pit8);
        pit8.setNextPit(pit9);
        pit9.setNextPit(pit10);
        pit10.setNextPit(pit11);
        pit11.setNextPit(pit12);
        pit12.setNextPit(bigPit1);

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
//
//    public void setupBoard(Player player1, Player player2) {
//        bigPit1.setPlayer(player1);
//        bigPit1.setOpposite(bigPit2);
//        pit1 = new Pit(6, player1, false, pit2, pit12);
//        pit2 = new Pit(6, player1, false, pit3, pit11);
//        pit3= new Pit(6, player1, false, pit4, pit10);
//        pit4 = new Pit(6, player1, false, pit5, pit9);
//        pit5 = new Pit(6, player1, false, pit6, pit8);
//        pit6 = new Pit(6, player1, false, bigPit2, pit7);
//        bigPit2 = new Pit(0, player2, true, pit7, null);
//        pit7 = new Pit(6, player2, false, pit8, pit12);
//        pit8 = new Pit(6, player2, false, pit9, pit12);
//        pit9 = new Pit(6, player2, false, pit10, pit12);
//        pit10 = new Pit(6, player2, false, pit11, pit12);
//        pit11 = new Pit(6, player2, false, pit12, pit12);
//        pit12 = new Pit(6, player2, false, bigPit1, pit12);
//
//        player1.setBigPit(bigPit1);
//        player2.setBigPit(bigPit2);
//    }

    public Pit getPit(Integer id) {
        try {
            return (Pit) Board.class.getDeclaredField("pit"+id.toString()).get(this);
        } catch (NoSuchFieldException |  IllegalAccessException e) {
            System.out.println("NO PIT WITH ID " + id + "/n" + e.getMessage());
            return  null;
        }
    }

    public Pit getBigPit(Integer id) {
        switch (id) {
            case 1:
                return bigPit1;
            case 2:
                return bigPit2;
            default:
                return null;
        }
    }

    public Integer pickUpStones(Pit pit) {
        Integer stonesCopy = pit.getStones();
        pit.setStones(0);
        return stonesCopy;
    }

    public Integer stealOpositeStones(Pit pit) {
        Integer myStones = this.pickUpStones(pit);
        Integer opponentStones = this.pickUpStones(pit.getOpposite());

        Player player = pit.getPlayer();
        player.getBigPit().addStones(myStones);
        player.getBigPit().addStones(opponentStones);

        return player.getBigPit().getStones();
    }


}
