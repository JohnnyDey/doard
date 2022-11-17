package com.jdey.board.model;

import java.util.List;

import static com.jdey.board.model.Round.Turn.*;

public class Rounds {
    private static final Round SMALL_1 = new Round(SIMPLE, SIMPLE, BLIND, BACKWARD);
    private static final Round SMALL_2 = new Round(SIMPLE, SIMPLE, BLIND, SIMPLE, SIMPLE);
    private static final Round SMALL_3 = new Round(SIMPLE, SIMPLE, SIMPLE, SIMPLE);
    private static final Round SMALL_4 = new Round(SIMPLE, DOUBLE, SIMPLE);
    private static final Round SMALL_5 = new Round(SIMPLE, BLIND, SIMPLE, SIMPLE);
    private static final Round SMALL_6 = new Round(SIMPLE, BLIND, SIMPLE, BLIND, SIMPLE);
    private static final Round SMALL_7 = new Round(SIMPLE, BLIND, DOUBLE, SIMPLE);

    private static final Round BIG_1 = new Round(SIMPLE, SIMPLE, BACKWARD);
    private static final Round BIG_2 = new Round(SIMPLE, BLIND, SIMPLE, BACKWARD);
    private static final Round BIG_3 = new Round(SIMPLE, BLIND, SIMPLE, SIMPLE);
    private static final Round BIG_4 = new Round(SIMPLE, DOUBLE);
    private static final Round BIG_5 = new Round(SIMPLE, BLIND, SIMPLE);
    private static final Round BIG_6 = new Round(SIMPLE, BLIND, SIMPLE, BLIND);
    private static final Round BIG_7 = new Round(SIMPLE, DOUBLE, BACKWARD);

    public static List<Round> getSmallDeck() {
        return List.of(SMALL_1, SMALL_2, SMALL_3, SMALL_4, SMALL_5, SMALL_6, SMALL_7);
    }

    public static List<Round> getBigDeck() {
        return List.of(BIG_1, BIG_2, BIG_3, BIG_4, BIG_5, BIG_6, BIG_7);
    }
}
