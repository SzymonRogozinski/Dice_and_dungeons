package Dice;

import java.util.Random;

public class Dice {
    public static final Random globalRandom=new Random();
    private DiceSide[] sides;

    public Dice(DiceSide[] sides){
        this.sides=sides;
    }

    public DiceSide roll(){
        int rollValue= Dice.globalRandom.nextInt(6);
        return sides[rollValue];
    }

    public DiceSide[] getSides() {
        return sides;
    }
}
