package Fight.Statuses;

import Fight.Tags;

import javax.swing.*;
import Character.GameCharacter;

public class StunStatus extends GameStatus{

    public StunStatus() {
        super(1, new Integer[]{}, new ImageIcon("StatusIcons/Stun.png"), new Tags[]{Tags.ON_TURN_START});
    }

    @Override
    public void effect(GameCharacter character) throws StatusException{
        throw new StatusException(StatusException.STUN);
    }

    @Override
    public void evaporate() throws StatusEvaporatedException {
        throw new StatusEvaporatedException();
    }
}
