package GUI;

import Dice.DiceAction.DiceAction;
import main.DiceMaster;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class RollPanel extends JPanel {
    private DiceMaster master;
    private JLabel reroll;

    public RollPanel(Border border){
        this.setLayout(new GridLayout(3,1));
        this.setSize(GUISettings.SMALL_PANEL_SIZE,GUISettings.SMALL_PANEL_SIZE);
        this.setBackground(Color.BLACK);
        this.setBorder(border);

        reroll=new JLabel("You have 2 rerolls.",SwingConstants.CENTER);
        reroll.setSize(GUISettings.SMALL_PANEL_SIZE,GUISettings.SMALL_PANEL_SIZE/3);
        reroll.setForeground(Color.WHITE);

        JButton rollButton=new JButton("Roll");
        rollButton.addActionListener(e->rollDicePool());

        JButton skipButton=new JButton("Skip and sum up");
        skipButton.addActionListener(e->sumUpDicePool());

        this.add(rollButton);
        this.add(reroll);
        this.add(skipButton);
    }

    public void setMaster(DiceMaster master){
        this.master=master;
    }

    public void rerollsChange(){
        reroll.setText("You have "+master.getRerolls()+" rerolls.");
    }

    private void rollDicePool(){
        if(master==null)
            return;
        master.roll();
        reroll.setText("You have "+master.getRerolls()+" rerolls.");
        this.revalidate();
    }

    private void sumUpDicePool(){
        ArrayList<DiceAction> actions=master.sumUpResults();
        StringBuilder res= new StringBuilder();
        for(DiceAction a:actions){
            res.append(a.action()).append(" ");
        }
        System.out.println(res);
        this.revalidate();
    }

}
