package com.jdey.board.controller;

import com.jdey.board.model.Game;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class GameRepository {
    private Map<String, Game> games = new HashMap<>();

    public Game getGame(String gameId) {
        return games.get(gameId);
    }

    public String createGame() {
        Game game = new Game();
        games.put(game.getId(), game);
        return game.getId();
    }
}
