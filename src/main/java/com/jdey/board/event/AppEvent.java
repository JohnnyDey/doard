package com.jdey.board.event;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class AppEvent extends ApplicationEvent {
    @Getter
    ComponentEvent<Component> innerEvent;
    public AppEvent(String source, ComponentEvent<Component> innerEvent) {
        super(source);
        this.innerEvent = innerEvent;
    }
}
