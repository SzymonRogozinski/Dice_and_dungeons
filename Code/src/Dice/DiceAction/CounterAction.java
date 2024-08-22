package Dice.DiceAction;

import Character.GameCharacter;
import Fight.Statuses.CounterStatus;

public class CounterAction implements DiceAction{

    private static final String id="Counter";
    private final int value;
    private boolean actionOnSelf;

    public CounterAction(int value,boolean actionOnSelf){
        this.actionOnSelf=actionOnSelf;
        this.value=value;
    }

    @Override
    public DiceAction sumAction(DiceAction action) {
        int newValue=value+ action.getValue();
        return new CounterAction(newValue,actionOnSelf);
    }

    @Override
    public String actionDescription(String characterName, String targetName) {
        if(targetName==null)
            return characterName+" applied " +value + " of counter to self.";
        return characterName+" applied " +value + " of counter to " + targetName+".";
    }

    @Override
    public String actionDescription() {
        return "Apply " +value + "of counter.";
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
        character.addStatus(new CounterStatus(value));
    }
    @Override
    public boolean onSelf(){
        return actionOnSelf;
    }
}
