package Fight;

import Dice.Dice;
import Dice.DiceAction.DamageAction;
import Dice.DiceAction.DiceAction;
import Dice.DiceAction.NullAction;
import Dice.DiceSide;
import Dice.DicePool;
import GUI.GUISettings;

import javax.swing.*;
import java.util.ArrayList;

public class DiceMaster {
    private int reroll;
    private DicePool pool;
    private ArrayList<DiceAction> sumUpActions;

    public DiceMaster() {}

    public DiceMaster(Dice dice, int diceNumber, int reroll) {
        this.pool=new DicePool(dice,diceNumber);
        this.reroll=reroll;
    }

    public void setDicePool(Dice dice, int diceNumber, int reroll){
        this.pool=new DicePool(dice,diceNumber);
        this.reroll=reroll;
        sumUpActions=null;
    }

    public void setResult(ArrayList<DiceAction> actions){
        pool=null;
        sumUpActions=actions;
    }

    public int getRerolls(){
        return reroll;
    }

    public ArrayList<DiceSide> getResults(){
        return pool==null?null:pool.getRollResult();
    }

    public void roll(){
        pool.roll();
    }

    public void reroll(int i){
        if(reroll>0) {
            pool.reroll(i);
            reroll--;
        }
    }

    public void sumUpResults(){
        ArrayList<DiceAction> actions = pool.sumUp();
        StringBuilder res= new StringBuilder();
        for(DiceAction a:actions){
            res.append(a.action()).append(" ");
        }
        System.out.println(res);
        sumUpActions=actions;
    }

    public ArrayList<DiceAction> getSumUpResults(){
        return sumUpActions;
    }
}
