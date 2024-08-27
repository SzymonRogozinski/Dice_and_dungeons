package Walking.Places;

import Walking.Collision.EnemyKilledException;
import Walking.Drones.Drone;

public class EnemyGamePlace extends GamePlace {
    public EnemyGamePlace(String name, String path) {
        super(name, path);
    }

    @Override
    public boolean getCollision(Drone goingToCollideCharacter) throws EnemyKilledException {
        if(goingToCollideCharacter.getIcon() instanceof EnemyGamePlace){
            return true;
        }else if(goingToCollideCharacter.getIcon() instanceof PlayerGamePlace){
            throw new EnemyKilledException(getReference());
        }else{
            return false;
        }
     }
}
