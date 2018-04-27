package model.action;

import model.element.ActionableElementType;
import model.element.Element;
import model.element.ElementType;
import model.element.impl.Horse;
import view.*;

public final class Action {

    private final ActionType actionType;
    private final ActionableElementType actionable;
    private ElementType elementType;
    private Vector1 vector1;
    private DirectionHorse directionHorse;

    public Action(ActionType actionType, ActionableElementType actionable) {
        this.actionable = actionable;
        this.actionType = actionType;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public ActionableElementType getActionable() {
        return actionable;
    }

    public void act(WorldView world, Element element, Vector from, Direction to) {
        if(!isStillAlive(world, element)) {
            return;
        }

        //if(!elementType.getElement().equals(elementType.HORSE)) {

            switch (actionType) {
                case MOVE:
                    world.move(element, from, to);
                    element.setPosition(element.getPosition().plus(to.toVector()));
                    return;

                case EAT:
                    world.eat(element, from, to);
                    element.setPosition(element.getPosition().plus(to.toVector()));
                    return;
                case REPRODUCE:
                case REPRODUCE_COPY:
                    world.reproduce(element, from, to);
                    return;
                default:
            }

        //}
        /*
        else
        {
            element.setHorsePosition(element.getHorsePosition().plus(directionHorse.toVector1()));
        }
        */


    }



    /**
     * manager.isAlive() is not called from synchronized context
     * so we must check again to prevent the case where the manager has remmoved the element
     * after the thread has verified manager.isAlive()
     */
    private boolean isStillAlive(WorldView world, Element element) {
        return world.getManager().isAlive(element);
    }

    @Override
    public String toString() {
        return "Action { " + actionType.name() + " }";
    }
}