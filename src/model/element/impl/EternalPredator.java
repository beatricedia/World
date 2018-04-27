package model.element.impl;

import java.util.HashSet;
import java.util.Set;

import model.action.Action;
import model.action.Actions;
import model.behavior.ActionChooser;
import model.behavior.ActionValidator;
import model.behavior.View;
import model.element.ElementType;
import model.element.SimpleElement;

/**
 * Defines an "undying" predator.
 * It's an eternal {@link SimpleElement} that eats other creatures.
 * @author Softvision
 *
 */
public class EternalPredator extends SimpleElement {

    private static final Set<ElementType> consumes = new HashSet<>();
    private static final Set<Action> actions = new HashSet<>();

    static {
        consumes.add(ElementType.HERBIVOROUS); // eats herbivorous animals
        consumes.add(ElementType.PREDATOR); // eats predators
        consumes.add(ElementType.PREDATOR2); // eats predators
        actions.add(Actions.eat());
        actions.add(Actions.move());
        actions.addAll(defaultActions());
    }

    public EternalPredator(View view, int tick, ActionChooser chooser, ActionValidator validator) {
        super(view, tick, chooser, validator);
    }

    @Override
    public ElementType getElementType() {
        return ElementType.SUPER_PREDATOR;
    }

    @Override
    public Set<Action> possibleActions() {
        return actions;
    }

    @Override
    public Set<ElementType> consumes() {
        return consumes;
    }
}