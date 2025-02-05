package Walking;

import Walking.Drones.Drone;

public class FogOfWar {

    private static final int inertia = 2;
    private static final int fogRange = 7;
    private final Drone player;
    private final int mapMaxX, mapMaxY;
    private int minX;
    private int minY;
    private int fogCenterX, fogCenterY;

    public FogOfWar(Drone player, GameMap map) {
        this.player = player;
        mapMaxX = map.getWidth();
        mapMaxY = map.getHeight();
        fogCenterX = player.getPosX();
        fogCenterY = player.getPosY();
        refreshFog();
    }

    public static int getSize() {
        return fogRange * 2 + 1;
    }

    public void refreshFog() {
        if (player.getPosX() > fogCenterX + inertia)
            fogCenterX++;
        else if (player.getPosX() < fogCenterX - inertia)
            fogCenterX--;
        else if (player.getPosY() > fogCenterY + inertia)
            fogCenterY++;
        else if (player.getPosY() < fogCenterY - inertia)
            fogCenterY--;

        minX = Math.max(Math.min(fogCenterX + fogRange, mapMaxX) - getSize(), 0);
        minY = Math.max(Math.min(fogCenterY + fogRange, mapMaxY) - getSize(), 0);
    }

    public int getMinX() {
        return minX;
    }

    public int getMinY() {
        return minY;
    }
}
