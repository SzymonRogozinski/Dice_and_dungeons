package Walking.Places;

import Walking.Collision.EnemyFightException;
import Walking.Drones.Drone;

public class PlayerGamePlace extends GamePlace {
    public PlayerGamePlace(String path) {
        super("player", path);
    }

    @Override
    public boolean getCollision(Drone goingToCollideCharacter) throws EnemyFightException{
        if(goingToCollideCharacter.getIcon() instanceof EnemyGamePlace)
            throw new EnemyFightException(getReference());
        return true;
    }
}
