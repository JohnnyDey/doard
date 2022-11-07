package com.jdey.board.model;

import com.jdey.board.model.characters.Champion;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Train {
    private final List<Carriage> carriageList = new ArrayList<>();

    public Train() {
        addCarriage();
    }

    public void addCarriage() {
        carriageList.add(new Carriage());
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

}
