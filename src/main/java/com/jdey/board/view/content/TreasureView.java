package com.jdey.board.view.content;

import com.jdey.board.model.tokens.Gold;
import com.vaadin.flow.component.html.Image;
import lombok.Getter;

public class TreasureView extends Image {
    @Getter Gold treasure;

    public TreasureView(Gold treasure) {
        //todo
        super("stub", String.valueOf(treasure.getCost()));
        Image image = new Image();
        image.setWidth("30px");
        image.setHeight("30px");
        this.treasure = treasure;
    }
}
