package view;

public enum DirectionHorse {

   NV(-1,2),NE(1,2),EN(2,1),ES(2,-1),SE(1,-2),SW(-1,-2),WS(-2,-1),WN(-2,1);

    private int x;
    private int y;

    private DirectionHorse(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector1 toVector1() {
        return new Vector1(x,y);
    }

}
