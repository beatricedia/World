package view;

public class Vector {
    private final int x;
    private final int y;

    public Vector(int x, int y) {
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

    public Vector plus(Vector other) {
        return new Vector(x + other.getX(), y+other.getY());
    }

    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(o instanceof Vector) {
            Vector other = (Vector)o;
            return getX() == other.getX() && getY() == other.getY();
        }
        return false;
    }

    @Override
    public String toString() {
        return "[" +getX()+"," + getY() + "]";
    }
}