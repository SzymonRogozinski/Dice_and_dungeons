package Dice.DiceAction;

import Character.GameCharacter;
import Fight.Statuses.PoisonStatus;

public class PoisonAction implements DiceAction{

    private static final String id="Poison";
    private final int value;

    public PoisonAction(int value){
        this.value=value;
    }

    @Override
    public DiceAction sumAction(DiceAction action) {
        int newValue=value+ action.getValue();
        return new PoisonAction(newValue);
    }

    @Override
    public String action() {
        return "Attack apply " +value + " of poison!";
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
        character.addStatus(new PoisonStatus(value));
    }
    @Override
    public boolean onSelf(){
        return false;
    }
}
