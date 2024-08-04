package Dice.DiceAction;

import Fight.Statuses.BleedingStatus;
import Character.GameCharacter;

public class BleedingAction implements DiceAction{
    private static final String id="Bleeding";
    private final int value;

    public BleedingAction(int value){
        this.value=value;
    }

    @Override
    public DiceAction sumAction(DiceAction action) {
        int newValue=value+ action.getValue();
        return new BleedingAction(newValue);
    }

    @Override
    public String actionDescription(String characterName, String targetName) {
        return characterName+" applied " +value + " of bleeding to " + targetName+".";
    }

    @Override
    public String actionDescription() {
        return "Apply " +value + " of bleeding.";
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
    public void doAction(GameCharacter character) {
        BleedingStatus bleed = new BleedingStatus(value);
        character.addStatus(bleed);
        bleed.effect(character);
    }
    @Override
    public boolean onSelf(){
        return false;
    }
}
