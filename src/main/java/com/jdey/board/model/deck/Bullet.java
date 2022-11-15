package com.jdey.board.model.deck;

import com.jdey.board.model.Game;
import com.jdey.board.model.Player;
import com.jdey.board.model.Selectable;
import com.jdey.board.model.characters.Champion;
import org.apache.commons.lang3.NotImplementedException;

import java.util.List;

public class Bullet extends Card{
    private final int order;

    public Bullet(Champion owner, int order) {
        super(owner);
        this.order = order;
    }

    @Override
    public String getName() {
        return super.getName() + order;
    }

    @Override
    public List<Selectable> getAvailable(Game game, Player player) {
        throw new NotImplementedException();
    }

    @Override
    public void action(Game game, Selectable selectable) {
        throw new NotImplementedException();
    }
}
