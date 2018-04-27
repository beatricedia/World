package util;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import model.element.Element;
import model.element.ElementType;
import view.Legend;

public class Util {

    private static final Object lockObject = new Object();

    public static final Object lockOn() {
        return lockObject;
    }

    public static final UUID generateRandomUUID() {
        return UUID.randomUUID();
    }

    public static int randomInRange(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    public static Element elementOf(char c, Legend legend) {
        return legend.get(ElementType.of(String.valueOf(c)));
    }

    public static boolean getRandomBoolean() {
        return Math.random() < 0.5;
    }
}