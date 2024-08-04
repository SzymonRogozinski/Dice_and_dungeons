package Generators;

import Dice.ActionEnum;
import Fight.ActionTarget;
import Fight.GameActions.DiceLambda;
import Game.GameCollection;
import Character.PlayerCharacter;
import Game.Tags;

import java.util.ArrayList;
import java.util.Arrays;

public class DiceItemFrames {

    private final static DiceItemFrame[] FRAMES=new DiceItemFrame[]{
            new DiceItemFrame(new double[]{0,0,0.2,0.2,0.3,0.3}, ActionEnum.DAMAGE_ACTION,new String[]{"sword"},ActionTarget.ENEMY_CHARACTER,new int[]{ActionEnum.POISON_ACTION,ActionEnum.BLEEDING_ACTION,ActionEnum.WEAKNESS_ACTION},p->p.getDiceNumber(p.getStrength()),false),
            new DiceItemFrame(new double[]{0,0.2,0.2,0.2,0.2,0.2}, ActionEnum.HEAL_ACTION,new String[]{"bag of healing"},ActionTarget.PLAYER_CHARACTER,new int[]{ActionEnum.MANA_ACTION},p->p.getDiceNumber(p.getCharisma()),false)

    };

    public static DiceItemBase getRandomDiceItemBase(int points){
        int i = GameCollection.random.nextInt(FRAMES.length);
        return FRAMES[i].getDiceItemBase(points);
    }
    private static class DiceItemFrame {
        final double[] values;
        final int diceAction;
        final String[] names;
        final ActionTarget target;
        final int[] secondaryActionType;
        final DiceLambda diceLambda;
        final boolean actionOnSelf;

        DiceItemFrame(double[] values, int diceAction, String[] names, ActionTarget target,int[] secondaryActionType,DiceLambda diceLambda,boolean actionOnSelf) {
            this.values = values;
            this.diceAction = diceAction;
            this.names = names;
            this.target = target;
            this.secondaryActionType=secondaryActionType;
            this.diceLambda=diceLambda;
            this.actionOnSelf=actionOnSelf;
        }

        DiceItemBase getDiceItemBase(int points){
            //Count points
            int [] stats = new int[6];
            int count=0;
            for(int i=0;i<6;i++) {
                int s = (int) (values[i] * points);
                stats[i] = s;
                count+=s;
            }
            //Add rest
            int i=5;
            while(count<points){
                if(i<0 || stats[i]==0)
                    i=5;
                stats[i--]+=1;
                count++;
            }

            return new DiceItemBase(stats,diceAction,names,target,secondaryActionType,diceLambda,actionOnSelf);
        }
    }
}
