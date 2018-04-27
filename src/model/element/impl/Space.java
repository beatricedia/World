package model.element.impl;

import model.element.AbstractElement;
import model.element.ElementType;

public class Space extends AbstractElement {

    public Space() {
        super(true);
    }

    @Override
    public ElementType getElementType() {
        return ElementType.SPACE;
    }
}