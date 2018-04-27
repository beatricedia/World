package view;

import manager.ElementManager;
import model.behavior.ActionChooser;
import model.behavior.ActionValidator;
import model.behavior.SimpleActionChooser;
import model.behavior.SimpleActionValidator;
import model.behavior.SimpleView;
import model.behavior.View;
import model.element.Element;
import model.element.ElementType;
import model.element.impl.*;
import util.Util;

public class SimpleLegend implements Legend {

    private static int MIN_SIMPLE_CREATURE_LIFE = 5;
    private static int MAX_SIMPLE_CREATURE_LIFE = 25;
    private static int SIMPLE_CREATURE_TICK = 300;
    private static int PLANT_TICK = 1000;
    private static int MIN_PLANT_LIFE = 10;
    private static int MAX_PLANT_LIFE = 40;
    private static int REPRODUCTION_COPY_COUNT = 3;
    private static int REPRODUCTION_COUNT = 2;
    private static final int PREDATOR_LIFE = 100;
    private static final ActionChooser ACTION_CHOOSER = new SimpleActionChooser();
    private static final ActionValidator ACTION_VALIDATOR = new SimpleActionValidator(REPRODUCTION_COPY_COUNT, REPRODUCTION_COUNT);
    private static final View VIEW = new SimpleView();

    private ElementManager manager;

    public SimpleLegend(ElementManager manager) {
        this.manager = manager;
    }

    @Override
    public Element get(ElementType elementType) {
        Element e = null;
        switch(elementType) {
            case WALL :
                e = manager.wall();
                break;
            case SPACE :
                e = manager.space();
                break;
            case PREDATOR :
                e = new Predator(PREDATOR_LIFE, VIEW, SIMPLE_CREATURE_TICK, ACTION_CHOOSER, ACTION_VALIDATOR);
                break;
            case PREDATOR2:
                e = new Predator2(PREDATOR_LIFE, VIEW, SIMPLE_CREATURE_TICK, ACTION_CHOOSER, ACTION_VALIDATOR);
                break;
            case SUPER_PREDATOR :
                e = new EternalPredator(VIEW, SIMPLE_CREATURE_TICK, ACTION_CHOOSER, ACTION_VALIDATOR);
                break;
            case PLANT :
                e = new Plant(Util.randomInRange(MIN_PLANT_LIFE, MAX_PLANT_LIFE),
                        VIEW, PLANT_TICK, ACTION_CHOOSER, ACTION_VALIDATOR);
                break;
            case HERBIVOROUS:
                e = new Herbivorous(Util.randomInRange(MIN_SIMPLE_CREATURE_LIFE, MAX_SIMPLE_CREATURE_LIFE),
                        VIEW, SIMPLE_CREATURE_TICK, ACTION_CHOOSER, ACTION_VALIDATOR);
                break;
            case HORSE:
                e = new Horse(Util.randomInRange(MIN_SIMPLE_CREATURE_LIFE, MAX_SIMPLE_CREATURE_LIFE),
                        VIEW, SIMPLE_CREATURE_TICK, ACTION_CHOOSER, ACTION_VALIDATOR);
                break;
        }
        if(e == null) {
            throw new IllegalArgumentException("UnknownElement:" + elementType);
        }
        manager.add(e);
        return e;
    }

    @Override
    public ElementManager getManager() {
        return manager;
    }
}