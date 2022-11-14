package com.jdey.board.model.deck;

import com.jdey.board.model.Carriage;
import com.jdey.board.model.Game;
import com.jdey.board.model.Player;
import com.jdey.board.model.Selectable;
import com.jdey.board.model.Train;
import com.jdey.board.model.characters.Champion;

import java.util.List;

public class Ladder extends Card {
    public Ladder(Champion owner) {
        super(owner);
    }

    @Override
    public List<Selectable> getAvailable(Game game, Player player) {
        Train train = game.getTrain();
        boolean isInRoof = isInRoof(game, player);
        int index = isInRoof ? getPlayerIndex(train.getRoofsList(), player)
                : getPlayerIndex(train.getCarriageList(), player);
        Carriage carriage = isInRoof ? train.getCarriageList().get(index) : train.getRoofsList().get(index);
        return List.of(carriage);
    }

    @Override
    public void action(Game game, Selectable selectable) {
        Carriage carriage = (Carriage) selectable;
        Player active = game.getActive();
        Carriage playerCarriage = getPlayerCarriage(game, active);
        playerCarriage.removeToken(active.getChampion());
        carriage.addToken(active.getChampion());
    }
}
