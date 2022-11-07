package com.jdey.board.view;

import com.jdey.board.controller.GameRepository;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route
public class MainView extends VerticalLayout {

    @Autowired
    GameRepository gameRepository;

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        Button create = new Button("Создать");
        create.addClickListener((event) -> {
            String gameId = gameRepository.createGame();
            UI.getCurrent().getPage().setLocation("/game/" + gameId);
        });
        add(create);
    }
}
