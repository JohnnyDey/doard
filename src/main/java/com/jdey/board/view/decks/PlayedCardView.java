package com.jdey.board.view.decks;

import com.jdey.board.view.hand.CardView;

public class PlayedCardView extends CardView {
    public PlayedCardView(String src, String alt) {
        super(src, alt);
        setHeight("50%");
        getStyle().set("transform", "rotate(90deg)")
                .set("margin", "0px 25px");
        setEnabled(false);
    }

}
