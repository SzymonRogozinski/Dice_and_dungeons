package Dice;

import Game.GameManager;

public record Dice(DiceSide[] sides) {

    public DiceSide roll() {
        int rollValue = GameManager.getRandom().nextInt(6);
        return sides[rollValue];
    }
}
