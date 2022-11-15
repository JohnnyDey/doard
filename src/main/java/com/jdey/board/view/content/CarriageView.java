package com.jdey.board.view.content;

import com.jdey.board.model.Carriage;
import com.jdey.board.model.characters.Champion;
import com.jdey.board.model.tokens.SheriffToken;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import lombok.Getter;

import java.util.List;

import static com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment.AUTO;
import static com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment.END;


public class CarriageView extends HorizontalLayout {
    @Getter
    private final Carriage carriage;

    public CarriageView(Carriage carriage, boolean enable, ComponentEventListener<ClickEvent<HorizontalLayout>> listener) {
        this.carriage = carriage;
        getStyle().set("background-image", "url('img/carriage.jpg')")
                .set("background-size", "contain")
                .set("background-repeat", "no-repeat");
        if (enable) {
            getStyle().set("border", "2px dotted DarkOrange");
        }

        setWidth("300px");
        setHeight("100px");
        setChampions(carriage.getTokens(Champion.class), carriage.getTokens(SheriffToken.class));
        setDefaultVerticalComponentAlignment(END);
        addClickListener(listener);
    }

    protected void setChampions(List<Champion> champions, List<SheriffToken> sheriffTokenList) {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        champions.forEach(champion -> {
            Image image = createImage(champion.getName());
            horizontalLayout.add(image);
        });
        if (sheriffTokenList.size() == 1) {
            Image image = createImage("Sheriff");
            horizontalLayout.add(image);
        }
        horizontalLayout.setWidth("100%");
        horizontalLayout.setHeight("40%");
        horizontalLayout.setAlignItems(AUTO);
        add(horizontalLayout);
    }

    private Image createImage(String name) {
        Image image = new Image("img/ava/" + name.toLowerCase() + ".png", name);
        image.setWidth("30px");
        image.setHeight("30px");
        return image;
    }

}
