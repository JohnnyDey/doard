package com.jdey.board.model.deck;

import com.jdey.board.model.Carriage;
import com.jdey.board.model.Game;
import com.jdey.board.model.Player;
import com.jdey.board.model.Selectable;
import com.jdey.board.model.Train;
import com.jdey.board.model.characters.Champion;

import java.util.List;

public class Movement extends Card {
    public Movement(Champion owner) {
        super(owner);
    }

    public List<Selectable> getAvailable(Game game, Player player) {
        Train train = game.getTrain();
        Carriage playerCarriage = getPlayerCarriage(game, player);
        int playerIndex = getPlayerIndex(game, player);

        List<Carriage> selectables = playerCarriage.isRoof() ? getSublist(train.getRoofsList(), playerIndex, 3)
                : getSublist(train.getCarriageList(), playerIndex, 1);
        selectables.remove(playerCarriage);
        return selectables.stream().map(Selectable.class::cast).toList();
    }

    public void action(Game game, Selectable selectable) {
        Champion champion = game.getActive().getChampion();
        getPlayerCarriage(game, game.getActive()).removeToken(champion);
        ((Carriage) selectable).addToken(champion);
    }
}
