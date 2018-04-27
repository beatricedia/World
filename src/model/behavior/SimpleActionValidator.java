package model.behavior;

import java.util.Optional;

import model.action.Action;
import model.action.Actions;
import model.element.Element;
import model.element.ElementType;
import model.element.SimpleElement;
import view.Direction;

public class SimpleActionValidator implements ActionValidator {

    private final int max_reproduction_copy_count;
    private final int max_reproduction_count;

    public SimpleActionValidator(int max_reproduction_copy_count, int max_reproduction_count) {
        this.max_reproduction_copy_count = max_reproduction_copy_count;
        this.max_reproduction_count = max_reproduction_count;
    }

    @Override
    public Optional<Direction> validateAction(Action action, SimpleElement element,
                                              Optional<Direction> actionDirection) {
        if(Actions.reproduce().equals(action) && actionDirection.isPresent()
                && element.getReproductionCount() <= max_reproduction_count) {
            // find space for baby if partner is fit for reproducing
            Element partner = element
                    .getWorld()
                    .getGrid()
                    .getGridContent(element
                            .getPosition()
                            .plus(actionDirection.get().toVector()));
            double actualLife = element.getWorld().getManager().getLife(partner);
            double perc = 0d;
            if(partner.getLife() == 0) {
                perc = 100d;
            } else {
                perc = actualLife / partner.getLife() * 100;
            }
            if(perc > 100) { // partner fit
                return element.getView().find(element.getWorld(), element.getPosition(), ElementType.SPACE);
            }
            return Optional.empty();
        }
        if(Actions.reproduce_copy().equals(action)
                && element.getReproductionCount() >= max_reproduction_copy_count) {
            return Optional.empty(); // not allowed anymore
        }
        return actionDirection;
    }

}