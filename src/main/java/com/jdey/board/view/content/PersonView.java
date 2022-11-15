package com.jdey.board.view.content;

import com.jdey.board.model.characters.Champion;
import com.vaadin.flow.component.html.Image;
import lombok.Getter;

public class PersonView extends Image {
    @Getter Champion champion;

    public PersonView(String name, Champion champion) {
        super("img/ava/" + name.toLowerCase() + ".png", name);
        Image image = new Image();
        image.setWidth("30px");
        image.setHeight("30px");
        this.champion = champion;
    }
}
