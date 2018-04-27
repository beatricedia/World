package model.element;

import java.util.Collections;
import java.util.Set;

/**
 * Defines the type of elements that can be acted upon.
 * E.g : a "move" {@link Action} would have a SPACE-like {@link Element} that could be acted upon,
 * whereas an "eat" {@link Action} would act upon other elements that can be consumed by the give {@link Element}.
 * So if a predator-element would choose to eat , it would act upon ( consume ) its prey (a set of other elements).
 * @author Softvision
 *
 */
public enum ActionableElementType {
    SPACE, SAME, CONSUMED, NONE;

    public Set<ElementType> toElementTypes(Element element) {
        switch(this) {
            case SPACE:
                return Collections.singleton(ElementType.SPACE);
            case SAME:
                return Collections.singleton(element.getElementType());
            case CONSUMED:
                return element.consumes();
            default:
                return Collections.emptySet();
        }
    }
}
