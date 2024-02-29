package Dice;

import Dice.DiceAction.DiceAction;

import javax.swing.*;
import java.util.ArrayList;

public class DiceSide {
    private final ArrayList<DiceAction> actions;
    private final ImageIcon icon;

    public DiceSide(ArrayList<DiceAction> actions, ImageIcon icon){
        this.icon=icon;
        this.actions=actions;
    }

    public ArrayList<DiceAction> getValue(){
        return actions;
    }

    public ImageIcon getIcon(){
        return icon;
    }
}
