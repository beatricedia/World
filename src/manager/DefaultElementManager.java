package manager;

import model.element.Element;
import model.element.impl.Space;
import model.element.impl.Wall;

/**
 * Concrete {@link ElementManager} implementation
 * @author Softivion
 * @since 31/03/2018
 */
public class DefaultElementManager extends AbstractElementManager {

    @Override
    public Element space() {
        return new Space();
    }

    @Override
    public Element wall() {
        return new Wall();
    }

}