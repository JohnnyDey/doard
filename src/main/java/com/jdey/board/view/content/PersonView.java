package com.jdey.board.view.content;

import com.jdey.board.model.characters.Champion;
import com.jdey.board.model.tokens.Token;
import com.vaadin.flow.component.html.Image;
import lombok.Getter;

public class PersonView extends Image {
    @Getter Champion champion;

    public PersonView(String name, Champion champion) {
        super("img/ava/" + name.toLowerCase() + ".png", name);
        setWidth(Token.SIZE);
        setHeight(Token.SIZE);
        this.champion = champion;
    }
}
