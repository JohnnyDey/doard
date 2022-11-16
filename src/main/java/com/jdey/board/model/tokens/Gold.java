package com.jdey.board.model.tokens;

import lombok.Getter;

@Getter
public class Gold extends PickUps {
    private final int cost;

    public Gold(Cost cost) {
        this.cost = cost.getCost();
    }

    Gold(int cost) {
        this.cost = cost;
    }

    public enum Cost {
        _500(500),
        _400(400),
        _300(300),
        _250(250);

        @Getter final int cost;

        Cost(int cost) {
            this.cost = cost;
        }
    }
}
