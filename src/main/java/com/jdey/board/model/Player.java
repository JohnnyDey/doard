package com.jdey.board.model;

import com.jdey.board.model.characters.Champion;
import com.vaadin.flow.component.UI;
import lombok.Getter;
import org.springframework.web.context.request.RequestContextHolder;

public class Player {
    @Getter private final Champion champion;
    @Getter private final String sessionId;
    @Getter private final String vaadinId;


    public Player(Champion champion) {
        this.champion = champion;
        this.sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        this.vaadinId = UI.getCurrent().getSession().getPushId();
    }


}
