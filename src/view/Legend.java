package view;

import manager.ElementManager;
import model.element.Element;
import model.element.ElementType;

public interface Legend {
    Element get(ElementType elementType);
    ElementManager getManager();
}