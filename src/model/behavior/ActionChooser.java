package model.behavior;

import model.action.Action;
import model.element.Element;

/**
 * Defines a strategy for choosing the next {@link Action} that an {@link Element} wants to make.
 * @author Softvision
 *
 */
public interface ActionChooser {
    public Action chooseNextAction(Element element);
}