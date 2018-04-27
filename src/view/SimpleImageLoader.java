package view;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;
import model.element.Element;
import model.element.ElementType;

public class SimpleImageLoader implements ImageLoader {

    private final Map<ElementType, Image> images = new HashMap<>();

    public SimpleImageLoader() throws Exception {
        FileInputStream input = new FileInputStream("res/plant2.jpg");
        Image image = new Image(input);
        input.close();
        images.put(ElementType.PLANT, image);

        input = new FileInputStream("res/herbiv2.jpg");
        image = new Image(input);
        input.close();
        images.put(ElementType.HERBIVOROUS, image);

        input = new FileInputStream("res/cal.jpg");
        image = new Image(input);
        input.close();
        images.put(ElementType.HORSE, image);

        input = new FileInputStream("res/space1.png");
        image = new Image(input);
        input.close();
        images.put(ElementType.SPACE, image);

        input = new FileInputStream("res/wall1.jpg");
        image = new Image(input);
        input.close();
        images.put(ElementType.WALL, image);

        input = new FileInputStream("res/predator2.jpg");
        image = new Image(input);
        input.close();
        images.put(ElementType.PREDATOR, image);

        input = new FileInputStream("res/predator1.jpg");
        image = new Image(input);
        input.close();
        images.put(ElementType.PREDATOR2, image);


        input = new FileInputStream("res/superpred1.jpg");
        image = new Image(input);
        input.close();
        images.put(ElementType.SUPER_PREDATOR, image);

    }

    @Override
    public Image load(ElementType e) {
        return images.get(e);
    }

    @Override
    public Image load(Element e) {
        return images.get(e.getElementType());
    }

}