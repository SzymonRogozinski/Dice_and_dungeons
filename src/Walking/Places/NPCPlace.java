package Walking.Places;

import Dialog.NPCLines;
import Walking.Collision.DoorOpenException;
import Walking.Collision.EnemyFightException;
import Walking.Collision.NPCDialogException;
import Walking.Drones.Drone;
import Walking.Drones.PlayerDrone;

public class NPCPlace extends GamePlace{

    private NPCLines lines;

    public NPCPlace(String path) {
        super("NPC", path);
    }

    @Override
    public boolean getCollision(Drone goingToCollideCharacter) throws NPCDialogException {
        if (goingToCollideCharacter instanceof PlayerDrone) {
            throw new NPCDialogException(lines);
        }
        return true;
    }

    public void setLines(NPCLines lines) {
        this.lines = lines;
    }
}
