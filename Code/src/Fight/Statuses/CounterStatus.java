package Fight.Statuses;

import Character.CharacterDieException;
import Character.GameCharacter;
import Game.Tags;

import javax.swing.*;

public class CounterStatus extends GameStatus{

    public CounterStatus(int startValue) {
        super(startValue,new Integer[]{0,0,startValue}, new ImageIcon("Texture/StatusIcons/Counter.png"),new Tags[]{Tags.ON_DEFEND});
    }

    @Override
    public void effect(GameCharacter character){
        try {
            character.receiveDamage(getSumUpValue());
        }catch (CharacterDieException ignore){}
    }

    @Override
    public String effectCommunicate(String name) { return name+" counter attack.";}

    @Override
    public String info(){
        return "Counter: "+getSumUpValue();
    }
}
