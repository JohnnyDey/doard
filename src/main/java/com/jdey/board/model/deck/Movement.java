package com.jdey.board.model.deck;

import com.jdey.board.model.Carriage;
import com.jdey.board.model.Game;
import com.jdey.board.model.Player;
import com.jdey.board.model.Selectable;
import com.jdey.board.model.characters.Champion;

import java.util.ArrayList;
import java.util.List;

public class Movement extends Card {
    public Movement(Champion owner) {
        super(owner);
    }

    public List<Selectable> getAvailable(Game game, Player player) {
        List<Carriage> carriageList = game.getTrain().getCarriageList();
        int playerIndex = getPlayerIndex(carriageList, player);

        ArrayList<Selectable> selectables = new ArrayList<>();
        addIfInBound(playerIndex - 1, carriageList, selectables);
        addIfInBound(playerIndex + 1, carriageList, selectables);
        return selectables;
    }

    private void addIfInBound(int deltaIndex, List<Carriage> carriageList, ArrayList<Selectable> selectables) {
        if (deltaIndex >= 0 && deltaIndex < carriageList.size()) {
            selectables.add(carriageList.get(deltaIndex));
        }
    }
}
