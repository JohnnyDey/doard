package com.jdey.board.model;

import com.google.common.collect.Lists;
import com.jdey.board.model.characters.Champion;
import com.jdey.board.model.tokens.Case;
import com.jdey.board.model.tokens.Gold;
import com.jdey.board.model.tokens.Ruby;
import com.jdey.board.model.tokens.SheriffToken;
import com.jdey.board.model.tokens.Token;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Train {
    private final List<Carriage> roofsList = new ArrayList<>();
    private final List<Carriage> carriageList = new ArrayList<>();

    public Train() {
        addCarriage(Lists.newArrayList(new Case()));
        carriageList.get(0).addToken(SheriffToken.INSTANCE);
    }

    private void addCarriage(List<Token> treasures) {
        roofsList.add(new CarriageRoof());
        carriageList.add(new Carriage(treasures));
    }

    public void addCarriage() {
        List<Token> treasures = new ArrayList<>();
        //todo: make random
        treasures.add(new Ruby());
        treasures.add(new Gold(Gold.Cost._300));
        addCarriage(treasures);
    }

    public void placePlayers(List<Player> players) {
        carriageList.forEach(Carriage::removePlayers);
        Carriage last = carriageList.get(carriageList.size() - 1);
        Carriage preLast = carriageList.get(carriageList.size() - 2);
        for (int i = 0; i < players.size(); i++) {
            Champion champion = players.get(i).getChampion();
            if (i % 2 == 0) {
                last.addToken(champion);
            } else {
                preLast.addToken(champion);
            }
        }
    }

    public int size() {
        return roofsList.size();
    }

}
