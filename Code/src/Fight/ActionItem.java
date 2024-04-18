package Fight;

import Dice.Dice;

public class ActionItem {
    private final String name;
    private final Dice dice;

    public ActionItem(String name, Dice dice) {
        this.name = name;
        this.dice = dice;
    }

    public String getName() {
        return name;
    }

    public Dice getDice() {
        return dice;
    }
}
