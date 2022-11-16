package com.jdey.board.view;

import com.jdey.board.controller.GameHolder;
import com.jdey.board.event.ChooseChampionEvent;
import com.jdey.board.model.Game;
import com.jdey.board.model.characters.Belle;
import com.jdey.board.model.characters.Champion;
import com.jdey.board.model.characters.Cheyenne;
import com.jdey.board.model.characters.Django;
import com.jdey.board.model.characters.Doc;
import com.jdey.board.model.characters.Ghost;
import com.jdey.board.model.characters.Tuco;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serial;

@Component
@UIScope
public class ChooseVew extends VerticalLayout implements ComponentEventListener<ChooseChampionEvent> {
    @Serial private static final long serialVersionUID = 3965820581499572715L;

    @Autowired private GameHolder gameHolder;

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        buildButtons();
        ComponentUtil.addListener(attachEvent.getUI(), ChooseChampionEvent.class, this);
    }

    private void buildButtons() {
        if(gameHolder.getGame().isNew()) {
            add(createButton("Belle", Belle.class));
            add(createButton("Cheyenne", Cheyenne.class));
            add(createButton("Django", Django.class));
            add(createButton("Doc", Doc.class));
            add(createButton("Ghost", Ghost.class));
            add(createButton("Tuco", Tuco.class));
        } else {
            add(new H2("Нельзя войти. Игра уже началась."));
        }
    }

    private Button createButton(String label, Class<? extends Champion> championClass) {
        Button button = new Button("Войти как " + label);
        button.setWidth("100%");
        Game game = gameHolder.getGame();
        button.addClickListener((event) -> {
            game.joinAs(championClass);
            gameHolder.publishChooseChampionEvent(this);
            UI.getCurrent().getPage().reload();
        });
        button.setEnabled(game.getPlayers()
                .stream()
                .noneMatch(player -> player.getChampion().getClass() == championClass));
        return button;
    }

    @Override
    public void onComponentEvent(ChooseChampionEvent event) {
        getUI().ifPresent(ui -> ui.access(() -> {
            removeAll();
            buildButtons();
        }));
    }
}
