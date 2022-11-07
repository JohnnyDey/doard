package com.jdey.board.model.characters;

import com.jdey.board.model.Game;
import com.jdey.board.model.deck.Card;

public class Ghost extends Champion{

    @Override
    public void play(Card card, Game game) {
        if(game.getTurn() == 1) {
            card.setOpen(false);
        }
        super.play(card, game);
    }
}
