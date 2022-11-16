package com.jdey.board.view.content;

import com.jdey.board.model.Carriage;

public class LocomotiveRoof extends Locomotive {
    public LocomotiveRoof(Carriage carriage, boolean enable, TrainCarriageView.ButtonClickListener buttonClickListener) {
        super(carriage, enable, buttonClickListener);
        this.getStyle().set("background-image", "url('img/locomotive_roof.jpg')");
        setHeight("85px");
    }
}
