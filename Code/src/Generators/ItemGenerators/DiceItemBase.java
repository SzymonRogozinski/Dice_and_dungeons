package Generators.ItemGenerators;

import Dice.ActionEnum;
import Fight.ActionTarget;
import Fight.GameActions.DiceLambda;
import Game.Tags;

import javax.swing.*;
import java.util.ArrayList;

public class DiceItemBase {
    final int[] secondaryActionList;
    final String attribute;
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
    public ImageIcon icon;

    public DiceItemBase(int[] firstActionValues, int firstAction, String[] names, ActionTarget target, int[] secondaryActionList, DiceLambda diceLambda, boolean actionOnSelf, ImageIcon icon,String attribute) {
        this.firstActionValues = firstActionValues;
        this.firstAction = firstAction;
        this.names = names;
        this.target = target;
        this.tags = new ArrayList<>();
        this.haveEmptySide=true;
        this.secondaryActionList = secondaryActionList;
        this.diceLambda=diceLambda;
        this.actionOnSelf=actionOnSelf;
        this.secondAction= ActionEnum.NULL_ACTION;
        this.icon=icon;
        this.attribute=attribute;
    }
}
