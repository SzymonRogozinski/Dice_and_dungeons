package Walking.Places;

import Game.PlayerInfo;
import Walking.Collision.DoorOpenException;
import Walking.Drones.Drone;
import Walking.Drones.EnemyDrone;
import Walking.Drones.PlayerDrone;

public class SafeRoomDoor extends GamePlace{

    public SafeRoomDoor(String path) {
        super("SD", path);
    }

    @Override
    public boolean getCollision(Drone goingToCollideCharacter) throws DoorOpenException {
        if (goingToCollideCharacter instanceof EnemyDrone)
            return true;
        else if (goingToCollideCharacter instanceof PlayerDrone)
            return false;
        return false;
    }
}
