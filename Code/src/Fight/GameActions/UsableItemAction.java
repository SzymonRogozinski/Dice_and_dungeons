package Fight.GameActions;

import Dice.DiceAction.DiceAction;
import Fight.ActionTarget;
import Game.Tags;

import java.util.ArrayList;

public class UsableItemAction extends GameAction{

    private final ArrayList<DiceAction> constActions;

    public UsableItemAction(ActionTarget target,ArrayList<DiceAction> constActions, Tags[] tags) {
        super(null, target, null,tags);
        this.constActions=constActions;
    }

    @Override
    public ArrayList<DiceAction> getActionFactories(){
        return constActions;
    }
}
