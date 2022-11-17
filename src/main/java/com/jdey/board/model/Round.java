package com.jdey.board.model;

import com.google.common.collect.ImmutableList;
import lombok.Getter;

import java.util.List;

public class Round {
    private final List<Turn> turns;
    private Player prevPlayer;
    @Getter private int turn = 0;

    Round(Turn... turns) {
        this.turns = ImmutableList.copyOf(turns);
    }

    public boolean isBlind() {
        return turns.get(turn) == Turn.BLIND;
    }

    public boolean isBackward() {
        return turns.get(turn) == Turn.BACKWARD;
    }

    public boolean isDouble() {
        return turns.get(turn) == Turn.DOUBLE;
    }

    public void nextTurn() {
        turn++;
    }

    public boolean isEmpty() {
        return turn >= turns.size();
    }

    public boolean mayBeNextPlayer(Player player) {
        if (!isDouble() || player == prevPlayer) {
            return true;
        }
        prevPlayer = player;
        return false;
    }

    public enum Turn{
        SIMPLE,
        BLIND,
        BACKWARD,
        DOUBLE
    }
}
