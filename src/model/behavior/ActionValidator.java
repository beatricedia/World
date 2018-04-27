package model.behavior;

import java.util.Optional;

import model.action.Action;
import model.element.SimpleElement;
import view.Direction;

/**
 * Validates a given {@link Action}
 * @author Softvision
 *
 */
public interface ActionValidator {
    public Optional<Direction> validateAction(Action action,
                                              SimpleElement element,
                                              Optional<Direction> actionDirection);
}