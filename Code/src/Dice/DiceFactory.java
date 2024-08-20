package Dice;

import Dice.DiceAction.*;
import GUI.GUISettings;
import Game.GameBalance;
import Generators.ItemGenerators.DiceItemBase;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DiceFactory {

    private static final int size= GUISettings.SMALL_PANEL_SIZE/2-3;
    private static final int ICON_SIZE= 40;
    private static final String SYMBOL_PATH = "Texture/DiceSymbols/";
    private static final String NUMBER_PATH = "Texture/DiceNumber/";

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
                if(value> GameBalance.MAX_DICE_VALUE)
                    value=GameBalance.MAX_DICE_VALUE;
                action1CostRest+=(base.firstActionValues[i]-value*cost);
                action1[i]=value;
            }
            //second action
            if(base.secondAction!=ActionEnum.NULL_ACTION && base.secondActionValues[i]>0){
                cost = ActionEnum.actionCost(base.secondAction);
                value = base.secondActionValues[i]/cost;
                if(value>GameBalance.MAX_DICE_VALUE)
                    value=GameBalance.MAX_DICE_VALUE;
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
                if(action1[i]<GameBalance.MAX_DICE_VALUE) {
                    action1[i]++;
                    action1CostRest -= cost;
                }else if(checkIfLocked(action1)){
                    action2CostRest += action1CostRest;
                    action1CostRest = 0;
                }
                i--;
                if(action1[i]==0)
                    i=5;
            }
        }
        //Add rest to second action
        //Calc returnPoint
        cost = ActionEnum.actionCost(base.secondAction);
        int returnPoint=-1;
        for(i=5;i>=0;i--){
            if(action2[i]>0){
                returnPoint=i;
                break;
            }
        }
        i = returnPoint;
        //returnPoint=0 -> No values for second action
        while(action2CostRest>0 && returnPoint>=0){
            if(cost>action2CostRest) {
                action2CostRest = 0;
            }else{
                if(action2[i]<GameBalance.MAX_DICE_VALUE) {
                    action2[i]++;
                    action2CostRest -= cost;
                }else if(checkIfLocked(action2)){
                    action2CostRest = 0;
                }
                i--;
                if(action2[2]==0)
                    i=returnPoint;
            }
        }
        int [][] ins = new int[6][];
        //Allocating memory
        for(i = 0;i<6;i++){
            int size = 1;
            if (action1[i]>0) {
                size++;
                if (base.firstAction == ActionEnum.COUNTER_ACTION || base.firstAction == ActionEnum.SHIELD_ACTION || base.firstAction == ActionEnum.HEAL_ACTION)
                    size++;
            }if (base.secondAction!=ActionEnum.NULL_ACTION && action2[i]>0) {
                size += (size == 1 ? 1 : 2);
                if (base.secondAction == ActionEnum.COUNTER_ACTION || base.secondAction == ActionEnum.SHIELD_ACTION || base.secondAction == ActionEnum.HEAL_ACTION)
                    size++;
            }
            ins[i]=new int[size];
        }
        //Putting values
        int j;
        for(i = 0;i<6;i++){
            j=0;
            //First action
            if(action1[i]==0 && action2[i]==0) {
                ins[i][j] = ActionEnum.NULL_ACTION;
                continue;
            }else if(action1[i]>0){
                ins[i][j++]= base.firstAction;
                ins[i][j++]=action1[i];
            }
            if(action1[i]>0 && (base.firstAction==ActionEnum.COUNTER_ACTION || base.firstAction==ActionEnum.SHIELD_ACTION || base.firstAction==ActionEnum.HEAL_ACTION))
                ins[i][j++]=base.actionOnSelf?1:0;
            //Second action
            if(base.secondAction!=ActionEnum.NULL_ACTION && action2[i]>0){
                ins[i][j++]= base.secondAction;
                ins[i][j++]=action2[i];
            }
            if(action2[i]>0 && (base.secondAction==ActionEnum.COUNTER_ACTION || base.secondAction==ActionEnum.SHIELD_ACTION || base.secondAction==ActionEnum.HEAL_ACTION))
                ins[i][j]=base.actionOnSelf?1:0;
        }
        return buildDice(ins);
    }

    private static DiceSide buildSide(int[] instruction){
        if(instruction.length==1) {   //Null action
            NullAction action = new NullAction();
            ArrayList<DiceAction> actions = new ArrayList<>();
            actions.add(action);
            return new DiceSide(actions,resizeIcon(SYMBOL_PATH+"N.png",size));
        }
        ArrayList<DiceAction> actions = new ArrayList<>();
        ArrayList<String> imageCode=new ArrayList<>();
        for(int i=0;i<instruction.length;i+=2){
            int actionType = instruction[i];
            switch (actionType){
                case ActionEnum.DAMAGE_ACTION -> {
                    actions.add(new DamageAction(instruction[i+1]));
                    imageCode.add("D");
                    imageCode.add(""+instruction[i+1]);
                }
                case ActionEnum.SHIELD_ACTION -> {
                    actions.add(new ShieldAction(instruction[i+1],instruction[i+2]==1));
                    imageCode.add("S");
                    imageCode.add(""+instruction[i+1]);
                    i++;
                }
                case ActionEnum.HEAL_ACTION -> {
                    actions.add(new HealAction(instruction[i+1],instruction[i+2]==1));
                    imageCode.add("H");
                    imageCode.add(""+instruction[i+1]);
                    i++;
                }
                case ActionEnum.MANA_ACTION -> {
                    actions.add(new ManaAction(instruction[i+1]));
                    imageCode.add("M");
                    imageCode.add(""+instruction[i+1]);
                }
                case ActionEnum.POISON_ACTION -> {
                    actions.add(new PoisonAction(instruction[i+1]));
                    imageCode.add("P");
                    imageCode.add(""+instruction[i+1]);
                }
                case ActionEnum.BLEEDING_ACTION -> {
                    actions.add(new BleedingAction(instruction[i+1]));
                    imageCode.add("B");
                    imageCode.add(""+instruction[i+1]);
                }
                case ActionEnum.WEAKNESS_ACTION -> {
                    actions.add(new WeaknessAction(instruction[i+1]));
                    imageCode.add("W");
                    imageCode.add(""+instruction[i+1]);
                }
                case ActionEnum.COUNTER_ACTION -> {
                    actions.add(new CounterAction(instruction[i+1],instruction[i+2]==1));
                    imageCode.add("C");
                    imageCode.add(""+instruction[i+1]);
                    i++;
                }
                default -> {
                    throw new RuntimeException("Illegal actionType: "+actionType);
                }
            }
        }
        return new DiceSide(actions,buildIcon(imageCode));
    }

    private static ImageIcon resizeIcon(String path,int size){
        return new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(size,size,java.awt.Image.SCALE_SMOOTH));
    }

    private static ImageIcon buildIcon(ArrayList<String> iconCode) {
        BufferedImage completeImage = new BufferedImage(ICON_SIZE,ICON_SIZE,BufferedImage.TYPE_INT_RGB);
        Graphics2D g = completeImage.createGraphics();
        g.setBackground(Color.WHITE);
        g.clearRect(0,0,ICON_SIZE,ICON_SIZE);
        try {
            if (iconCode.size() == 2) {         //One symbol
                BufferedImage symbol = ImageIO.read(new File(SYMBOL_PATH+iconCode.get(0)+".png"));
                BufferedImage value = ImageIO.read(new File(NUMBER_PATH+iconCode.get(1)+".png"));
                g.drawImage(value,1,1,null);
                g.drawImage(symbol,20,20,null);
            } else if (iconCode.size() == 4) {  //Two symbol
                BufferedImage symbol1 = ImageIO.read(new File(SYMBOL_PATH+iconCode.get(0)+".png"));
                BufferedImage value1 = ImageIO.read(new File(NUMBER_PATH+iconCode.get(1)+".png"));
                BufferedImage symbol2 = ImageIO.read(new File(SYMBOL_PATH+iconCode.get(2)+".png"));
                BufferedImage value2 = ImageIO.read(new File(NUMBER_PATH+iconCode.get(3)+".png"));
                g.drawImage(value1,1,1,null);
                g.drawImage(symbol1,20,1,null);
                g.drawImage(value2,20,20,null);
                g.drawImage(symbol2,1,20,null);

            } else {                            //Error
                throw new RuntimeException("iconCode has illegal number of elements: "+iconCode.size());
            }
        }catch (IOException e){
            throw new RuntimeException("Cannot find/open texture!");
        }
        g.setColor(Color.BLACK);
        g.draw(new Rectangle(39,39));
        g.dispose();
        return new ImageIcon(completeImage.getScaledInstance(size,size,java.awt.Image.SCALE_SMOOTH));
    }

    private static boolean checkIfLocked(int[]array){
        for(int i:array){
            if(i!=0 && i!=GameBalance.MAX_DICE_VALUE)
                return false;
        }
        return true;
    }
}
