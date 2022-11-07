package com.jdey.board;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.shared.communication.PushMode;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Push(PushMode.MANUAL)
@Theme(themeClass = Lumo.class, variant = Lumo.LIGHT)
public class Cong implements AppShellConfigurator {
}
