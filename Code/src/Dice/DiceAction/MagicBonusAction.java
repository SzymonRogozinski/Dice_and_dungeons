package Dice.DiceAction;

import Dice.Dice;
import Fight.Statuses.BonusDiceStatus;
import Fight.Tags;
import Character.GameCharacter;
import Dice.DiceFactory;

public class MagicBonusAction implements DiceAction{

    private static final String id="MagicBonus";
    private static final String imagePath="StatusIcons/SpellDice.png";
    private final int value;
    private boolean actionOnSelf;

    public MagicBonusAction(int value,boolean actionOnSelf){
        this.actionOnSelf=actionOnSelf;
        this.value=value;
    }

    @Override
    public DiceAction sumAction(DiceAction action) {
        int newValue=value+ action.getValue();
        return new MagicBonusAction(newValue,actionOnSelf);
    }

    @Override
    public String action() {
        return "Applied " +value + " of magic bonus!";
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
        int diceStrength = (character.getIntelligence()+5)/10+2;
        Dice dice = DiceFactory.buildDice(new int[][]{{0},{0},{0},{4,diceStrength},{4,diceStrength},{4,diceStrength}});
        character.addStatus(new BonusDiceStatus(value,imagePath,dice, Tags.MAGIC));
    }

    @Override
    public boolean onSelf(){
        return actionOnSelf;
    }
}
