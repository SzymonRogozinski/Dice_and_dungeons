package Walking.Places;

import Game.PlayerInfo;
import Walking.Collision.DoorOpenException;
import Walking.Drones.Drone;

public class DoorGamePlace extends GamePlace {
    public DoorGamePlace(String path) {
        super('D', path);
    }

    @Override
    public boolean getCollision(Drone goingToCollideCharacter) throws DoorOpenException {
        if (goingToCollideCharacter.getIcon() instanceof EnemyGamePlace)
            return true;
        else if (goingToCollideCharacter.getIcon() instanceof PlayerGamePlace) {
            if (PlayerInfo.useKey())
                throw new DoorOpenException();
            else
                return true;
        } else
            return false;
    }
}
