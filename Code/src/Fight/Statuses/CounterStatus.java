package Fight.Statuses;

import Fight.Tags;
import Character.GameCharacter;
import Character.CharacterDieException;

import javax.swing.*;

public class CounterStatus extends GameStatus{

    public CounterStatus(int startValue) {
        super(startValue,new Integer[]{0,0,startValue}, new ImageIcon("StatusIcons/Counter.png"),new Tags[]{Tags.ON_DEFEND});
    }

    @Override
    public void effect(GameCharacter character){
        try {
            character.dealDamage(getSumUpValue());
        }catch (CharacterDieException ignore){}
    }

    @Override
    public String info(){
        return "Counter: "+getSumUpValue();
    }
}
