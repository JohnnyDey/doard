package com.jdey.board.event;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;

public class ChooseChampionEvent extends ComponentEvent<Component> {
    public ChooseChampionEvent(Component source) {
        super(source, false);
    }
}
