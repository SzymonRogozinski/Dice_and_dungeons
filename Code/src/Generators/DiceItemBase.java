package Generators;

import Fight.ActionTarget;
import Fight.GameActions.DiceLambda;
import Game.Tags;

import java.util.ArrayList;

public class DiceItemBase {
    final int[] secondaryActionType;
    public int[] firstActionValues;
    public int firstAction;
    public int[] secondActionValues;
    public int secondAction;
    public String name;
    public ActionTarget target;
    public ArrayList<Tags> tags;
    public boolean haveEmptySide;
    public DiceLambda diceLambda;

    public DiceItemBase(int[] firstActionValues, int firstAction, String name, ActionTarget target, ArrayList<Tags> tags,int[] secondaryActionType,DiceLambda diceLambda) {
        this.firstActionValues = firstActionValues;
        this.firstAction = firstAction;
        this.name = name;
        this.target = target;
        this.tags = tags;
        this.haveEmptySide=true;
        this.secondaryActionType=secondaryActionType;
        this.diceLambda=diceLambda;
    }
}
