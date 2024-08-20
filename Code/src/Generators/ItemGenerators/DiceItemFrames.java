package Generators.ItemGenerators;

import Dice.ActionEnum;
import Fight.ActionTarget;
import Fight.GameActions.DiceLambda;
import Game.GameCollection;

import javax.swing.*;

public class DiceItemFrames {

    private final static DiceItemFrame[] DICE_ITEM_FRAMES =new DiceItemFrame[]{
            new DiceItemFrame(new double[]{0,0,0.2,0.2,0.3,0.3}, ActionEnum.DAMAGE_ACTION,new String[]{"sword"},ActionTarget.ENEMY_CHARACTER,new int[]{ActionEnum.POISON_ACTION,ActionEnum.BLEEDING_ACTION,ActionEnum.WEAKNESS_ACTION},p->p.getDiceNumber(p.getStrength()),false, new ImageIcon("Texture/Items/sword.png")),
            new DiceItemFrame(new double[]{0,0.2,0.2,0.2,0.2,0.2}, ActionEnum.HEAL_ACTION,new String[]{"bag of healing"},ActionTarget.PLAYER_CHARACTER,new int[]{ActionEnum.MANA_ACTION},p->p.getDiceNumber(p.getCharisma()),false, new ImageIcon("Texture/Items/bag_healing.png"))
    };

    private final static DiceItemFrame[] SPELL_FRAMES =new DiceItemFrame[]{
            new DiceItemFrame(new double[]{0,0,0,0.2,0.4,0.4}, ActionEnum.DAMAGE_ACTION,new String[]{"fire ball"},ActionTarget.ENEMY_CHARACTER,new int[]{ActionEnum.WEAKNESS_ACTION},p->p.getDiceNumber(p.getIntelligence()),false,new ImageIcon("Texture/Items/fireball.png")),
    };

    public static DiceItemBase getRandomDiceItemBase(int points){
        int i = GameCollection.random.nextInt(DICE_ITEM_FRAMES.length);
        return DICE_ITEM_FRAMES[i].getDiceItemBase(points);
    }

    public static DiceItemBase getRandomSpellItemBase(int points){
        int i = GameCollection.random.nextInt(SPELL_FRAMES.length);
        return SPELL_FRAMES[i].getDiceItemBase(points);
    }

    private static class DiceItemFrame {
        final double[] values;
        final int diceAction;
        final String[] names;
        final ActionTarget target;
        final int[] secondaryActionType;
        final DiceLambda diceLambda;
        final boolean actionOnSelf;
        final ImageIcon icon;

        DiceItemFrame(double[] values, int diceAction, String[] names, ActionTarget target,int[] secondaryActionType,DiceLambda diceLambda,boolean actionOnSelf,ImageIcon icon) {
            this.values = values;
            this.diceAction = diceAction;
            this.names = names;
            this.target = target;
            this.secondaryActionType=secondaryActionType;
            this.diceLambda=diceLambda;
            this.actionOnSelf=actionOnSelf;
            this.icon=icon;
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

            return new DiceItemBase(stats,diceAction,names,target,secondaryActionType,diceLambda,actionOnSelf,icon);
        }
    }
}
