package model.element;

/**
 * Defines an element type along with it's associated character
 * @author Softvision
 *
 */
public enum ElementType {

    SPACE(" "), WALL("#"), HERBIVOROUS("o"), PREDATOR("*"), SUPER_PREDATOR("@"), PLANT("~"), PREDATOR2("&"), HORSE("$");

    private final String element;

    private ElementType(String s) {
        this.element = s;
    }

    public String getElement() {
        return element;
    }

    public static ElementType of(String type) {
        for(ElementType elementType : ElementType.values()) {
            if(elementType.getElement().equals(type)) {
                return elementType;
            }
        }
        throw new IllegalArgumentException("Invalid character:" + type);
    }
}