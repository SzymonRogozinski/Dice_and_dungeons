package Dice.DiceAction;

import Character.GameCharacter;

public class ManaAction implements DiceAction{
    private static final String id="Mana";
    private final int value;
    private boolean actionOnSelf=true;

    public ManaAction(int value){
        this.value=value;
    }

    @Override
    public DiceAction sumAction(DiceAction action) {
        int newValue=value+ action.getValue();
        return new ManaAction(newValue);
    }

    @Override
    public String actionDescription(String characterName, String targetName) {
        if(targetName==null)
            return characterName+" give " +value + " mana to self.";
        return characterName+" give " +value + " mana to " + targetName+".";
    }

    @Override
    public String actionDescription() {
        return "";
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
        character.gainMana(value);
    }
}
