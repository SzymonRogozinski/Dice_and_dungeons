package Fight.Statuses;

import Character.GameCharacter;
import Game.Tags;

import javax.swing.*;

public class BleedingStatus extends GameStatus {

    private int statusCount;

    public BleedingStatus(int startValue) {
        super(startValue, new Integer[]{}, new ImageIcon("Texture/StatusIcons/Bleeding.png"), new Tags[]{Tags.AFTER_TURN_START});
        statusCount = startValue;
    }

    @Override
    public void effect(GameCharacter character) {
        character.multiplyMod(1, 1.5);
    }

    @Override
    public void evaporate() throws StatusEvaporatedException {
        statusCount--;
        if (statusCount < 0)
            throw new StatusEvaporatedException();
    }

    @Override
    public void addEffect(int value) {
        statusCount += value;
    }

    @Override
    public String info() {
        return "Bleeding: " + (statusCount + 1);
    }
}
