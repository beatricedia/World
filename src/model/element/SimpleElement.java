package model.element;

import java.util.Optional;

import model.action.Action;
import model.action.Actions;
import model.behavior.ActionChooser;
import model.behavior.ActionValidator;
import model.behavior.View;
import util.Util;
import view.Direction;
import view.Vector;
import view.WorldView;

/**
 * Abstraction for defining a simple living being .
 * It has a {@link View} to look around , an {@link ActionChooser} to choose its next {@link Action}
 * and an {@link ActionValidator} to validate its chosen {@link Action}.
 * Every object of this type runs in its own {@link Thread}.
 *
 * @author Softvision
 *
 */
public abstract class SimpleElement extends AbstractElement implements Runnable {

    private Thread t;
    private View view;
    private final int tick;
    private final ActionChooser chooser;
    private final ActionValidator validator;
    private Action lastAction;
    private int reproductionCount;

    public SimpleElement(int life, View view, int tick, ActionChooser chooser, ActionValidator validator) {
        super(life);
        this.view = view;
        this.tick = tick;
        this.t = new Thread(this);
        this.chooser = chooser;
        this.validator = validator;
    }

    public SimpleElement(View view, int tick, ActionChooser chooser, ActionValidator validator) {
        super(true); // eternal
        this.view = view;
        this.tick = tick;
        this.t = new Thread(this);
        this.chooser = chooser;
        this.validator = validator;
    }

    @Override
    public void afterInit(WorldView world, Vector position) {
        t.start();
    }

    @Override
    public void run() {
        WorldView world = getWorld();
        while(world.getManager().isAlive(this)) {
            try {
                Thread.sleep(getTick());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Action next = chooser.chooseNextAction(this);
            setLastAction(next);
            if(next.equals(Actions.pause())) {
                continue;
            }
            synchronized(Util.lockOn()) {
                Optional<Direction> direction = validator.validateAction(next, this,
                        view.find(world, getPosition(), next.getActionable().toElementTypes(this)));
                if(direction.isPresent()) {
                    Direction dir = direction.get();
                    next.act(world, this, getPosition(), dir);
                    if(next.equals(Actions.reproduce()) || next.equals(Actions.reproduce_copy())) {
                        incrementReproductionCount();
                    }
                }
            }
        }
    }

    public final void setLastAction(Action action) {
        this.lastAction = action;
    }

    public final View getView() {
        return this.view;
    }

    @Override
    public final Action getLastAction() {
        return this.lastAction;
    }

    public final int getReproductionCount() {
        return reproductionCount;
    }

    private final int getTick() {
        return tick;
    }

    private final void incrementReproductionCount() {
        reproductionCount++;
    }
}