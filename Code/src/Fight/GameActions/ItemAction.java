package Fight.GameActions;

import Dice.Dice;
import Fight.ActionTarget;
import Game.Tags;

public class ItemAction extends GameAction {
    public ItemAction(Dice dice, ActionTarget target, DiceLambda countDice, Tags[] tags) {
        super(dice, target,countDice,tags);
    }
}
