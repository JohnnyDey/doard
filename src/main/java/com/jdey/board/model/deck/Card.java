package com.jdey.board.model.deck;

import com.jdey.board.model.Carriage;
import com.jdey.board.model.Game;
import com.jdey.board.model.Player;
import com.jdey.board.model.Selectable;
import com.jdey.board.model.characters.Champion;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class Card {

    private boolean isOpen = true;
    private Champion owner;

    private Card() {
    }

    protected Card(Champion owner) {
        this.owner = owner;
    }

    public List<Selectable> getAvailable(Game game, Player player) {
        return new ArrayList<>();
    };
    public void action(Selectable selectable){

    }
//    public abstract List<Selectable> getAvailable(Game game, Player player);

    public String getSrc() {
        if (isOpen) {
            String ownerName = owner.getName().toLowerCase();
            String cardName = getName().toLowerCase();
            return String.format("img/%s/%s.jpg", ownerName, cardName);
        } else {
            return COVER_SRC;
        }
    }

    public String getName() {
        return isOpen ? this.getClass().getSimpleName() : "Карта";
    }

    protected int getPlayerIndex(List<Carriage> carriageList, Player player) {
        Carriage carriage = getPlayerCarriage(carriageList, player);
        return carriageList.indexOf(carriage);
    }

    protected Carriage getPlayerCarriage(List<Carriage> carriageList, Player player) {
        return carriageList.stream()
                .filter(c -> c.getTokens(Champion.class).contains(player.getChampion()))
                .findFirst()
                .get();
    }

    public static final String COVER_SRC = "img/cover.jpg";
    public static final Card EMPTY_CARD = new Card() {
        @Override
        public List<Selectable> getAvailable(Game game, Player player) {
            throw new NotImplementedException();
        }

        @Override
        public String getSrc() {
            return COVER_SRC;
        }

        @Override
        public String getName() {
            return "Пусто";
        }
    };
}
