package com.jdey.board.view.top;

import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.avatar.AvatarVariant;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class PlayerInfo extends VerticalLayout {
    public static final String DOC = "Doc";
    public static final String GHOST = "Ghost";
    public static final String CHEYENNE = "Cheyenne";

    public PlayerInfo(String name, int hand, boolean active) {
        add(createAvatar(name));
        add(createTextField(hand));
        if (active) {
            add(createActiveIcon());
        }
    }

    private Icon createIcon(VaadinIcon vaadinIcon) {
        Icon icon = vaadinIcon.create();
        icon.getStyle().set("padding", "var(--lumo-space-xs");
        return icon;
    }

    private Avatar createAvatar(String name) {
        Avatar avatar = new Avatar(name);
        avatar.setImage("img/ava/" + name.toLowerCase() + ".png");
        avatar.addThemeVariants(AvatarVariant.LUMO_XLARGE);
        return avatar;
    }

    private TextField createTextField(int count) {
        TextField field = new TextField();
        field.setPrefixComponent(new Icon(VaadinIcon.HAND));
        field.setEnabled(false);
        field.setValue(count + " карт в руке");
        return field;
    }

    private Span createActiveIcon() {
        Span badge = new Span(createIcon(VaadinIcon.CLOCK),
                new Span("Ходит"));
        badge.getElement().getThemeList().add("badge");
        return badge;
    }
}
