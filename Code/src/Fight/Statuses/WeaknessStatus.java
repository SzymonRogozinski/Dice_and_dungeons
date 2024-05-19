package Fight.Statuses;

import Fight.Tags;
import Character.GameCharacter;

import javax.swing.*;

public class WeaknessStatus extends GameStatus{

    private int statusCount;

    public WeaknessStatus(int startValue) {
        super(startValue, new Integer[]{}, new ImageIcon("StatusIcons/Weakness.png"), new Tags[]{Tags.AFTER_TURN_START});
        statusCount=startValue;
    }

    @Override
    public void effect(GameCharacter character){
        character.multiplyMod(0.5,1);
    }

    @Override
    public void evaporate() throws StatusEvaporatedException {
        statusCount--;
        if (statusCount<0)
            throw new StatusEvaporatedException();
    }

    @Override
    public void addEffect(int value){
        statusCount+=value;
    }

    @Override
    public String info(){
        return "Weakness: "+(statusCount+1);
    }
}
