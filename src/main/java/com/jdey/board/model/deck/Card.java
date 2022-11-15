package com.jdey.board.model.deck;

import com.google.common.collect.Lists;
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

    public abstract List<Selectable> getAvailable(Game game, Player player);

    public abstract void action(Game game, Selectable selectable);

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
        return carriage != null ? carriageList.indexOf(carriage) : -1;
    }

    protected int getPlayerIndex(Game game, Player player) {
        int playerIndex = getPlayerIndex(game.getTrain().getCarriageList(), player);
        return playerIndex >= 0 ? playerIndex : getPlayerIndex(game.getTrain().getRoofsList(), player);
    }

    protected Carriage getPlayerCarriage(List<Carriage> carriageList, Player player) {
        return carriageList.stream()
                .filter(c -> c.getTokens(Champion.class).contains(player.getChampion()))
                .findFirst()
                .orElse(null);
    }

    protected Carriage getPlayerCarriage(Game game, Player player) {
        Carriage carriage = getPlayerCarriage(game.getTrain().getCarriageList(), player);
        if (carriage == null) {
            carriage = getPlayerCarriage(game.getTrain().getRoofsList(), player);
        }
        return carriage;
    }


    protected boolean isInRoof(Game game, Player player) {
        return getPlayerCarriage(game.getTrain().getRoofsList(), player) != null;
    }

    protected List<Carriage> getSublist(List<Carriage> list, int playerIndex, int delta) {
        List<Carriage> subList = list.subList(Math.max(0, playerIndex - delta),
                Math.min(list.size(), playerIndex + delta + 1));
        return new ArrayList<>(subList);
    }

    public static final String COVER_SRC = "img/cover.jpg";
    public static final Card EMPTY_CARD = new Card() {
        @Override
        public List<Selectable> getAvailable(Game game, Player player) {
            return Lists.newArrayList();
        }

        @Override
        public void action(Game game, Selectable selectable) {
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
