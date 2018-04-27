package model.element.impl;

import model.element.AbstractElement;
import model.element.ElementType;

public class Wall extends AbstractElement {

    public Wall() {
        super(true);
    }

    @Override
    public ElementType getElementType() {
        return ElementType.WALL;
    }
}