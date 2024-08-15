package Fight.Statuses;

import Character.GameCharacter;
import Game.Tags;

import javax.swing.*;

public class StunStatus extends GameStatus{

    public StunStatus() {
        super(1, new Integer[]{}, new ImageIcon("Texture/StatusIcons/Stun.png"), new Tags[]{Tags.ON_TURN_START});
    }

    @Override
    public void effect(GameCharacter character) throws StatusException{
        throw new StatusException(StatusException.STUN);
    }

    @Override
    public String effectCommunicate(String name) { return name+" lose turn due to stun.";}

    @Override
    public void evaporate() throws StatusEvaporatedException {
        throw new StatusEvaporatedException();
    }

    @Override
    public void addEffect(int value){}

    @Override
    public String info(){
        return "Stunned";
    }
}
