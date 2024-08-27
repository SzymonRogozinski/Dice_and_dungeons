package Walking.Places;

import Walking.Collision.EnemyKilledException;
import Walking.Collision.KeyCollectedException;
import Walking.Drones.Drone;

public class KeyGamePlace extends GamePlace {

    public KeyGamePlace(String path) {
        super('K', path);
    }

    @Override
    public boolean getCollision(Drone goingToCollideCharacter) throws EnemyKilledException, KeyCollectedException {
        if(goingToCollideCharacter.getIcon() instanceof EnemyGamePlace){
            return true;
        }else if(goingToCollideCharacter.getIcon() instanceof PlayerGamePlace){
            System.out.println("You collected a key!");
            throw new KeyCollectedException();
        }else{
            return false;
        }
    }
}
