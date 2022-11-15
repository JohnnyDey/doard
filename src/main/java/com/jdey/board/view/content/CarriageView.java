package com.jdey.board.view.content;

import com.jdey.board.model.Carriage;
import com.jdey.board.model.characters.Champion;
import com.jdey.board.model.tokens.Gold;
import com.jdey.board.model.tokens.SheriffToken;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import lombok.Getter;

import java.util.List;

import static com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment.AUTO;
import static com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment.END;


public class CarriageView extends HorizontalLayout {
    @Getter
    private final Carriage carriage;

    public CarriageView(Carriage carriage, boolean enable,
                        ComponentEventListener<ClickEvent<HorizontalLayout>> carriageClickListener,
                        ComponentEventListener<ClickEvent<Image>> personClickListener
    ) {
        this.carriage = carriage;
        getStyle().set("background-image", "url('img/carriage.jpg')")
                .set("background-size", "contain")
                .set("background-repeat", "no-repeat");
        if (enable) {
            getStyle().set("border", "2px dotted DarkOrange");
            addClickListener(carriageClickListener);
        }

        setWidth("300px");
        setHeight("100px");
        setChampions(carriage.getTokens(Champion.class), carriage.getTokens(SheriffToken.class), personClickListener);
        setTreasure(carriage.getTokens(Gold.class));
        setDefaultVerticalComponentAlignment(END);
    }

    protected void setChampions(List<Champion> champions, List<SheriffToken> sheriffTokenList, ComponentEventListener<ClickEvent<Image>> personClickListener) {
        HorizontalLayout trainChampions = new HorizontalLayout();
        champions.forEach(champion -> {
            Image image = new PersonView(champion.getName(), champion);
            image.addClickListener(personClickListener);
            trainChampions.add(image);
        });
        if (sheriffTokenList.size() == 1) {
            Image image = new PersonView("Sheriff", null);
            trainChampions.add(image);
        }
        trainChampions.setWidth("100%");
        trainChampions.setHeight("40%");
        trainChampions.setAlignItems(AUTO);
        add(trainChampions);
    }

    protected void setTreasure(List<Gold> treasures) {
        VerticalLayout verticalLayout = new VerticalLayout();
        treasures.forEach(treasure -> {
            Image image = new TreasureView(treasure);
            verticalLayout.add(image);
        });
        add(verticalLayout);
    }

}
