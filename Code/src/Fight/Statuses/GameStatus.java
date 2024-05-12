package Fight.Statuses;

import java.util.ArrayList;
import java.util.Arrays;
import Character.GameCharacter;

import javax.swing.*;

public abstract class GameStatus {

    private final ArrayList<Integer> values;
    private int sumUpValue;
    private final ImageIcon icon;

    public GameStatus(int startValue,ImageIcon icon){
        Integer[] tmp = new Integer[]{0,0,startValue};
        sumUpValue=startValue;
        values=new ArrayList<>(Arrays.stream(tmp).toList());
        this.icon=icon;
    }

    public int getSumUpValue(){return sumUpValue;}

    public void effect(GameCharacter character){throw new RuntimeException("Method not implemented!");}

    public ImageIcon getIcon() {
        return icon;
    }

    public void evaporate() throws StatusEvaporatedException {
        sumUpValue-=values.get(0);
        if (sumUpValue<0)
            throw new StatusEvaporatedException();
        values.remove(0);
        values.add(0);
    }

    public void addEffect(int value){
        values.set(2,values.get(2)+value);
        sumUpValue+=value;
    }
}
