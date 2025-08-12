package Walking.Places;

import Walking.Collision.DoorOpenException;
import Walking.Drones.Drone;

public class NPCPlace extends GamePlace{

    public NPCPlace(String path) {
        super("NPC", path);
    }

    @Override
    public boolean getCollision(Drone goingToCollideCharacter) throws DoorOpenException {
        return true;
    }
}
