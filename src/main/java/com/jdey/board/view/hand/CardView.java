package com.jdey.board.view.hand;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;

public class CardView extends Button{
    private static final String HEIGHT = "210px";
    private static final String WIDTH = "150px";

    public CardView(String src, String alt) {
        setIcon(createImg(src, alt));
        setHeight(HEIGHT);
        setWidth(WIDTH);
    }

    protected Image createImg(String src, String alt) {
        Image img = new Image(src, alt);
        img.setHeight(HEIGHT);
        img.setWidth(WIDTH);
        return img;
    }

}
