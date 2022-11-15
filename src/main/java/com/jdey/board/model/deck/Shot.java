package com.jdey.board.model.deck;

import com.google.common.collect.Lists;
import com.jdey.board.model.Carriage;
import com.jdey.board.model.Game;
import com.jdey.board.model.Player;
import com.jdey.board.model.Selectable;
import com.jdey.board.model.Train;
import com.jdey.board.model.characters.Champion;

import java.util.List;

public class Shot extends Card {
    public Shot(Champion owner) {
        super(owner);
    }

    @Override
    public List<Selectable> getAvailable(Game game, Player player) {
        if (player.getChampion().getBullets().size() > 0) {
            Train train = game.getTrain();
            Carriage playerCarriage = getPlayerCarriage(game, player);
            int playerIndex = getPlayerIndex(game, player);

            List<Carriage> selectables = playerCarriage.isRoof()
                    ? getSublist(train.getRoofsList(), playerIndex, Integer.MAX_VALUE)
                    : getSublist(train.getCarriageList(), playerIndex, 1);
            selectables.remove(playerCarriage);
            return selectables.stream()
                    .filter(c -> c.getTokens(Champion.class).size() > 0)
                    .flatMap(c -> c.getTokens(Champion.class).stream())
                    .map(Selectable.class::cast).toList();
        } else {
            return Lists.newArrayList();
        }
    }

    @Override
    public void action(Game game, Selectable selectable) {
        Champion activeChampion = game.getMe().getChampion();
        Bullet bullet = activeChampion.getBullets().remove(0);
        ((Champion) selectable).getDeck().add(bullet);
    }
}
