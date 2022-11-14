package com.jdey.board.model.tokens;

import lombok.Getter;

@Getter
public class Gold implements PickUps {
    private final int cost;
    public Gold(int cost) {
        this.cost = cost;
    }
}
