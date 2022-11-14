package com.jdey.board.view.content;

import com.jdey.board.controller.GameRepository;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@UIScope
public class TrainScroller extends Scroller {
    public static final String ID = "scroller";

    @Autowired TrainCarriageView trainCarriageView;
    @Autowired TrainRoofView trainRoofView;

    @Autowired
    public TrainScroller(GameRepository gameRepository) {
        setScrollDirection(ScrollDirection.HORIZONTAL);
        setId(ID);
        setWidthFull();
        setHeight("40%");
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setWidthFull();
        verticalLayout.add(trainRoofView);
        verticalLayout.add(trainCarriageView);
        verticalLayout.setSpacing(false);
        verticalLayout.getStyle().set("align-items", "center");
        setContent(verticalLayout);
    }
}
