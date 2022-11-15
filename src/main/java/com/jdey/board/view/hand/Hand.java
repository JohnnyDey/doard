package com.jdey.board.view.hand;

import com.jdey.board.controller.GameHolder;
import com.jdey.board.event.PlayCardEvent;
import com.jdey.board.model.Game;
import com.jdey.board.model.Player;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@UIScope
public class Hand extends HorizontalLayout implements ComponentEventListener<PlayCardEvent> {

    @Autowired private GameHolder gameHolder;

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        ComponentUtil.addListener(attachEvent.getUI(), PlayCardEvent.class, this);
        if (gameHolder.getGame().isNew()) {
            buildStarButton();
        } else {
            buildHand();
        }
        setSpacing(false);
    }

    private void buildHand() {
        Game game = gameHolder.getGame();
        Player me = game.getMe();
        boolean enableCard = game.isPlanning() && game.getActive() == me;
        me.getChampion().getHand().forEach(card -> {
            CardView cardView = new CardView(card.getSrc(), card.getName());
            cardView.setEnabled(enableCard);
            cardView.addClickListener(event -> {
                game.play(card.getOwner().getPlayer(), card);
                gameHolder.publishPlayCardEvent(this);
            });
            add(cardView);
        });
    }

    private void buildStarButton() {
        add(new Button("Начать игру", (event) -> {
            gameHolder.getGame().start();
            gameHolder.publishPlayCardEvent(this);
        }));
    }

    @Override
    public void onComponentEvent(PlayCardEvent event) {
        getUI().ifPresent(ui -> ui.access(() -> {
            removeAll();
            buildHand();
        }));
    }
}
