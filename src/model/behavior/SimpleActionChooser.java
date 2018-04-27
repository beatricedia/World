package model.behavior;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import model.action.Action;
import model.action.Actions;
import model.element.Element;

public class SimpleActionChooser implements ActionChooser {

    @Override
    public Action chooseNextAction(Element element) {
        double actualLife = element.getWorld().getManager().getLife(element);
        double perc = 0d;
        List<Action> possibleActions = new ArrayList<>();
        if(element.getLife() == 0) {
            perc = 100d;
        } else {
            perc = actualLife / element.getLife() * 100;
        }
        if(perc > 100 ) {
            possibleActions.add(Actions.reproduce_copy());
            possibleActions.add(Actions.reproduce());
            possibleActions.add(Actions.eat());
            possibleActions.add(Actions.move());
            possibleActions.add(Actions.move1());
        }else if(perc <= 100) {
            possibleActions.add(Actions.eat());
            possibleActions.add(Actions.move());
            possibleActions.add(Actions.move1());
            possibleActions.add(Actions.reproduce_copy());
        } else if (perc < 5) {
            possibleActions.add(Actions.reproduce_copy());
            possibleActions.add(Actions.eat());
            possibleActions.add(Actions.move());
            possibleActions.add(Actions.move1());
        } else { // too old just wait there and hope food will come
            possibleActions.add(Actions.eat());
        }

        if(possibleActions.size() > 1 && element.getLastAction() != null) {
            possibleActions.remove(element.getLastAction());
        }

        Set<Action> definedPossibleActions = element.possibleActions();
        Iterator<Action> iterator = possibleActions.iterator();
        while(iterator.hasNext()) {
            if(!definedPossibleActions.contains(iterator.next())) {
                iterator.remove();
            }
        }
        if(possibleActions.isEmpty()) {
            return Actions.pause();
        }

        Collections.shuffle(possibleActions);
        return possibleActions.get(0);
    }

}