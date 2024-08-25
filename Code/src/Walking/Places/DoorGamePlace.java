package Walking.Places;

import Walking.Collision.DoorOpenException;
import Walking.Drones.Drone;
import Walking.Drones.PlayerDrone;

public class DoorGamePlace extends GamePlace {
    public DoorGamePlace(String path) {
        super('D', path);
    }

    @Override
    public boolean getCollision(Drone goingToCollideCharacter) throws DoorOpenException {
        if(goingToCollideCharacter.getIcon() instanceof EnemyGamePlace){
            return true;
        }else if(goingToCollideCharacter.getIcon() instanceof PlayerGamePlace){
            var p=(PlayerDrone)goingToCollideCharacter;
            if(p.useKey()){
                throw new DoorOpenException();
            }else{
                System.out.println("You don't have key to open door!");
                return true;
            }
        }else{
            return false;
        }
    }
}
