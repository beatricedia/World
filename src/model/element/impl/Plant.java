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

public class Plant extends SimpleElement {

    private static final Set<Action> actions = new HashSet<>();

    static {
        actions.add(Actions.reproduce_copy());
        actions.addAll(defaultActions());
    }

    public Plant(int life, View view, int tick, ActionChooser chooser, ActionValidator validator) {
        super(life, view, tick, chooser, validator);
    }

    @Override
    public ElementType getElementType() {
        return ElementType.PLANT;
    }

    @Override
    public Set<Action> possibleActions() {
        return actions;
    }
}