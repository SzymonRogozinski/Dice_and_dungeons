package Generators;

import Equipment.Items.ActionItem;
import Equipment.Items.ItemQuality;
import Fight.GameActions.ItemAction;
import Game.GameCollection;
import Game.Tags;
import Dice.Dice;
import Dice.ActionEnum;
import Dice.DiceFactory;
import Generators.Dictionaries.ItemDictionary;

import javax.swing.*;
import java.util.Arrays;

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
        System.out.println("Points: "+points);

        int startPoints = points;
        int basePoints;
        // Item Concentrated
        if(quality == ItemQuality.COMMON)
            basePoints=points;
        else if(GameCollection.random.nextDouble()>=CONCENTRATED_PROP) {
            basePoints = points;
            System.out.println("Item concentrated");
        }else
            basePoints = GeneratorConst.MEDIUM_POINTS*GeneratorConst.COMMON_MOD;
        points-=basePoints;
        DiceItemBase base = DiceItemFrames.getRandomDiceItemBase(basePoints);

        System.out.println("Base: "+base.names[0]);
        System.out.println("On start");
        printBase(base);

        while (points>0){
            //Too little points to other action
            if(points<(int)(startPoints*EQUALITY_EDGE)){
                redistributePointsEqual(base,points);
                points=0;
            }else if(base.haveEmptySide && GameCollection.random.nextDouble()<REPLACE_EMPTY_SIDE_PROP){
                points-=replaceEmptySide(base,points);
                System.out.println("Add new side");
                printBase(base);
            }else{
                base.secondActionValues=new int[6];
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
        System.out.println("Second action distribution: addEffectEqualAll");
        int sidePoints = points/6;
        for(int i=0;i<6;i++)
            base.secondActionValues[i]=sidePoints;
        points-=(sidePoints*6);
        System.out.println("Give Away Points");
        System.out.println("Before");
        printBase(base);
        giveAwayPoints(base.secondActionValues,points,true);
        System.out.println("After");
        printBase(base);
    }

    private static void addEffectEqualEmptySides(DiceItemBase base,int points){
        System.out.println("Second action distribution: addEffectEqualEmptySides");
        int index = findFirstEmptySide(base.firstActionValues);
        int sidePoints = points/(index+1);
        for(int i=0;i<(index+1);i++)
            base.secondActionValues[i]=sidePoints;
        points-=(sidePoints*(index+1));
        System.out.println("Give Away Points");
        System.out.println("Before");
        printBase(base);
        giveAwayPoints(base.secondActionValues,points,true);
        System.out.println("After");
        printBase(base);
    }

    private static void addEffectEqualFullSides(DiceItemBase base,int points){
        System.out.println("Second action distribution: addEffectEqualFullSides");
        int index = findFirstEmptySide(base.firstActionValues);
        int sidePoints = points/(5-index);
        for(int i=index+1;i<6;i++)
            base.secondActionValues[i]=sidePoints;
        points-=(sidePoints*(5-index));
        System.out.println("Give Away Points");
        System.out.println("Before");
        printBase(base);
        giveAwayPoints(base.secondActionValues,points,true);
        System.out.println("After");
        printBase(base);
    }

    private static void addEffectProportional(DiceItemBase base,int points){
        System.out.println("Second action distribution: addEffectProportional");
        int index = findFirstEmptySide(base.firstActionValues);
        int sum=0;
        for(int i=index+1;i<6;i++)
            sum+=base.firstActionValues[i];

        int pointsCopy=points;
        for(int i=index+1;i<6;i++) {
            //Calculate proportional value
            int tmp = (int)(((double)(base.firstActionValues[i]) / sum) * pointsCopy);
            base.secondActionValues[i] = tmp;
            points-=tmp;
        }
        System.out.println("Give Away Points");
        System.out.println("Before");
        printBase(base);
        giveAwayPoints(base.secondActionValues,points,true);
        System.out.println("After");
        printBase(base);
    }

    private static void addEffectInverselyProportional(DiceItemBase base,int points){
        System.out.println("Second action distribution: addEffectInverselyProportional");
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
        int pointsCopy=points;
        for(int i=index+1;i<6;i++) {
            //Calculate proportional value
            int tmp = (int)((prop[i] / iSum) * pointsCopy);
            base.secondActionValues[i] = tmp;
            points-=tmp;
        }
        System.out.println("Give Away Points");
        System.out.println("Before");
        printBase(base);
        giveAwayPoints(base.secondActionValues,points,true);
        System.out.println("After");
        printBase(base);
    }

    private static void redistributePointsEqual(DiceItemBase base,int points){
        System.out.println("Redistribute Points Equal");
        System.out.println("Before");
        printBase(base);
        boolean isFirstValue=true;
        while(points>0){
            points = giveAwayPoints(isFirstValue?base.firstActionValues:base.secondActionValues,points,false);
            //If base use second action, change focused action
            if(base.secondAction!= ActionEnum.NULL_ACTION)
                isFirstValue=!isFirstValue;
        }
        System.out.println("After");
        printBase(base);
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
        if (base.secondaryActionList.length==0)
            return;
        base.secondAction = base.secondaryActionList[GameCollection.random.nextInt(base.secondaryActionList.length)];
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
            if(array[i]>0){
                goBackIndex=i;
                break;
            }
        }
        if(goBackIndex<0)
            goBackIndex=5;

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


    //TODO remove this, only for debug
    private static void printBase(DiceItemBase base){
        System.out.println("Action1: "+ base.firstAction);
        System.out.println("Values: " + Arrays.toString(base.firstActionValues));
        System.out.println("Action2: "+ base.secondAction);
        System.out.println("Values: " + (base.secondActionValues==null?"None":Arrays.toString(base.secondActionValues)));

    }

}
