package Fight.GameActions;

import Dice.Dice;
import Dice.DiceAction.DiceAction;
import Fight.ActionTarget;
import Character.PlayerCharacter;
import Fight.Tagable;
import Fight.Tags;

import java.util.ArrayList;

public abstract class GameAction extends Tagable {

    private final String name;
    private final Dice dice;
    private final ActionTarget target;
    private final DiceLambda countDice;

    public GameAction(String name, Dice dice, ActionTarget target, DiceLambda countDice, Tags[] tags) {
        super(tags);
        this.name = name;
        this.dice = dice;
        this.target = target;
        this.countDice = countDice;
    }

    public String getName() {
        return name;
    }

    public Dice getDice() {
        return dice;
    }

    public ActionTarget getTarget() {
        return target;
    }

    public int getDiceNumber(PlayerCharacter p){
        return countDice.myMethod(p);
    }

    public ArrayList<DiceAction> getConstActions(){throw new RuntimeException("Method not implemented!");}

}
