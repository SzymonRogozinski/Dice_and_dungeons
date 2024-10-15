package Fight.Statuses;

import Character.GameCharacter;
import Dice.Dice;
import Fight.DiceMaster;
import Fight.GameActions.GameAction;
import Game.Tags;

import javax.swing.*;

public class BonusDiceStatus extends GameStatus {
    private final static int maxDice = 3;
    private final Dice dice;
    private final Tags activationTag;
    private int diceNumber;

    public BonusDiceStatus(int diceNumber, String path, Dice dice, Tags activationTag) {
        super(diceNumber, new Integer[]{}, new ImageIcon(path), new Tags[]{Tags.BONUS_DICE});
        this.diceNumber = Math.min(diceNumber, maxDice);
        this.dice = dice;
        this.activationTag = activationTag;
    }

    @Override
    public void effect(GameCharacter character) {
    }

    @Override
    public void evaporate() throws StatusEvaporatedException {
        throw new StatusEvaporatedException();
    }

    @Override
    public void addEffect(int value) {
        diceNumber = Math.min(diceNumber + value, maxDice);
    }

    @Override
    public String info() {
        return "Bonus dice: " + diceNumber;
    }

    public void addBonusDice(DiceMaster master, GameAction action) {
        if (action.haveTag(activationTag))
            master.setBonusDicePool(dice, diceNumber);
    }
}
