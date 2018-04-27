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

public class Herbivorous extends SimpleElement {

    private static final Set<ElementType> consumes = new HashSet<>();
    private static final Set<Action> actions = new HashSet<>();

    static {
        consumes.add(ElementType.PLANT); // eats plants
        actions.add(Actions.eat());
        actions.add(Actions.move());
        actions.add(Actions.reproduce());
        actions.addAll(defaultActions());
    }

    public Herbivorous(int life, View view, int tick, ActionChooser chooser, ActionValidator validator) {
        super(life, view, tick, chooser, validator);
    }

    @Override
    public ElementType getElementType() {
        return ElementType.HERBIVOROUS;
    }

    @Override
    public Set<ElementType> consumes() {
        return consumes;
    }

    @Override
    public Set<Action> possibleActions() {
        return actions;
    }

}