package Fight.GameActions;

import Dice.Dice;
import Fight.ActionTarget;

public class SpellAction extends GameAction{

    private final int manaCost;

    public SpellAction(String name, Dice dice, ActionTarget target, DiceLambda countDice, int manaCost) {
        super(name, dice, target,countDice);
        this.manaCost=manaCost;
    }

    public int getManaCost() {
        return manaCost;
    }
}
