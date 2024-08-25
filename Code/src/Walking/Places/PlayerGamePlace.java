package Walking.Places;

import Walking.Drones.Drone;

public class PlayerGamePlace extends GamePlace {
    public PlayerGamePlace(String path) {
        super("player", path);
    }

    @Override
    public boolean getCollision(Drone goingToCollideCharacter){ return true; }
}
