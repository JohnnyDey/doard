package com.jdey.board.model.tokens;

public abstract class PickUps implements Token {

    public String getSrc() {
        return String.format("img/%s.jpg", getName());
    }

    public String getName() {
        return this.getClass().getSimpleName();
    }
}
