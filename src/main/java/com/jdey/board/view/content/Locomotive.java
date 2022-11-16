package com.jdey.board.view.content;

import com.jdey.board.model.Carriage;

public class Locomotive extends CarriageView {
    public Locomotive(Carriage carriage, boolean enable, TrainCarriageView.ButtonClickListener buttonClickListener) {
        super(carriage, enable, buttonClickListener);
        this.getStyle().set("background-image", "url('img/locomotive.jpg')");
        setWidth("230px");
    }
}
