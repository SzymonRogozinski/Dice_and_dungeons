package Walking.Places;

import Walking.Collision.KeyCollectedException;
import Walking.Drones.Drone;

public class KeyGamePlace extends GamePlace {

    public KeyGamePlace(String path) {
        super('K', path);
    }

    @Override
    public boolean getCollision(Drone goingToCollideCharacter) throws KeyCollectedException {
        if (goingToCollideCharacter.getIcon() instanceof EnemyGamePlace) {
            return true;
        } else if (goingToCollideCharacter.getIcon() instanceof PlayerGamePlace) {
            throw new KeyCollectedException();
        } else {
            return false;
        }
    }
}
