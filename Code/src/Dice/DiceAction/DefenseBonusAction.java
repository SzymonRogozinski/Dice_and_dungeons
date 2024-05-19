package Dice.DiceAction;

import Character.GameCharacter;
import Dice.Dice;
import Dice.DiceFactory;
import Fight.Statuses.BonusDiceStatus;
import Fight.Tags;

public class DefenseBonusAction implements DiceAction {

    private static final String id="DefenseBonus";
    private static final String imagePath="StatusIcons/DefendDice.png";
    private final int value;
    private boolean actionOnSelf;

    public DefenseBonusAction(int value,boolean actionOnSelf){
        this.actionOnSelf=actionOnSelf;
        this.value=value;
    }

    @Override
    public DiceAction sumAction(DiceAction action) {
        int newValue=value+ action.getValue();
        return new DefenseBonusAction(newValue,actionOnSelf);
    }

    @Override
    public String action() {
        return "Applied " +value + " of shield bonus!";
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
        int diceStrength = (character.getEndurance()+5)/10+2;
        Dice dice = DiceFactory.buildDice(new int[][]{{0},{0},{0},{2,diceStrength,1},{2,diceStrength,1},{2,diceStrength,1}});
        character.addStatus(new BonusDiceStatus(value,imagePath,dice, Tags.DEFENCE));
    }

    @Override
    public boolean onSelf(){
        return actionOnSelf;
    }
}
