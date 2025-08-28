package Walking.Places;

import Game.PlayerInfo;
import Walking.Collision.DoorOpenException;
import Walking.Drones.Drone;
import Walking.Drones.EnemyDrone;
import Walking.Drones.PlayerDrone;

public class DoorGamePlace extends GamePlace {
    public DoorGamePlace(String path) {
        super('D', path);
    }

    @Override
    public boolean getCollision(Drone goingToCollideCharacter) throws DoorOpenException {
        if (goingToCollideCharacter instanceof EnemyDrone)
            return true;
        else if (goingToCollideCharacter instanceof PlayerDrone) {
            if (PlayerInfo.useKey())
                throw new DoorOpenException();
            else
                return true;
        } else
            return false;
    }
}
