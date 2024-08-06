package Dice.DiceAction;

import Character.GameCharacter;
import Dice.Dice;
import Dice.DiceFactory;
import Fight.Statuses.BonusDiceStatus;
import Game.GameBalance;
import Game.Tags;

public class AttackBonusAction implements DiceAction{
    private static final String id="AttackBonus";
    private static final String imagePath="StatusIcons/AttackDice.png";
    private final int value;
    private boolean actionOnSelf;

    public AttackBonusAction(int value,boolean actionOnSelf){
        this.actionOnSelf=actionOnSelf;
        this.value=value;
    }

    @Override
    public DiceAction sumAction(DiceAction action) {
        int newValue=value+ action.getValue();
        return new AttackBonusAction(newValue,actionOnSelf);
    }

    @Override
    public String actionDescription(String characterName, String targetName) {
        if(targetName==null)
            return characterName+" applied " +value + " of attack bonus to self.";
        return characterName+" applied " +value + " of attack bonus to " + targetName+".";
    }

    @Override
    public String actionDescription() {
        return "";
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
        int diceStrength = (character.getStrength() + GameBalance.DICE_BONUS_ADD_TO_STAT_MOD)/GameBalance.DICE_BONUS_DIVIDE + GameBalance.MIN_DICE_BONUS_VALUE;
        diceStrength = Math.min(diceStrength, GameBalance.MAX_DICE_VALUE);
        Dice dice = DiceFactory.buildDice(new int[][]{{0},{0},{0},{1,diceStrength},{1,diceStrength},{1,diceStrength}});
        character.addStatus(new BonusDiceStatus(value,imagePath,dice, Tags.ATTACK));
    }

    @Override
    public boolean onSelf(){
        return actionOnSelf;
    }
}
