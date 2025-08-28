package Walking.Places;

import Walking.Collision.EnterExitException;
import Walking.Drones.Drone;
import Walking.Drones.EnemyDrone;

public class EntryGamePlace extends GamePlace {
    private final boolean isStart;

    public EntryGamePlace(String path, boolean isStart, boolean isBoss) {
        super(isBoss ? "Boss" : isStart ? "Entry" : "Exit", path);
        this.isStart = isStart;
    }

    @Override
    public boolean getCollision(Drone goingToCollideCharacter) throws EnterExitException {
        if (goingToCollideCharacter instanceof EnemyDrone && !isStart)
            return true;
        else if (!isStart)
            throw new EnterExitException();
        return false;
    }
}
