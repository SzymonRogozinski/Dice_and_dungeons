package Fight.GameActions;

import Dice.Dice;
import Fight.ActionTarget;

public abstract class GameAction {

    private final String name;
    private final Dice dice;
    private final ActionTarget target;

    public GameAction(String name, Dice dice,ActionTarget target) {
        this.name = name;
        this.dice = dice;
        this.target = target;
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
}
