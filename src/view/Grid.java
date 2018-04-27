package view;

import model.element.Element;

public class Grid {

    private final int width;
    private final int height;
    private final Element[] space;

    public Grid(int width, int height) {
        if(width < 1 || height < 1) {
            throw new IllegalArgumentException("Invalid Grid Object");
        }
        space = new Element[width*height];
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isInside(Vector v) {
        return 0 <= v.getX() && v.getX() < getWidth() &&
                0 <= v.getY() && v.getY() < getHeight();
    }

    public Element getGridContent(Vector v) {
        return this.space[v.getX() + getWidth() * v.getY()];
    }

    public void setGridContent(Vector v, Element value) {
        this.space[v.getX() + getWidth() * v.getY()] = value;
    }
}