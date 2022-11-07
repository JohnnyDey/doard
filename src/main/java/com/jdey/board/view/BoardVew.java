package com.jdey.board.view;

import com.jdey.board.controller.GameRepository;
import com.jdey.board.controller.GameHolder;
import com.jdey.board.event.GameApplicationListener;
import com.jdey.board.model.Game;
import com.jdey.board.view.content.TrainScroller;
import com.jdey.board.view.decks.DecksView;
import com.jdey.board.view.hand.Hand;
import com.jdey.board.view.top.TopPanel;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.ArrayList;
import java.util.List;

import static com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment.CENTER;

@Route("game/:gameId")
public class BoardVew extends VerticalLayout implements BeforeEnterObserver {

    @Autowired private GameRepository gameRepository;
    @Autowired private GameApplicationListener gameApplicationListener;
    @Autowired private GameHolder gameHolder;

    //Model elements:
    @Autowired private TopPanel topPanel;
    @Autowired private Hand hand;
    @Autowired private TrainScroller trainScroller;
    @Autowired private DecksView decks;
    @Autowired private ChooseVew chooseVew;


    public BoardVew() {
        setPadding(true);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        if (gameHolder.getGame() != null) {
            gameApplicationListener.register(gameHolder.getId());
            if (isInGame()) {
                createGameContent();
            } else {
                add(chooseVew);
            }
        }
    }

    private void createGameContent() {
        UI.getCurrent().getPage().executeJs("const scrollContainer = document.getElementById('scroller');" +
                "scrollContainer.addEventListener('wheel', (evt) => {" +
                "    evt.preventDefault();" +
                "    scrollContainer.scrollLeft += evt.deltaY;" +
                "});");
        add(topPanel);
        add(trainScroller);
        add(decks);
        add(hand);
        setDefaultHorizontalComponentAlignment(CENTER);
    }

    private boolean isInGame() {
        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        Game game = gameHolder.getGame();
        return game.getPlayers().stream().anyMatch(p -> sessionId.equals(p.getSessionId()));
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        String gameId = event.getRouteParameters().get("gameId").orElse(null);
        Game game = gameRepository.getGame(gameId);
        if (game != null) {
            gameHolder.setGame(game);
        } else {
            UI.getCurrent().getPage().setLocation("/");
        }
    }
}
