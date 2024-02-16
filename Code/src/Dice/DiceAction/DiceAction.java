package Dice.DiceAction;

public interface DiceAction {
    DiceAction sumAction(DiceAction action);
    String action();
    int getValue();
    String getIdentification();
}
