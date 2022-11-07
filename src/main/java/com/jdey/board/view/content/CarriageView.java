package com.jdey.board.view.content;

import com.jdey.board.model.Carriage;
import com.jdey.board.model.characters.Champion;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import java.util.List;

import static com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment.*;

public class CarriageView extends HorizontalLayout {

    public CarriageView(Carriage carriage) {
        this.getStyle().set("background-image", "url('img/carriage.png')");
        this.getStyle().set("background-size", "contain");

        setWidth("275px");
        setHeight("130px");
        setChampions(carriage.getTokens(Champion.class));
        setDefaultVerticalComponentAlignment(END);
    }

    protected void setChampions(List<Champion> champions) {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        champions.forEach(champion -> {
            String name = champion.getName();
            Image image = new Image("img/ava/" + name.toLowerCase() + ".png", name);
            image.setWidth("30px");
            image.setHeight("30px");
            horizontalLayout.add(image);
        });
        horizontalLayout.setWidth("100%");
        horizontalLayout.setHeight("40%");
        horizontalLayout.setAlignItems(AUTO);
        add(horizontalLayout);
    }

}
