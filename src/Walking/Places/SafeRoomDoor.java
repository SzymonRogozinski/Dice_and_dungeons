package Walking.Places;

import Game.PlayerInfo;
import Walking.Collision.DoorOpenException;
import Walking.Drones.Drone;

public class SafeRoomDoor extends GamePlace{

    public SafeRoomDoor(String path) {
        super("SD", path);
    }

    @Override
    public boolean getCollision(Drone goingToCollideCharacter) throws DoorOpenException {
        if (goingToCollideCharacter.getIcon() instanceof EnemyGamePlace)
            return true;
        else if (goingToCollideCharacter.getIcon() instanceof PlayerGamePlace)
            return false;
        return false;
    }
}
