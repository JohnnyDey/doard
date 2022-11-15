package com.jdey.board.view.content;

import com.jdey.board.model.Carriage;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class CarriageRoof extends CarriageView{
    public CarriageRoof(Carriage carriage, boolean enable,
                        ComponentEventListener<ClickEvent<HorizontalLayout>> listener,
                        ComponentEventListener<ClickEvent<Image>> personClickListener) {
        super(carriage, enable, listener, personClickListener);
        this.getStyle().set("background-image", "url('img/carriage_roof.jpg')");
        setHeight("85px");
    }
}
