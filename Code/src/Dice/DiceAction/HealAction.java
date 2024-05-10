package Dice.DiceAction;

import Character.GameCharacter;

public class HealAction implements DiceAction{
    private static final String id="Heal";
    private final int value;
    private boolean actionOnSelf;

    public HealAction(int value,boolean actionOnSelf){
        this.actionOnSelf=actionOnSelf;
        this.value=value;
    }

    @Override
    public DiceAction sumAction(DiceAction action) {
        int newValue=value+ action.getValue();
        return new HealAction(newValue,actionOnSelf);
    }

    @Override
    public String action() {
        return "Character heal " +value + "!";
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
        character.healDamage(value);
    }
}
