package Dice.DiceAction;

import Character.GameCharacter;

public interface DiceAction {
    DiceAction sumAction(DiceAction action);
    String actionDescription(String characterName, String targetName);
    String actionDescription();
    int getValue();
    String getIdentification();
    void doAction(GameCharacter character);
    boolean onSelf();
}
