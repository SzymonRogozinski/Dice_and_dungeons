package Fight.GameActions;

import Dice.Dice;
import Fight.ActionTarget;
import Game.Tags;

public class SpellAction extends GameAction {

    private final int manaCost;

    public SpellAction(Dice dice, ActionTarget target, DiceLambda countDice, int manaCost, Tags[] tags) {
        super(dice, target, countDice, tags);
        this.manaCost = manaCost;
    }

    public int getManaCost() {
        return manaCost;
    }
}
