package Dice.DiceAction;

import Fight.FightModule;
import Character.GameCharacter;

public class NullAction implements DiceAction{
    private static final String id=null;

    public NullAction(){}

    @Override
    public DiceAction sumAction(DiceAction action) {
        return new NullAction();
    }

    @Override
    public String action() {
        return null;
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
    public void doAction(GameCharacter character) {}
    @Override
    public boolean onSelf(){
        return true;
    }
}
