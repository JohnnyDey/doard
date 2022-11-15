package com.jdey.board.model.deck;

import com.jdey.board.model.Carriage;
import com.jdey.board.model.Game;
import com.jdey.board.model.Player;
import com.jdey.board.model.Selectable;
import com.jdey.board.model.characters.Champion;
import com.jdey.board.model.tokens.SheriffToken;

import java.util.List;

public class Sheriff extends Card {
    public Sheriff(Champion owner) {
        super(owner);
    }

    @Override
    public List<Selectable> getAvailable(Game game, Player player) {
        List<Carriage> carriageList = game.getTrain().getCarriageList();
        Carriage carriage = getSheriffCarriage(carriageList);
        int sheriffIndex = carriageList.indexOf(carriage);
        List<Carriage> sublist = getSublist(carriageList, sheriffIndex, 1);
        sublist.remove(carriage);
        return sublist.stream().map(Selectable.class::cast).toList();
    }

    @Override
    public void action(Game game, Selectable selectable) {
        Carriage carriage = getSheriffCarriage(game.getTrain().getCarriageList());
        carriage.removeToken(SheriffToken.INSTANCE);
        ((Carriage) selectable).addToken(SheriffToken.INSTANCE);
    }

    private Carriage getSheriffCarriage(List<Carriage> carriageList) {
        return carriageList.stream()
                .filter(c -> c.getTokens(SheriffToken.class).size() > 0)
                .findFirst()
                .get();
    }
}
