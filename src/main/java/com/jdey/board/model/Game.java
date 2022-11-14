package com.jdey.board.model;

import com.jdey.board.model.characters.Champion;
import com.jdey.board.model.deck.Card;
import com.vaadin.flow.component.UI;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
public class Game {
    private final Train train = new Train();
    private final List<Player> players = new ArrayList<>();
    private final List<Card> played = new LinkedList<>();
    private final String id = UUID.randomUUID().toString();
    private Status status = Status.NEW;
    private int active = 0;
    private int turn = 1;
    private int turns = 2;

    public void joinAs(Class<? extends Champion> championClass) {
        try {
            Champion champion = championClass.getConstructor().newInstance();
            Player player = new Player(champion);
            champion.setPlayer(player);
            players.add(player);
            train.addCarriage();
            train.placePlayers(players);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void action(Player player, Selectable selectable) {
        Card lastCard = getLastCard();
        if (isAction() && getActive() == player && lastCard.getAvailable(this, player).contains(selectable)) {
            player.getChampion().action(this, lastCard, selectable);
            played.remove(lastCard);
            changeActiveOnAction();
            if (played.size() == 0) {
                status = Status.PLANNING;
            }
        }
    }

    public void draw(Player player) {
        if (isPlanning() && getActive() == player) {
            player.getChampion().draw(3);
            nextPlayer();
        }
    }

    public void play(Player player, Card card) {
        if (isPlanning() && getActive() == player) {
            player.getChampion().play(card, this);
            played.add(card);
            nextPlayer();
        }
    }

    private void nextPlayer() {
        if (++active >= players.size()) {
            active = 0;
            turn++;
        }
        if (turn > turns) {
            status = Status.ACTION;
            played.forEach(card -> card.setOpen(true));
            Collections.reverse(played);
            changeActiveOnAction();
        }
    }

    private void changeActiveOnAction() {
        active = players.indexOf(getLastCard().getOwner().getPlayer());
    }

    public Card getLastCard() {
        if (getPlayed().size() == 0) {
            return Card.EMPTY_CARD;
        }
        return getPlayed().get(getPlayed().size() - 1);
    }

    public Player getActive() {
        return players.get(active);
    }

    public Player getMe() {
        return players.stream().filter(p -> {
            String sessionId = UI.getCurrent().getSession().getPushId();
            return Objects.equals(p.getVaadinId(), sessionId);
        }).findFirst().orElseThrow(IllegalStateException::new);
    }

    public boolean isNew() {
        return status == Status.NEW;
    }

    public boolean isPlanning() {
        return status == Status.PLANNING;
    }

    public boolean isAction() {
        return status == Status.ACTION;
    }

    public void start() {
        status = Status.PLANNING;
        players.stream().map(Player::getChampion).forEach(Champion::initDraw);
    }

    private enum Status {
        NEW,
        PLANNING,
        ACTION,
        END
    }
}
