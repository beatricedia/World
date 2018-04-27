package view;

import manager.ElementManager;
import model.element.Element;

public interface WorldView {
    public Grid getGrid();
    public ElementManager getManager();
    public void eat(Element element, Vector from, Direction to);
    public void move(Element element, Vector from, Direction to);
    public void reproduce(Element element, Vector from, Direction to);
    public int getTick();
}