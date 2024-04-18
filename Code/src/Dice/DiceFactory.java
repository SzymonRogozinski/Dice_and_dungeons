package Dice;

import Dice.DiceAction.DamageAction;
import Dice.DiceAction.DiceAction;
import Dice.DiceAction.NullAction;
import GUI.GUISettings;

import javax.swing.*;
import java.util.ArrayList;

public class DiceFactory {
    public static final int NULL_ACTION=0;
    public static final int DAMAGE_ACTION=1;

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
            return new DiceSide(actions,resizeIcon("DiceIcons/NullSide.png",size));
        }
        ArrayList<DiceAction> actions = new ArrayList<>();
        String imageCode="";
        for(int i=0;i<instruction.length;i+=2){
            int actionType = instruction[i];
            switch (actionType){
                case NULL_ACTION -> {
                    actions.add(new NullAction());
                    imageCode+="N";
                }
                case DAMAGE_ACTION -> {
                    actions.add(new DamageAction(instruction[i+1]));
                    imageCode+="D"+instruction[i+1];
                }
            }
        }
        return new DiceSide(actions,resizeIcon("DiceIcons/"+imageCode+".png",size));
    }

    private static ImageIcon resizeIcon(String path,int size){
        return new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(size,size,java.awt.Image.SCALE_SMOOTH));
    }
}
