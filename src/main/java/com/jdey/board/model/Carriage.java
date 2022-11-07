package com.jdey.board.model;

import com.jdey.board.model.characters.Champion;
import com.jdey.board.model.tokens.Token;

import java.util.ArrayList;
import java.util.List;

public class Carriage implements Selectable {
    private List<Token> object = new ArrayList<>();

    public Carriage(List<Token> object) {
        this.object = object;
    }

    public Carriage() {
    }

    public void removePlayers() {
        getTokens(Champion.class).forEach(object::remove);
    }

    public void addToken(Token token) {
        object.add(token);
    }

    public <T extends Token> List<T> getTokens(Class<T> clazz) {
        return object.stream().filter(clazz::isInstance).map(clazz::cast).toList();
    }
}
