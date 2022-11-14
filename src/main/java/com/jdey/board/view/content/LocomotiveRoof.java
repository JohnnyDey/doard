package com.jdey.board.view.content;

import com.jdey.board.model.Carriage;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class LocomotiveRoof extends Locomotive {
    public LocomotiveRoof(Carriage carriage, boolean enable, ComponentEventListener<ClickEvent<HorizontalLayout>> listener) {
        super(carriage, enable, listener);
        this.getStyle().set("background-image", "url('img/locomotive_roof.jpg')");
        setHeight("85px");
    }
}
