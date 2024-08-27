package Walking.Drones;

import Walking.Places.GamePlace;

public class Drone {
    private int posX;
    private int posY;
    private final GamePlace icon;


    public Drone(int posX, int posY, GamePlace icon) {
        this.posX = posX;
        this.posY = posY;
        this.icon = icon;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public GamePlace getIcon() {
        return icon;
    }

    public void setPos(int dx, int dy){
        posX+=dx;
        posY+=dy;
    }
}
