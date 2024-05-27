package Dice.DiceAction;

import Character.GameCharacter;

public class ShieldAction implements DiceAction{

    private static final String id="Shield";
    private final int value;
    private boolean actionOnSelf;

    public ShieldAction(int value,boolean actionOnSelf){
        this.actionOnSelf=actionOnSelf;
        this.value=value;
    }

    @Override
    public DiceAction sumAction(DiceAction action) {
        int newValue=value+ action.getValue();
        return new ShieldAction(newValue,actionOnSelf);
    }

    @Override
    public String actionDescription(String characterName, String targetName) {
        if(targetName==null)
            return characterName+" give " +value + " shield to self.";
        return characterName+" give " +value + " shield to " + targetName+".";
    }

    @Override
    public String actionDescription() {
        return "Apply " +value + " shield.";
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String getIdentification() {
        return id;
    }

    @Override
    public boolean onSelf() {
        return actionOnSelf;
    }

    @Override
    public void doAction(GameCharacter character) {
        character.addShield(value);
    }
}
