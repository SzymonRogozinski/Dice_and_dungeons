package Fight.GameActions;

import Dice.Dice;
import Fight.ActionTarget;
import Fight.Tags;

public class ActionItem extends GameAction {
    public ActionItem(String name, Dice dice, ActionTarget target, DiceLambda countDice, Tags[] tags) {
        super(name, dice, target,countDice,tags);
    }
}
