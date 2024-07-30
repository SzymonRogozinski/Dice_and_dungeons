package Generators;

import Equipment.Items.ActionItem;
import Equipment.Items.ItemQuality;
import Fight.GameActions.ItemAction;
import Game.GameCollection;
import Game.Tags;
import Generators.Dictionaries.DiceIemDictionary;
import Dice.Dice;
import Dice.DiceFactory;
import Generators.Dictionaries.ItemDictionary;

import javax.swing.*;

public class DiceItemGenerator extends Generator{

    private final static double CONCENTRATED_PROP = 0.9;
    private final static double REPLACE_EMPTY_SIDE_PROP = 0.5;
    private final static double EQUALITY_EDGE = 0.15;

    public static ActionItem generateItem(ItemQuality quality){
        if (quality==null)
            throw new IllegalArgumentException("Quality of item cannot be null!");

        int points;
        Tags tag=null;

        switch (quality){
            case COMMON -> points = getPoints(GeneratorConst.MEDIUM_POINTS*GeneratorConst.COMMON_MOD);
            case RARE -> {
                points = getPoints(GeneratorConst.MEDIUM_POINTS*GeneratorConst.RARE_MOD);
                //Add tag
                if (GameCollection.random.nextBoolean()) {
                    tag = getRandomTag();
                    points += GeneratorConst.TAG_BONUS*GeneratorConst.RARE_MOD;
                }
            }
            case LEGENDARY -> {
                points = getPoints(GeneratorConst.MEDIUM_POINTS*GeneratorConst.LEGENDARY_MOD)+GeneratorConst.TAG_BONUS*GeneratorConst.LEGENDARY_MOD;
                tag = getRandomTag();
            }
            default -> throw new RuntimeException("Quality not implemented");
        }
        int startPoints = points;
        int basePoints;
        // Item Concentrated
        if(quality == ItemQuality.COMMON)
            basePoints=points;
        else if(GameCollection.random.nextDouble()>=CONCENTRATED_PROP)
            basePoints = points;
        else
            basePoints = Math.min(GeneratorConst.MEDIUM_POINTS*GeneratorConst.COMMON_MOD,points);
        points-=basePoints;
        DiceItemBase base = DiceItemFrames.getRandomDiceItemBase(basePoints);

        while (points>0){
            //Too little points to other action
            if(points<(int)(startPoints*EQUALITY_EDGE)){
                redistributePointsEqual(base,points);
                points=0;
            }else if(base.haveEmptySide && GameCollection.random.nextDouble()<REPLACE_EMPTY_SIDE_PROP){
                points-=replaceEmptySide(base,points);
            }else{
                getRandomAction(base);
                addActionRandomly(base,points);
                points=0;
            }
        }

        String name = ItemDictionary.getNameFromItemBase(base);
        Dice dice = DiceFactory.buildDice(base);
        Tags[] actionTags= ItemDictionary.getTagsFromAction(base.firstAction,base.secondAction);

        ItemAction action=new ItemAction(dice,base.target,base.diceLambda,actionTags);
        ImageIcon icon = null; //TODO
        Tags [] tags = tag==null ? new Tags[]{}:new Tags[]{tag};
        return new ActionItem(action,tags,icon,name,quality);
    }

    private static void addActionRandomly(DiceItemBase base, int points){
        int bound = base.firstActionValues[0]==0?5:4;
        int roll=GameCollection.random.nextInt(bound);
        switch (roll){
            case 0 -> addEffectEqualAll(base,points);
            case 1 -> addEffectEqualFullSides(base,points);
            case 2 -> addEffectProportional(base,points);
            case 3 -> addEffectInverselyProportional(base,points);
            case 4 -> addEffectEqualEmptySides(base,points);
        }
    }

    private static void addEffectEqualAll(DiceItemBase base,int points){
        int sidePoints = points/6;
        for(int i=0;i<6;i++)
            base.secondaryActionType[i]=sidePoints;
        points-=(sidePoints*6);
        giveAwayPoints(base.secondaryActionType,points,true);
    }

    private static void addEffectEqualEmptySides(DiceItemBase base,int points){
        int index = findFirstEmptySide(base.firstActionValues);
        int sidePoints = points/(index+1);
        for(int i=0;i<(index+1);i++)
            base.secondaryActionType[i]=sidePoints;
        points-=(sidePoints*(index+1));
        giveAwayPoints(base.secondaryActionType,points,true);
    }

    private static void addEffectEqualFullSides(DiceItemBase base,int points){
        int index = findFirstEmptySide(base.firstActionValues);
        int sidePoints = points/(5-index);
        for(int i=index+1;i<6;i++)
            base.secondaryActionType[i]=sidePoints;
        points-=(sidePoints*(5-index));
        giveAwayPoints(base.secondaryActionType,points,true);
    }

    private static void addEffectProportional(DiceItemBase base,int points){
        int index = findFirstEmptySide(base.firstActionValues);
        int sum=0;
        for(int i=index+1;i<6;i++)
            sum+=base.firstActionValues[i];

        for(int i=index+1;i<6;i++) {
            //Calculate proportional value
            int tmp = (base.firstActionValues[i] / sum) * points;
            base.secondaryActionType[i] = tmp;
            points-=tmp;
        }
        giveAwayPoints(base.secondaryActionType,points,true);
    }

    private static void addEffectInverselyProportional(DiceItemBase base,int points){
        int index = findFirstEmptySide(base.firstActionValues);
        int sum=0;
        for(int i=index+1;i<6;i++)
            sum+=base.firstActionValues[i];

        //Invert proportion
        double[] prop=new double[6];
        double iSum=0;
        for(int i=index+1;i<6;i++) {
            double tmp = Math.pow((double) base.firstActionValues[i] / sum, -1);
            prop[i] = tmp;
            iSum+=tmp;
        }

        for(int i=index+1;i<6;i++) {
            //Calculate proportional value
            int tmp = (int)((prop[i] / iSum) * points);
            base.secondaryActionType[i] = tmp;
            points-=tmp;
        }
        giveAwayPoints(base.secondaryActionType,points,true);
    }

    private static void redistributePointsEqual(DiceItemBase base,int points){
        boolean isFirstValue=true;
        while(points>0){
            points = giveAwayPoints(isFirstValue?base.firstActionValues:base.secondActionValues,points,false);
            //If base use second action, change focused action
            if(base.secondAction!=0)
                isFirstValue=!isFirstValue;
        }
    }

    private static int replaceEmptySide(DiceItemBase base,int points){
        int index = findFirstEmptySide(base.firstActionValues);
        if(index==-1)
            return 0;
        int put = Math.min(points, base.firstActionValues[index+1]);
        base.firstActionValues[index]=put;
        return put;
    }

    private static void getRandomAction(DiceItemBase base){
        if (base.secondaryActionType.length==0)
            return;
        base.secondAction = base.secondaryActionType[GameCollection.random.nextInt(base.secondaryActionType.length)];
        if(base.secondAction == base.firstAction)
            getRandomAction(base);
    }

    private static int findFirstEmptySide(int[]array){
        int index;
        for(index=5;index>=0;index--){
            if(array[index]==0)
                break;
        }
        return index;
    }

    private static int giveAwayPoints(int[]array,int points, boolean isLooped){
        int goBackIndex=-1;
        for(int i=5;i>=0;i--){
            if(array[i]==0){
                goBackIndex=i;
                break;
            }
        }
        if(goBackIndex<0)
            throw new RuntimeException("Error, array cannot be empty!");

        int i=5;
        while(points>0){
            //If out of range or empty side, return index to top
            if(i<0 || (array[i]==0)){
                if(!isLooped)
                    break;
                else
                    i = goBackIndex;
            }
            array[i--]++;
            points--;
        }
        return points;
    }

}
