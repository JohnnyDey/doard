package com.jdey.board.event;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;

public class PlayCardEvent extends ComponentEvent<Component> {

    public PlayCardEvent(Component source) {
        super(source, false);
    }
}
