package Dice;

import Dice.DiceAction.DiceAction;
import Dice.DiceAction.NullAction;

import java.util.ArrayList;

public class DicePool {
    private final Dice dice;
    private final int size;
    private ArrayList<DiceSide> rollResult;

    public DicePool(Dice dice, int size) {
        this.dice = dice;
        this.size = size;
        this.rollResult = null;
    }

    public void roll() {
        rollResult = new ArrayList<>();
        for (int i = 0; i < size; i++)
            rollResult.add(dice.roll());
    }

    public void reroll(int i) {
        rollResult.set(i, dice.roll());
    }

    public int getDiceNumber() {
        return size;
    }

    public ArrayList<DiceSide> getRollResult() {
        return rollResult;
    }

    public ArrayList<DiceAction> sumUp() {
        ArrayList<DiceAction> actions = new ArrayList<>();
        for (DiceSide side : rollResult) {
            actions.addAll(side.getValue());
        }
        actions.removeIf(action -> action instanceof NullAction);
        ArrayList<DiceAction> SumOfActions = new ArrayList<>();
        for (DiceAction action : actions) {
            DiceAction foundAction = SumOfActions.stream().filter((a -> a.getIdentification().equals(action.getIdentification()))).findFirst().orElse(null);
            if (foundAction == null)
                SumOfActions.add(action);
            else {
                SumOfActions.set(SumOfActions.indexOf(foundAction), foundAction.sumAction(action));
            }
        }
        return SumOfActions;
    }
}
