package Fight.GameActions;

import Dice.Dice;
import Fight.ActionTarget;
import Fight.GameActions.GameAction;

public class ActionItem extends GameAction {
    public ActionItem(String name, Dice dice, ActionTarget target) {
        super(name, dice, target);
    }
}
