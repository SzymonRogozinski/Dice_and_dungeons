package Fight.GameActions;

import Character.PlayerCharacter;
import Dice.Dice;
import Dice.DiceAction.DiceAction;
import Fight.ActionTarget;
import Game.Taggable;
import Game.Tags;

import java.util.ArrayList;

public abstract class GameAction extends Taggable {

    private final Dice dice;
    private final ActionTarget target;
    private final DiceLambda countDice;

    public GameAction(Dice dice, ActionTarget target, DiceLambda countDice, Tags[] tags) {
        super(tags);
        this.dice = dice;
        this.target = target;
        this.countDice = countDice;
    }

    public Dice getDice() {
        return dice;
    }

    public ActionTarget getTarget() {
        return target;
    }

    public int getDiceNumber(PlayerCharacter p) {
        return Math.min(countDice.calcDice(p), 12);
    }

    public ArrayList<DiceAction> getActionFactories() {
        throw new RuntimeException("Method not implemented!");
    }

}
