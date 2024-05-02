package Fight.GameActions;

import Dice.Dice;
import Fight.ActionTarget;
import Character.PlayerCharacter;

public abstract class GameAction {

    private final String name;
    private final Dice dice;
    private final ActionTarget target;
    private final DiceLambda countDice;

    public GameAction(String name, Dice dice, ActionTarget target, DiceLambda countDice) {
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

}
