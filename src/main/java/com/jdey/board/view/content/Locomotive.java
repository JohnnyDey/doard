package com.jdey.board.view.content;

import com.jdey.board.model.Carriage;

public class Locomotive extends CarriageView {
    public Locomotive(Carriage carriage) {
        super(carriage);
        this.getStyle().set("background-image", "url('img/locomotive.png')");
        setWidth("230px");
    }
}
