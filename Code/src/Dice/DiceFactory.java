package Dice;

import Dice.DiceAction.*;
import GUI.GUISettings;
import Generators.DiceItemBase;

import javax.swing.*;
import java.util.ArrayList;

public class DiceFactory {

    private static final int size= GUISettings.SMALL_PANEL_SIZE/2;

    public static Dice buildDice(int[][] instruction){
        if(instruction.length!=6)
            throw new IllegalArgumentException("Cannot build dice from this instruction! Instruction is to short.");
        DiceSide[] sides=new DiceSide[6];
        for(int i=0;i<6;i++)
            sides[i]=buildSide(instruction[i]);
        return new Dice(sides);
    }

    public static Dice buildDice(DiceItemBase base){
        int [] action1=new int[6];
        int [] action2=new int[6];
        int action1CostRest=0;
        int action2CostRest=0;
        int cost,value,i;
        //Calc values
        for(i = 0;i<6;i++){
            //first action
            if(base.firstActionValues[i]>0){
                cost = ActionEnum.actionCost(base.firstAction);
                value = base.firstActionValues[i]/cost;
                if(value>20)
                    value=20;
                action1CostRest+=(base.firstActionValues[i]-value*cost);
                action1[i]=value;
            }
            //second action
            if(base.secondActionValues[i]>0){
                cost = ActionEnum.actionCost(base.secondAction);
                value = base.secondActionValues[i]/cost;
                if(value>20)
                    value=20;
                action2CostRest+=(base.secondActionValues[i]-value*cost);
                action2[i]=value;
            }
        }
        //Add rest to first action
        cost = ActionEnum.actionCost(base.firstAction);
        i =5;
        while(action1CostRest>0){
            if(cost>action1CostRest) {
                action2CostRest += action1CostRest;
                action1CostRest = 0;
            }else{
                action1[i--]++;
                action1CostRest-=cost;
                if(action1[i]==0)
                    i=5;
            }
        }
        //Add rest to second action
        //Calc returnPoint
        int returnPoint=-1;
        for(i=5;i>=0;i++){
            if(action2[i]>0){
                returnPoint=i;
                break;
            }
        }
        i = returnPoint;
        //returnPoint=0 -> No values for second action
        while(action2CostRest>0 && returnPoint>0){
            if(cost>action2CostRest) {
                action2CostRest = 0;
            }else{
                action2[i--]++;
                action2CostRest-=cost;
                if(action1[2]==0)
                    i=returnPoint;
            }
        }
        int [][] ins = new int[6][];
        //Allocating memory
        for(i = 0;i<6;i++){
            int size = 1;
            if (base.firstActionValues[i]>0)
                size++;
            if (base.secondActionValues[i]>0)
                size+= size==1?1:2;
            if (base.firstAction==ActionEnum.COUNTER_ACTION || base.firstAction==ActionEnum.SHIELD_ACTION )
                size++;
            if (base.secondAction==ActionEnum.COUNTER_ACTION || base.secondAction==ActionEnum.SHIELD_ACTION )
                size++;
            ins[i]=new int[size];
        }
        //Putting values
        int j;
        for(i = 0;i<6;i++){
            j=0;
            //First action
            if(base.firstAction==ActionEnum.NULL_ACTION)
                ins[i][j]=ActionEnum.NULL_ACTION;
            else{
                ins[i][j++]= base.firstAction;
                ins[i][j++]=action1[i];
            }
            if(base.firstAction==ActionEnum.COUNTER_ACTION || base.firstAction==ActionEnum.SHIELD_ACTION)
                ins[i][j++]=base.actionOnSelf?1:0;
            //Second action
            if(base.secondAction!=ActionEnum.NULL_ACTION){
                ins[i][j++]= base.secondAction;
                ins[i][j++]=action2[i];
            }
            if(base.secondAction==ActionEnum.COUNTER_ACTION || base.secondAction==ActionEnum.SHIELD_ACTION)
                ins[i][j]=base.actionOnSelf?1:0;
        }
        return buildDice(ins);
    }

    private static DiceSide buildSide(int[] instruction){
        if(instruction.length==1) {   //Null action
            NullAction action = new NullAction();
            ArrayList<DiceAction> actions = new ArrayList<>();
            actions.add(action);
            return new DiceSide(actions,resizeIcon("DiceIcons/N.png",size));
        }
        ArrayList<DiceAction> actions = new ArrayList<>();
        String imageCode="";
        for(int i=0;i<instruction.length;i+=2){
            int actionType = instruction[i];
            switch (actionType){
                case ActionEnum.DAMAGE_ACTION -> {
                    actions.add(new DamageAction(instruction[i+1]));
                    imageCode+="D"+instruction[i+1];
                }
                case ActionEnum.SHIELD_ACTION -> {
                    actions.add(new ShieldAction(instruction[i+1],instruction[i+2]==1));
                    imageCode+="S"+instruction[i+1];
                    i++;
                }
                case ActionEnum.HEAL_ACTION -> {
                    actions.add(new HealAction(instruction[i+1],instruction[i+2]==1));
                    imageCode+="H"+instruction[i+1];
                    i++;
                }
                case ActionEnum.MANA_ACTION -> {
                    actions.add(new ManaAction(instruction[i+1]));
                    imageCode+="M"+instruction[i+1];
                }
                case ActionEnum.POISON_ACTION -> {
                    actions.add(new PoisonAction(instruction[i+1]));
                    imageCode+="P"+instruction[i+1];
                }
                case ActionEnum.BLEEDING_ACTION -> {
                    actions.add(new BleedingAction(instruction[i+1]));
                    imageCode+="B"+instruction[i+1];
                }
                case ActionEnum.WEAKNESS_ACTION -> {
                    actions.add(new WeaknessAction(instruction[i+1]));
                    imageCode+="W"+instruction[i+1];
                }
                case ActionEnum.COUNTER_ACTION -> {
                    actions.add(new CounterAction(instruction[i+1],instruction[i+2]==1));
                    imageCode+="C"+instruction[i+1];
                    i++;
                }
            }
        }
        return new DiceSide(actions,resizeIcon("DiceIcons/"+imageCode+".png",size));
    }

    private static ImageIcon resizeIcon(String path,int size){
        return new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(size,size,java.awt.Image.SCALE_SMOOTH));
    }
}
