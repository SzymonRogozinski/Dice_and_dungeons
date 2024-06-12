package Fight.Statuses;

import Character.GameCharacter;
import Game.Tagable;
import Game.Tags;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class GameStatus extends Tagable {

    private final ArrayList<Integer> values;
    private int sumUpValue;
    private final ImageIcon icon;

    public GameStatus(int startValue,Integer[] startValues,ImageIcon icon,Tags[] tags){
        super(tags);
        sumUpValue=startValue;
        values=new ArrayList<>(Arrays.stream(startValues).toList());
        this.icon=icon;
    }

    public int getSumUpValue(){return sumUpValue;}

    public void effect(GameCharacter character) throws StatusException{throw new RuntimeException("Method not implemented!");}

    public String effectCommunicate(String name) { return null;}

    public ImageIcon getIcon() {
        return icon;
    }

    public void evaporate() throws StatusEvaporatedException {
        sumUpValue-=values.get(0);
        if (sumUpValue<=0)
            throw new StatusEvaporatedException();
        values.remove(0);
        values.add(0);
    }

    public void addEffect(int value){
        values.set(2,values.get(2)+value);
        sumUpValue+=value;
    }

    public String info(){
        return "Status: "+sumUpValue;
    }
}
