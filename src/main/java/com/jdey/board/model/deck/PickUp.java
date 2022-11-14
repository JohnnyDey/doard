package com.jdey.board.model.deck;

import com.jdey.board.model.Carriage;
import com.jdey.board.model.Game;
import com.jdey.board.model.Player;
import com.jdey.board.model.Selectable;
import com.jdey.board.model.characters.Champion;
import com.jdey.board.model.tokens.PickUps;
import com.jdey.board.model.tokens.Token;

import java.util.List;

public class PickUp extends Card {
    public PickUp(Champion owner) {
        super(owner);
    }

    public List<Selectable> getAvailable(Game game, Player player) {
        Carriage carriage = getPlayerCarriage(game.getTrain().getCarriageList(), player);
        return carriage.getTokens(PickUps.class).stream().map(Selectable.class::cast).toList();
    }

    @Override
    public void action(Game game, Selectable selectable) {
        Player player = game.getActive();
        getPlayerCarriage(game.getTrain().getCarriageList(), player).removeToken((Token) selectable);
        player.getChampion().getTreasure().add((PickUps) selectable);
    }
}
