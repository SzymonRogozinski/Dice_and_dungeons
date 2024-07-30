package Generators;

import Fight.ActionTarget;
import Fight.GameActions.DiceLambda;
import Game.GameCollection;
import Game.Tags;

import java.util.ArrayList;
import java.util.Arrays;

public class DiceItemFrames {

    private final static DiceItemFrame[] FRAMES=new DiceItemFrame[]{};

    public static DiceItemBase getRandomDiceItemBase(int points){
        int i = GameCollection.random.nextInt(FRAMES.length);
        return FRAMES[i].getDiceItemBase(points);
    }
    private class DiceItemFrame {
        final double[] values;
        final int diceAction;
        final String[] names;
        final ActionTarget target;
        final Tags[] tags;
        final int[] secondaryActionType;
        final DiceLambda diceLambda;
        final boolean actionOnSelf;

        DiceItemFrame(double[] values, int diceAction, String[] names, ActionTarget target, Tags[] tags,int[] secondaryActionType,DiceLambda diceLambda,boolean actionOnSelf) {
            this.values = values;
            this.diceAction = diceAction;
            this.names = names;
            this.target = target;
            this.tags = tags;
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
            ArrayList<Tags> tagsArrayList=new ArrayList<>();
            tagsArrayList.addAll(Arrays.asList(tags));

            return new DiceItemBase(stats,diceAction,names,target,tagsArrayList,secondaryActionType,diceLambda,actionOnSelf);
        }
    }
}
