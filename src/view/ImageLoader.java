package view;

import javafx.scene.image.Image;
import model.element.Element;
import model.element.ElementType;

public interface ImageLoader {

    public Image load(ElementType e);
    public Image load(Element e);
}