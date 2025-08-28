package Walking.Places;

import Walking.Collision.EnemyFightException;
import Walking.Drones.Drone;
import Walking.Drones.EnemyDrone;
import Walking.Drones.PlayerDrone;

public class EnemyGamePlace extends GamePlace {
    public EnemyGamePlace(String name, String path) {
        super(name, path);
    }

    @Override
    public boolean getCollision(Drone goingToCollideCharacter) throws EnemyFightException {
        if (goingToCollideCharacter instanceof EnemyDrone) {
            return true;
        } else if (goingToCollideCharacter instanceof PlayerDrone) {
            throw new EnemyFightException(getReference());
        } else {
            return false;
        }
    }
}
