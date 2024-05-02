package Fight.GameActions;

import Dice.Dice;
import Fight.ActionTarget;

public class ActionItem extends GameAction {
    public ActionItem(String name, Dice dice, ActionTarget target, DiceLambda countDice) {
        super(name, dice, target,countDice);
    }
}
