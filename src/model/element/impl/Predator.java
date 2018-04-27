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

public class Predator extends SimpleElement {

    private static final Set<ElementType> consumes = new HashSet<>();
    private static final Set<Action> actions = new HashSet<>();

    static {
        consumes.add(ElementType.HERBIVOROUS); // eats herbivorous animals
        actions.add(Actions.eat());
        actions.add(Actions.move());
        actions.add(Actions.reproduce());
        actions.addAll(defaultActions());
    }

    public Predator(int life, View view, int tick, ActionChooser chooser, ActionValidator validator) {
        super(life, view, tick, chooser, validator);
    }

    @Override
    public ElementType getElementType() {
        return ElementType.PREDATOR;
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