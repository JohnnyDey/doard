package com.jdey.board.view.decks;

import com.jdey.board.controller.GameHolder;
import com.jdey.board.event.PlayCardEvent;
import com.jdey.board.model.Game;
import com.jdey.board.model.deck.Card;
import com.vaadin.componentfactory.Tooltip;
import com.vaadin.componentfactory.TooltipPosition;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@UIScope
public class DecksView extends HorizontalLayout implements ComponentEventListener<PlayCardEvent> {
    @Autowired GameHolder gameHolder;

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        buildDecks();
        ComponentUtil.addListener(attachEvent.getUI(), PlayCardEvent.class, this);
    }

    private void buildDecks() {
        Card lastCard = gameHolder.getGame().getLastCard();
        add(new PlayedCardView(lastCard.getSrc(), lastCard.getName()));

        DeckCardView deckCardView = new DeckCardView();
        deckCardView.addClickListener((event -> {
            gameHolder.getGame().draw(gameHolder.getGame().getMe());
            gameHolder.publishPlayCardEvent(this);
        }));
        add(deckCardView, createDeckTooltip(deckCardView));
    }

    private Tooltip createDeckTooltip(DeckCardView deckCardView) {
        Tooltip tooltip = new Tooltip();
        int deckSize = gameHolder.getGame().getMe().getChampion().getDeck().size();
        tooltip.add(new Paragraph("В колоде осталось карт: " + deckSize));
        tooltip.attachToComponent(deckCardView);
        tooltip.setPosition(TooltipPosition.RIGHT);
        return tooltip;
    }

    @Override
    public void onComponentEvent(PlayCardEvent event) {
        getUI().get().access(() -> {
            removeAll();
            buildDecks();
        });
    }
}
