package com.mmoscardini.mancala.core.board;

import com.mmoscardini.mancala.core.pit.Pit;
import com.mmoscardini.mancala.core.player.Player;

public class Board {
    private Pit pit1;
    private Pit pit2;
    private Pit pit3;
    private Pit pit4;
    private Pit pit5;
    private Pit pit6;
    private Pit pit7;
    private Pit pit8;
    private Pit pit9;
    private Pit pit10;
    private Pit pit11;
    private Pit pit12;

    private Pit bigPit1;
    private Pit bigPit2;

    public Board(Player player1, Player player2) {
        Pit bigPit1 = null;
        Pit pit1 = null;
        Pit pit2 = null;
        Pit pit3 = null;
        Pit pit4 = null;
        Pit pit5 = null;
        Pit pit6 = null;
        Pit bigPit2 = null;
        Pit pit7 = null;
        Pit pit8 = null;
        Pit pit9 = null;
        Pit pit10 = null;
        Pit pit11 = null;
        Pit pit12 = null;

        bigPit1 = new Pit(0, player1, pit1, null);
        pit1 = new Pit(6, player1, pit2, pit12);
        pit2 = new Pit(6, player1, pit3, pit11);
        pit3= new Pit(6, player1, pit4, pit10);
        pit4 = new Pit(6, player1, pit5, pit9);
        pit5 = new Pit(6, player1, pit6, pit8);
        pit6 = new Pit(6, player1, bigPit2, pit7);
        bigPit2 = new Pit(0, player2, pit7, null);
        pit7 = new Pit(6, player2, pit8, pit12);
        pit8 = new Pit(6, player2, pit9, pit12);
        pit9 = new Pit(6, player2, pit10, pit12);
        pit10 = new Pit(6, player2, pit11, pit12);
        pit11 = new Pit(6, player2, pit12, pit12);
        pit12 = new Pit(6, player2, bigPit1, pit12);
    }
}
