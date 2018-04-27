package manager;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import model.element.Element;
import view.Vector;

/**
 * Skeletal implementation of an {@link ElementManager}
 * @author Softvision
 * @since 31/03/2018
 */
public abstract class AbstractElementManager implements ElementManager {

    private Map<UUID, ElementTraits> elements;

    protected AbstractElementManager() {
        elements = new HashMap<>();
    }

    @Override
    public final void decrementLife(Element element) {
        if(!element.isEternal() && isManaged(element)) {
            ElementTraits trait = get(element);
            elements.put(element.getId(), trait.setLife(trait.getLife()-1));
        }
    }

    @Override
    public final void incrementLife(Element element) {
        if(!element.isEternal() && isManaged(element)) {
            ElementTraits trait = get(element);
            elements.put(element.getId(), trait.setLife(trait.getLife()+1));
        }
    }

    @Override
    public final void augmentLifeBy(Element element, int quantity) {
        if(!element.isEternal() && isManaged(element)) {
            ElementTraits trait = get(element);
            elements.put(element.getId(), trait.setLife(trait.getLife()+quantity));
        }
    }

    @Override
    public final void reduceLifeBy(Element element, int quantity) {
        if(!element.isEternal() && isManaged(element)) {
            ElementTraits trait = get(element);
            elements.put(element.getId(), trait.setLife(trait.getLife()-quantity));
        }
    }

    @Override
    public void remove(Element element) {
        if(isManaged(element) && !element.isEternal()) {
            elements.remove(element.getId());
        }
    }

    @Override
    public final boolean isAlive(Element element) {
        if(isManaged(element)) {
            if(!element.isEternal()) {
                return get(element).getLife() > 0;
            }
            return true;
        }
        return false;
    }

    @Override
    public final int getLife(Element element) {
        if(isManaged(element)) {
            if(!element.isEternal()) {
                return get(element).getLife();
            }
            return Integer.MAX_VALUE;
        }
        return -1;
    }

    @Override
    public final void add(Element element) {
        elements.put(element.getId(), new ElementTraits(element.getLife(), element.isEternal()));
    }

    @Override
    public final boolean isManaged(Element element) {
        return elements.containsKey(element.getId());
    }

    @Override
    public final int getWorldCapacityBy(Element element) {
        int count = 0;
        for(int i=0;i< element.getWorld().getGrid().getHeight();i++) {
            for(int k=0;k< element.getWorld().getGrid().getWidth();k++) {
                if(!element.getWorld().getGrid().getGridContent(new Vector(k, i)).equals(wall())) {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public final int populationCount(Element element) {
        int count = 0;
        for(int i=0;i< element.getWorld().getGrid().getHeight();i++) {
            for(int k=0;k< element.getWorld().getGrid().getWidth();k++) {
                if(element.getWorld().getGrid().getGridContent(new Vector(k, i)).getElementType().equals(element.getElementType())) {
                    count++;
                }
            }
        }
        return count;
    }

    private ElementTraits get(Element element) {
        return elements.get(element.getId());
    }

}