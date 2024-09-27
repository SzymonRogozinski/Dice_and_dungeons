package GUI.FightGUI;

import Dice.DiceSide;
import GUI.GUISettings;
import Game.GameManager;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class DicePanel extends JPanel {
    private static final int diceNumber=12;
    private static final int diceRow=2;
    private static final int diceColumns=6;

    public DicePanel(Border border){
        this.setSize(GUISettings.PANEL_SIZE,GUISettings.SMALL_PANEL_SIZE);
        this.setLayout(new GridLayout(diceRow,diceColumns));
        this.setBorder(border);
        this.setBackground(Color.BLACK);
    }

    public void showDiceResults(ArrayList<DiceSide> diceResults){
        this.removeAll();
        int i= 0;
        for(DiceSide res:diceResults){
            DiceButton button=new DiceButton(i++,res.getIcon());
            this.add(button);
        }
        //Fulfill
        while(i<diceNumber){
            this.add(new JLabel());
            i++;
        }
        this.revalidate();
    }

    private class DiceButton extends JButton {

        public DiceButton(int index,ImageIcon icon) {
            super(icon);
            this.addActionListener(e-> GameManager.getFight().rerollDice(index));
        }
    }
}
