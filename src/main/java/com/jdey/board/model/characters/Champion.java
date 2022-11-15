package com.jdey.board.model.characters;

import com.jdey.board.model.Game;
import com.jdey.board.model.Player;
import com.jdey.board.model.Selectable;
import com.jdey.board.model.deck.Bullet;
import com.jdey.board.model.tokens.PickUps;
import com.jdey.board.model.tokens.Token;
import com.jdey.board.model.deck.Card;
import com.jdey.board.model.deck.Hit;
import com.jdey.board.model.deck.Ladder;
import com.jdey.board.model.deck.Movement;
import com.jdey.board.model.deck.PickUp;
import com.jdey.board.model.deck.Sheriff;
import com.jdey.board.model.deck.Shot;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

abstract public class Champion implements Token {
    @Getter private final List<Card> hand = new ArrayList<>();
    @Getter private final List<Bullet> bullets = new LinkedList<>();
    @Getter private final List<Card> deck = new ArrayList<>();
    @Getter private final List<PickUps> treasure = new ArrayList<>();
    private final Random rand = new Random();
    @Getter
    @Setter
    private Player player;

    Champion() {
        deck.add(new Movement(this));
        deck.add(new Movement(this));
        deck.add(new Ladder(this));
        deck.add(new Ladder(this));
        deck.add(new PickUp(this));
        deck.add(new PickUp(this));
        deck.add(new Shot(this));
        deck.add(new Shot(this));
        deck.add(new Hit(this));
        deck.add(new Sheriff(this));
        Collections.shuffle(deck);
        for (int i = 6; i > 0; i--) {
            bullets.add(new Bullet(this, i));
        }
    }

    public void initDraw() {
        draw(getHandCap());
    }

    public void draw(int count) {
        while (count-- > 0) {
            if (deck.size() == 0) break;
            Card card = drawRandom();
            deck.remove(card);
            hand.add(card);
        }
    }

    public void play(Card card, Game game) {
        if (hand.contains(card)) {
            hand.remove(card);
        } else {
            throw new IllegalStateException("Играемая карта не в руке");
        }
    }

    public void action(Game game, Card card, Selectable selectable) {
        card.action(game, selectable);
    }

    private Card drawRandom() {
        return deck.get(rand.nextInt(deck.size()));
    }

    public int getHandCap() {
        return 6;
    }

    public String getName() {
        return this.getClass().getSimpleName();
    }
}
