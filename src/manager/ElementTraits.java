package manager;

public final class ElementTraits {
    private final int life;
    //private final boolean moveable;
    private final boolean eternal;

    public ElementTraits(int life, boolean eternal) {
        this.life = life;
        //this.moveable = moveable;
        this.eternal = eternal;
    }

    public int getLife() {
        return life;
    }

    public ElementTraits setLife(int life) {
        return new ElementTraits(life, isEternal());
    }
    /*
        public boolean isMoveable() {
            return moveable;
        }
    */
    public boolean isEternal() {
        return eternal;
    }
}