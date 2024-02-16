package Dice;

import Dice.DiceAction.DiceAction;

import java.util.ArrayList;

public class DiceSide {
    private final ArrayList<DiceAction> actions;

    public DiceSide(ArrayList<DiceAction> actions){
        this.actions=actions;
    }

    public ArrayList<DiceAction> getValue(){
        return actions;
    }

    public String getIcon(){
        String res="";
        for(DiceAction action:actions){
            res+=action.action()+" ";
        }
        return res;
    }
}
