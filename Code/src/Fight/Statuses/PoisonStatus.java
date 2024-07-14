package Fight.Statuses;

import Character.CharacterDieException;
import Character.GameCharacter;
import Game.Tags;

import javax.swing.*;

public class PoisonStatus extends GameStatus{
    public PoisonStatus(int startValue) {
        super(startValue,new Integer[]{0,0,startValue}, new ImageIcon("StatusIcons/Poison.png"),new Tags[]{Tags.ON_TURN_START});
    }

    @Override
    public void effect(GameCharacter character) throws StatusException{
        try {
            character.dealDamage(getSumUpValue());
        }catch (CharacterDieException e){
            throw new StatusException(StatusException.DEATH);
        }
    }

    @Override
    public String effectCommunicate(String name) { return "Poison deal "+ getSumUpValue() +" damage to "+ name+".";}

    @Override
    public String info(){
        return "Poison: "+getSumUpValue();
    }
}
