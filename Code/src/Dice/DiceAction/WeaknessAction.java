package Dice.DiceAction;

import Character.GameCharacter;
import Fight.Statuses.WeaknessStatus;

public class WeaknessAction implements DiceAction{
    private static final String id="Weakness";
    private final int value;

    public WeaknessAction(int value){
        this.value=value;
    }

    @Override
    public DiceAction sumAction(DiceAction action) {
        int newValue=value+ action.getValue();
        return new WeaknessAction(newValue);
    }

    @Override
    public String action() {
        return "Attack apply Weakness!";
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
        WeaknessStatus weak = new WeaknessStatus(value);
        character.addStatus(weak);
        weak.effect(character);
    }
    @Override
    public boolean onSelf(){
        return false;
    }
}
