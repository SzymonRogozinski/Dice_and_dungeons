package Fight;

import Dice.Dice;

public class ActionItem {
    private final String name;
    private final Dice dice;
    private final ActionTarget target;

    public ActionItem(String name, Dice dice,ActionTarget target) {
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
