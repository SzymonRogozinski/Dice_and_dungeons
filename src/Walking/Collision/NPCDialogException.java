package Walking.Collision;

import Dialog.NPCLines;

public class NPCDialogException extends CollisionException{

    private final NPCLines lines;

    public NPCDialogException(NPCLines lines) {
        this.lines = lines;
    }

    public NPCLines getLines() {
        return lines;
    }
}
