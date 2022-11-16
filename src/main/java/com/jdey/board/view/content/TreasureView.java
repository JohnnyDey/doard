package com.jdey.board.view.content;

import com.jdey.board.model.tokens.Gold;
import com.jdey.board.model.tokens.Token;
import com.vaadin.flow.component.html.Image;
import lombok.Getter;

public class TreasureView extends Image {
    @Getter Gold treasure;

    public TreasureView(Gold treasure) {
        super(treasure.getSrc(), String.valueOf(treasure.getCost()));
        setWidth(Token.SIZE);
        setHeight(Token.SIZE);
        this.treasure = treasure;
    }
}
