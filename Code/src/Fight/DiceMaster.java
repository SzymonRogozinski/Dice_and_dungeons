package Fight;

import Dice.Dice;
import Dice.DiceAction.DiceAction;
import Dice.DicePool;
import Dice.DiceSide;

import java.util.ArrayList;

public class DiceMaster {
    private int reroll;
    private DicePool pool;
    private DicePool bonusPool;
    private ArrayList<DiceAction> sumUpActions;

    public DiceMaster() {}

    public DiceMaster(Dice dice, int diceNumber, int reroll) {
        pool=new DicePool(dice,diceNumber);
        this.reroll=reroll;
    }

    public void setDicePool(Dice dice, int diceNumber, int reroll){
        pool=new DicePool(dice,diceNumber);
        this.reroll=reroll;
        sumUpActions=null;
        bonusPool=null;
    }

    public void setBonusDicePool(Dice dice, int diceNumber){
        if(pool.getDiceNumber()+diceNumber>12){
            diceNumber=12-pool.getDiceNumber();
        }
        bonusPool=new DicePool(dice,diceNumber);
    }

    public void setResult(ArrayList<DiceAction> actions){
        pool=null;
        bonusPool=null;
        sumUpActions=actions;
    }

    public int getRerolls(){
        return reroll;
    }

    public ArrayList<DiceSide> getResults(){
        if (pool==null)
            return null;
        else if(bonusPool==null)
            return pool.getRollResult();
        else{
            ArrayList<DiceSide> result = new ArrayList<>(pool.getRollResult());
            result.addAll(bonusPool.getRollResult());
            return result;
        }
    }

    public void roll(){
        pool.roll();
        if(bonusPool!=null)
            bonusPool.roll();
    }

    public void reroll(int i){
        if(reroll<=0) {
        }
        else if(i<pool.getDiceNumber()) {
            pool.reroll(i);
            reroll--;
        }else{
            i-=pool.getDiceNumber();
            bonusPool.reroll(i);
            reroll--;
        }
    }

    public void sumUpResults(){
        ArrayList<DiceAction> actions = pool.sumUp();
        if(bonusPool!=null){
            actions.addAll(bonusPool.sumUp());
        }
        sumUpActions=actions;
    }

    public ArrayList<DiceAction> getSumUpResults(){
        return sumUpActions;
    }
}
