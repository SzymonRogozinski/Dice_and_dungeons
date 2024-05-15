package Dice.DiceAction;

import Fight.Statuses.StunStatus;
import Character.GameCharacter;

public class StunAction implements DiceAction{
    private static final String id="Stun";

    public StunAction(){}

    @Override
    public DiceAction sumAction(DiceAction action) {
        return this;
    }

    @Override
    public String action() {
        return "Attack stunned enemy!";
    }

    @Override
    public int getValue() {
        return 1;
    }

    @Override
    public String getIdentification() {
        return id;
    }

    @Override
    public void doAction(GameCharacter character) {
        character.addStatus(new StunStatus());
    }
    @Override
    public boolean onSelf(){
        return false;
    }
}
