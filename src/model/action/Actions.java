package model.action;

import java.util.HashMap;
import java.util.Map;

import model.element.ActionableElementType;

public class Actions {
    private static final Map<ActionType, Action> ALL_ACTIONS= new HashMap<>();
    static {
        ALL_ACTIONS.put(ActionType.MOVE, new Action(ActionType.MOVE, ActionableElementType.SPACE));
        ALL_ACTIONS.put(ActionType.MOVE1, new Action(ActionType.MOVE1, ActionableElementType.SPACE));
        ALL_ACTIONS.put(ActionType.PAUSE, new Action(ActionType.PAUSE, ActionableElementType.NONE));
        ALL_ACTIONS.put(ActionType.EAT, new Action(ActionType.EAT, ActionableElementType.CONSUMED));
        ALL_ACTIONS.put(ActionType.REPRODUCE, new Action(ActionType.REPRODUCE, ActionableElementType.SAME));
        ALL_ACTIONS.put(ActionType.REPRODUCE_COPY, new Action(ActionType.REPRODUCE_COPY, ActionableElementType.SPACE));
    }

    public static Action move() {
        return ALL_ACTIONS.get(ActionType.MOVE);
    }

    public static Action move1() {
        return ALL_ACTIONS.get(ActionType.MOVE1);
    }

    public static Action pause() {
        return ALL_ACTIONS.get(ActionType.PAUSE);
    }

    public static Action eat() {
        return ALL_ACTIONS.get(ActionType.EAT);
    }

    public static Action reproduce() {
        return ALL_ACTIONS.get(ActionType.REPRODUCE);
    }

    public static Action reproduce_copy() {
        return ALL_ACTIONS.get(ActionType.REPRODUCE_COPY);
    }
}