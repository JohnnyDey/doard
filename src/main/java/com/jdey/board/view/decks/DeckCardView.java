package com.jdey.board.view.decks;

import com.jdey.board.view.hand.CardView;

public class DeckCardView extends CardView {
    public DeckCardView() {
        super("img/cover.jpg", "Взять карту");
        getStyle().set("margin", "0px 25px")
                .set("transform", "rotate(90deg)");
    }
}
