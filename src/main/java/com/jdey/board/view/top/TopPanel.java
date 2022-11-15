package com.jdey.board.view.top;

import com.jdey.board.controller.GameHolder;
import com.jdey.board.event.ChooseChampionEvent;
import com.jdey.board.event.PlayCardEvent;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@UIScope
public class TopPanel extends HorizontalLayout {

    @Autowired
    private GameHolder gameHolder;

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        buildPanel();
        ComponentUtil.addListener(attachEvent.getUI(), PlayCardEvent.class, new PlayListener());
        ComponentUtil.addListener(attachEvent.getUI(), ChooseChampionEvent.class, new ChooseListener());
    }

    public void buildPanel() {
        gameHolder.getGame().getPlayers().forEach(player -> {
            boolean isActive = gameHolder.getGame().getActive() == player;
            String name = player.getChampion().getName();
            int size = player.getChampion().getHand().size();
            add(new PlayerInfo(name, size, isActive));
        });
    }

    private void rebuild() {
        getUI().ifPresent(ui -> ui.access(() -> {
            removeAll();
            buildPanel();
        }));
    }

    class PlayListener implements ComponentEventListener<PlayCardEvent> {
        @Override
        public void onComponentEvent(PlayCardEvent event) {
            rebuild();
        }
    }

    class ChooseListener implements ComponentEventListener<ChooseChampionEvent> {
        @Override
        public void onComponentEvent(ChooseChampionEvent event) {
            rebuild();
        }
    }
}
