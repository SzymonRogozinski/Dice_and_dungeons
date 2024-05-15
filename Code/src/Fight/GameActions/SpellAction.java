package Fight.GameActions;

import Dice.Dice;
import Fight.ActionTarget;
import Fight.Tags;

public class SpellAction extends GameAction{

    private final int manaCost;

    public SpellAction(String name, Dice dice, ActionTarget target, DiceLambda countDice, int manaCost, Tags[] tags) {
        super(name, dice, target,countDice,tags);
        this.manaCost=manaCost;
    }

    public int getManaCost() {
        return manaCost;
    }
}
