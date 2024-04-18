package main;

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
    private final FightModule fight;

    public DiceMaster(FightModule fight) {
        this.fight=fight;
    }

    public DiceMaster(FightModule fight, Dice dice, int diceNumber, int reroll) {
        this.fight=fight;
        this.pool=new DicePool(dice,diceNumber);
        this.reroll=reroll;
    }

    public void setDicePool(Dice dice, int diceNumber, int reroll){
        this.pool=new DicePool(dice,diceNumber);
        this.reroll=reroll;
    }

    public int getRerolls(){
        return reroll;
    }

    public ArrayList<DiceSide> getResults(){
        return pool.getRollResult();
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
    }
}
