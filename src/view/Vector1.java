package view;

public class Vector1 {
    private final int x;
    private final int y;

    public Vector1(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Vector1 plus(Vector1 other) {
        return new Vector1(x + other.getX(), y+other.getY());
    }

    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(o instanceof Vector1) {
            Vector1 other = (Vector1)o;
            return getX() == other.getX() && getY() == other.getY();
        }
        return false;
    }

    @Override
    public String toString() {
        return "[" +getX()+"," + getY() + "]";
    }
}
