package com.jdey.board.event;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.nimbusds.jose.util.Pair;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

@Component
@Order
public class GameApplicationListener implements ApplicationListener<AppEvent> {

    private final Multimap<String, Pair<String, UI>> uis = ArrayListMultimap.create();

    @Override
    public void onApplicationEvent(AppEvent event) {
        uis.get((String) event.getSource()).forEach(pair -> {
            UI ui = pair.getRight();
            ComponentUtil.fireEvent(ui, event.getInnerEvent());
            ui.access(ui::push);
        });

    }

    public void register(String gameId) {
        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        uis.get(gameId).stream()
                .filter(pair -> pair.getLeft().equals(sessionId))
                .findFirst()
                .ifPresent(uiPair -> uis.remove(gameId, uiPair));
        uis.put(gameId, Pair.of(sessionId, UI.getCurrent()));
    }
}
