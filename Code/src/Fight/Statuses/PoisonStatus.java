package Fight.Statuses;

import javax.swing.*;
import Character.GameCharacter;

public class PoisonStatus extends GameStatus{
    public PoisonStatus(int startValue, ImageIcon icon) {
        super(startValue, icon);
    }

    @Override
    public void effect(GameCharacter character){
        character.dealDamage(getSumUpValue());
    }
}
