package com.jdey.board.view.content;

import com.google.common.collect.Lists;
import com.jdey.board.controller.GameHolder;
import com.jdey.board.event.PlayCardEvent;
import com.jdey.board.model.Carriage;
import com.jdey.board.model.Game;
import com.jdey.board.model.Selectable;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@UIScope
public class TrainCarriageView extends HorizontalLayout implements ComponentEventListener<PlayCardEvent> {

    @Autowired protected GameHolder gameHolder;

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        buildTrain();
        ComponentUtil.addListener(attachEvent.getUI(), PlayCardEvent.class, this);
    }

    private void buildTrain() {
        int size = getCarriages().size();
        setPadding(false);
        getStyle().set("display", "inline-flex");
        List<Selectable> availables = getAvailables();
        while (--size > 0) {
            add(createCarriage(size, availables));
        }
        add(createLocomotive(size, availables));
    }

    protected List<Carriage> getCarriages() {
        return gameHolder.getGame().getTrain().getCarriageList();
    }

    protected HorizontalLayout createCarriage(int index, List<Selectable> availables) {
        Carriage carriage = getCarriages().get(index);
        boolean contains = availables.contains(carriage);
        return new CarriageView(carriage, contains, this::selectCarriage, this::selectChampion);
    }

    protected HorizontalLayout createLocomotive(int index, List<Selectable> availables) {
        Carriage carriage = getCarriages().get(index);
        boolean contains = availables.contains(carriage);
        return new Locomotive(carriage, contains, this::selectCarriage, this::selectChampion);
    }

    private List<Selectable> getAvailables() {
        Game game = gameHolder.getGame();
        if (game.isAction() && game.getActive() == game.getMe()) {
            return game.getLastCard().getAvailable(game, game.getActive());
        }
        return Lists.newArrayList();
    }

    protected void selectCarriage(ClickEvent<HorizontalLayout> event) {
        Game game = gameHolder.getGame();
        game.action(game.getMe(), ((CarriageView) event.getSource()).getCarriage());
        gameHolder.publishPlayCardEvent(this);
    }

    protected void selectChampion(ClickEvent<Image> event) {
        Game game = gameHolder.getGame();
        game.action(game.getMe(), ((PersonView) event.getSource()).getChampion());
        gameHolder.publishPlayCardEvent(this);
    }

    @Override
    public void onComponentEvent(PlayCardEvent event) {
        getUI().ifPresent(ui -> ui.access(() -> {
            removeAll();
            buildTrain();
        }));
    }
}
