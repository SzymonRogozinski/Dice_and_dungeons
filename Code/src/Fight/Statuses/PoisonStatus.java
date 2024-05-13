package Fight.Statuses;

import javax.swing.*;
import Character.GameCharacter;
import Fight.Tags;
import Character.CharacterDieException;

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
}
