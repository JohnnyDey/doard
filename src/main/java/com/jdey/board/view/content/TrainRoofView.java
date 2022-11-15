package com.jdey.board.view.content;

import com.jdey.board.model.Carriage;
import com.jdey.board.model.Selectable;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@UIScope
public class TrainRoofView extends TrainCarriageView {
    @Override
    protected List<Carriage> getCarriages() {
        return gameHolder.getGame().getTrain().getRoofsList();
    }

    @Override
    protected HorizontalLayout createCarriage(int index, List<Selectable> availables) {
        Carriage carriage = getCarriages().get(index);
        boolean contains = availables.contains(carriage);
        return new CarriageRoof(getCarriages().get(index), contains, this::selectCarriage, this::selectChampion);
    }

    @Override
    protected HorizontalLayout createLocomotive(int index, List<Selectable> availables) {
        Carriage carriage = getCarriages().get(index);
        boolean contains = availables.contains(carriage);
        return new LocomotiveRoof(getCarriages().get(index), contains, this::selectCarriage, this::selectChampion);
    }
}
