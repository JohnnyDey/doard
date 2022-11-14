package com.jdey.board.model.deck;

import com.jdey.board.model.Carriage;
import com.jdey.board.model.Game;
import com.jdey.board.model.Player;
import com.jdey.board.model.Selectable;
import com.jdey.board.model.characters.Champion;

import java.util.List;

public class Hit extends Card{
    public Hit(Champion owner) {
        super(owner);
    }

    @Override
    public List<Selectable> getAvailable(Game game, Player player) {
        Carriage playerCarriage = getPlayerCarriage(game.getTrain().getCarriageList(), player);
        return playerCarriage.getTokens(Champion.class).stream().map(Selectable.class::cast).toList();
    }

    @Override
    public void action(Game game, Selectable selectable) {
        //TODO:
    }
}
