package Dice.DiceAction;

import Character.GameCharacter;

public interface DiceAction {
    DiceAction sumAction(DiceAction action);
    String action();
    int getValue();
    String getIdentification();
    void doAction(GameCharacter character);
    boolean onSelf();
}
