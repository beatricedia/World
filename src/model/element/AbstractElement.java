package model.element;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import model.action.Action;
import model.action.Actions;
import util.Util;
import view.Vector;
import view.Vector1;
import view.WorldView;

public abstract class AbstractElement implements Element {

    private final int life;
    private final boolean eternal;
    private Vector position;
    private Vector1 positionHorse;
    private final UUID id;
    private WorldView world;
    private volatile boolean initialized;
    private static final Set<Action> defaultActions = new HashSet<>();

    static {
        defaultActions.add(Actions.pause());
    }

    protected AbstractElement(boolean eternal) {
        this(eternal, Integer.MAX_VALUE);
    }

    protected AbstractElement(int life) {
        this(false, life);
    }

    protected static Set<Action> defaultActions() {
        return defaultActions;
    }

    private AbstractElement(boolean eternal, int life) {
        this.life = life;
        this.eternal = eternal;
        this.id = Util.generateRandomUUID();
    }

    @Override
    public final void init(WorldView world, Vector pos) {
        if(initialized) {
            throw new IllegalStateException("World already initialized");
        }
        this.world = world;
        this.position = pos;
        afterInit(world, pos);
    }

    @Override
    public final Vector getPosition() {
        return position;
    }

    @Override
    public Vector1 getHorsePosition() {
        return positionHorse;
    }

    @Override
    public final void setPosition(Vector vector) {
        this.position = vector;
    }

    @Override
    public void setHorsePosition(Vector1 vector1) {
        this.positionHorse = vector1;
    }

    @Override
    public final UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object other) {
        if(this == other) {
            return true;
        }
        if(other instanceof SimpleElement) {
            return getId().equals(((SimpleElement) other).getId());
        }
        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public final WorldView getWorld() {
        return world;
    }

    @Override
    public Set<ElementType> consumes() {
        return Collections.emptySet();
    }

    /**
     * Hook method
     * @param world
     * @param pos
     */
    public void afterInit(WorldView world, Vector pos) {

    }

    @Override
    public final boolean isEternal() {
        return eternal;
    }

    @Override
    public final int getLife() {
        return life;
    }

    @Override
    public Set<Action> possibleActions() {
        return Collections.emptySet();
    }

    @Override
    public Action getLastAction() {
        return Actions.pause();
    }

    @Override
    public String toString() {
        return "Element " + getElementType() + " {" + getPosition() + "}";
    }
}