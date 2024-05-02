package Dice.DiceAction;

import Character.GameCharacter;

public class DamageAction implements DiceAction{
    private static final String id="Damage";
    private final int value;

    public DamageAction(int value){
        this.value=value;
    }

    @Override
    public DiceAction sumAction(DiceAction action) {
        int newValue=value+ action.getValue();
        return new DamageAction(newValue);
    }

    @Override
    public String action() {
        return "Attack hit for " +value + " damage!";
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
        character.dealDamage(value);
    }
    @Override
    public boolean onSelf(){
        return false;
    }
}
