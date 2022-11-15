package com.jdey.board.view.content;

import com.jdey.board.model.Carriage;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class Locomotive extends CarriageView {
    public Locomotive(Carriage carriage, boolean enable, ComponentEventListener<ClickEvent<HorizontalLayout>> listener,
                      ComponentEventListener<ClickEvent<Image>> personClickListener) {
        super(carriage, enable, listener, personClickListener);
        this.getStyle().set("background-image", "url('img/locomotive.jpg')");
        setWidth("230px");
    }
}
