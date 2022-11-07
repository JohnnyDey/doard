package com.jdey.board.view.content;

import com.jdey.board.controller.GameHolder;
import com.jdey.board.event.PlayCardEvent;
import com.jdey.board.model.Carriage;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@UIScope
public class TrainView extends HorizontalLayout implements ComponentEventListener<PlayCardEvent> {

    @Autowired GameHolder gameHolder;

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        buildTrain();
        ComponentUtil.addListener(attachEvent.getUI(), PlayCardEvent.class, this);
    }

    private void buildTrain() {
        List<Carriage> carriages = gameHolder.getGame().getTrain().getCarriageList();
        int size = carriages.size();
        setPadding(false);
        getStyle().set("display", "inline-flex");
        while (--size > 0) {
            add(new CarriageView(carriages.get(size)));
        }
        add(new Locomotive(carriages.get(0)));
    }

    @Override
    public void onComponentEvent(PlayCardEvent event) {
        getUI().get().access(() -> {
            removeAll();
            buildTrain();
        });
    }
}
