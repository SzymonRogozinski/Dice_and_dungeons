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
    public String[] names;
    public ActionTarget target;
    public ArrayList<Tags> tags;
    public boolean haveEmptySide;
    public DiceLambda diceLambda;
    public boolean actionOnSelf;

    public DiceItemBase(int[] firstActionValues, int firstAction, String[] names, ActionTarget target, ArrayList<Tags> tags,int[] secondaryActionType,DiceLambda diceLambda,boolean actionOnSelf) {
        this.firstActionValues = firstActionValues;
        this.firstAction = firstAction;
        this.names = names;
        this.target = target;
        this.tags = tags;
        this.haveEmptySide=true;
        this.secondaryActionType=secondaryActionType;
        this.diceLambda=diceLambda;
        this.actionOnSelf=actionOnSelf;
    }
}
