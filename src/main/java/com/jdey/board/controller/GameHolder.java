package com.jdey.board.controller;

import com.jdey.board.event.AppEvent;
import com.jdey.board.event.ChooseChampionEvent;
import com.jdey.board.event.PlayCardEvent;
import com.jdey.board.model.Game;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
@Getter
@Setter
public class GameHolder {
    private Game game;
    @Autowired private ApplicationEventPublisher applicationEventPublisher;

    public String getId() {
        return game.getId();
    }

    public void publishPlayCardEvent(com.vaadin.flow.component.Component source) {
        applicationEventPublisher.publishEvent(new AppEvent(getId(), new PlayCardEvent(source)));
    }

    public void publishChooseChampionEvent(com.vaadin.flow.component.Component source) {
        applicationEventPublisher.publishEvent(new AppEvent(getId(), new ChooseChampionEvent(source)));
    }

}
