package main;

import Dice.Dice;
import Dice.DiceAction.DamageAction;
import Dice.DiceAction.DiceAction;
import Dice.DiceAction.NullAction;
import Dice.DiceSide;
import Dice.DicePool;
import GUI.GUISettings;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class DiceMaster {
    private static final int maxRerolls=2;
    private int reroll;
    private DicePool pool;
    private final FightModule fight;

    public DiceMaster(FightModule fight) {
        this.fight=fight;
        DamageAction dmg = new DamageAction(4);
        DamageAction bigdmg = new DamageAction(6);
        NullAction none = new NullAction();

        int size= GUISettings.SMALL_PANEL_SIZE/2;
        DiceSide dmgSide = new DiceSide(new ArrayList<>(List.of(dmg)), resizeIcon("DiceIcons/Damage4.png",size));
        DiceSide bigdmgSide = new DiceSide(new ArrayList<>(List.of(bigdmg)), resizeIcon("DiceIcons/Damage6.png",size));
        DiceSide noneSide = new DiceSide(new ArrayList<>(List.of(none)), resizeIcon("DiceIcons/NullSide.png",size));

        Dice dice = new Dice(new DiceSide[]{noneSide, noneSide, noneSide, dmgSide, dmgSide, bigdmgSide});

        pool = new DicePool(dice, 6);
        reroll = maxRerolls;
    }

    private ImageIcon resizeIcon(String path,int size){
        return new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(size,size,java.awt.Image.SCALE_SMOOTH));
    }

    public int getRerolls(){
        return reroll;
    }

    public ArrayList<DiceSide> getResults(){
        return pool.getRollResult();
    }

    public void roll(){
        reroll=maxRerolls;
        pool.roll();
        fight.getDicePanel().showDiceResults(pool.getRollResult());
    }

    public void reroll(int i){
        if(reroll>0) {
            pool.reroll(i);
            reroll--;
            fight.getDicePanel().showDiceResults(pool.getRollResult());
            fight.getControlPanel().rerollsChange();
        }
    }

    public ArrayList<DiceAction> sumUpResults(){
        return pool.sumUp();
    }
}
