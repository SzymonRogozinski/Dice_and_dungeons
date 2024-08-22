package Dice.DiceAction;

import Character.GameCharacter;
import Fight.Statuses.StunStatus;

public class StunAction implements DiceAction{
    private static final String id="Stun";

    public StunAction(){}

    @Override
    public DiceAction sumAction(DiceAction action) {
        return this;
    }

    @Override
    public String actionDescription(String characterName, String targetName) {
        return characterName+" applied stun to " + targetName+".";
    }

    @Override
    public String actionDescription() {
        return "Apply stun.";
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
