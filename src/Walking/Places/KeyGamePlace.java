package Walking.Places;

import Walking.Collision.KeyCollectedException;
import Walking.Drones.Drone;
import Walking.Drones.EnemyDrone;
import Walking.Drones.PlayerDrone;

public class KeyGamePlace extends GamePlace {

    public KeyGamePlace(String path) {
        super('K', path);
    }

    @Override
    public boolean getCollision(Drone goingToCollideCharacter) throws KeyCollectedException {
        if (goingToCollideCharacter instanceof EnemyDrone) {
            return true;
        } else if (goingToCollideCharacter instanceof PlayerDrone) {
            throw new KeyCollectedException();
        } else {
            return false;
        }
    }
}
