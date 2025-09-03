package Walking.Places;

import Walking.Collision.EnemyFightException;
import Walking.Drones.Drone;
import Walking.Drones.EnemyDrone;

public class PlayerGamePlace extends GamePlace {
    public PlayerGamePlace(String path) {
        super("player", path);
    }

    @Override
    public boolean getCollision(Drone goingToCollideCharacter) throws EnemyFightException {
        if(goingToCollideCharacter.getIcon() instanceof QuestPlace questPlace)
            questPlace.getQuest().questWasDone();
        if (goingToCollideCharacter instanceof EnemyDrone)
            throw new EnemyFightException(getReference());
        return true;
    }
}
