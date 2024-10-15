package Walking.Places;

import Walking.Collision.EnemyFightException;
import Walking.Drones.Drone;

public class EnemyGamePlace extends GamePlace {
    public EnemyGamePlace(String name, String path) {
        super(name, path);
    }

    @Override
    public boolean getCollision(Drone goingToCollideCharacter) throws EnemyFightException {
        if (goingToCollideCharacter.getIcon() instanceof EnemyGamePlace) {
            return true;
        } else if (goingToCollideCharacter.getIcon() instanceof PlayerGamePlace) {
            throw new EnemyFightException(getReference());
        } else {
            return false;
        }
    }
}
