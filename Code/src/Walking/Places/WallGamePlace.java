package Walking.Places;

import Walking.Drones.Drone;

public class WallGamePlace extends GamePlace {

    public WallGamePlace(char pic, String path) {
        super(pic, path);
    }

    @Override
    public boolean getCollision(Drone goingToCollideCharacter) {
        return true;
    }
}
