package com.jdey.board.model.deck;

import com.google.common.collect.Lists;
import com.jdey.board.model.Game;
import com.jdey.board.model.Player;
import com.jdey.board.model.Selectable;
import com.jdey.board.model.characters.Champion;

import java.util.List;

public class Shot extends Card{
    public Shot(Champion owner) {
        super(owner);
    }

    @Override
    public List<Selectable> getAvailable(Game game, Player player) {
        return Lists.newArrayList();
    }

    @Override
    public void action(Game game, Selectable selectable) {

    }
}
