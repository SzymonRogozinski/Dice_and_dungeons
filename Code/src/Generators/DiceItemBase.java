package Generators;

import Fight.ActionTarget;
import Game.Tags;

import java.util.ArrayList;

public class DiceItemBase {
    public int[] firstActionValues;
    public int firstAction;
    public int[] secondActionValues;
    public int secondAction;
    public String name;
    public ActionTarget target;
    public ArrayList<Tags> tags;

    public DiceItemBase(int[] firstActionValues, int firstAction, String name, ActionTarget target, ArrayList<Tags> tags) {
        this.firstActionValues = firstActionValues;
        this.firstAction = firstAction;
        this.name = name;
        this.target = target;
        this.tags = tags;
    }
}
