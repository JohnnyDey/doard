package com.jdey.board.view.content;

import com.jdey.board.controller.GameRepository;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@UIScope
public class TrainScroller extends Scroller {
    public static final String ID = "scroller";

    @Autowired TrainView trainView;

    @Autowired
    public TrainScroller(GameRepository gameRepository) {
        setScrollDirection(ScrollDirection.HORIZONTAL);
        setId(ID);
        setWidthFull();
        setHeight("40%");
        getStyle().set("text-align", "center");
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        setContent(trainView);
    }
}
