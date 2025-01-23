package Dice.DiceAction;

import Character.GameCharacter;

public class NullAction implements DiceAction {
    private static final String id = null;

    public NullAction() {
    }

    @Override
    public DiceAction sumAction(DiceAction action) {
        return new NullAction();
    }

    @Override
    public String actionDescription(String characterName, String targetName) {
        return "";
    }

    @Override
    public String actionDescription() {
        return "";
    }

    @Override
    public int getValue() {
        return 0;
    }

    @Override
    public String getIdentification() {
        return id;
    }

    @Override
    public void doAction(GameCharacter character) {
    }

    @Override
    public boolean onSelf() {
        return true;
    }
}
