package Dice;

import Dice.DiceAction.*;
import GUI.GUISettings;

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
