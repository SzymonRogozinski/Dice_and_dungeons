package GUI;

import Dice.DiceAction.DiceAction;
import main.DiceMaster;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ControlPanel extends JPanel {
    private DiceMaster master;
    private JLabel reroll,rollLabel,sumUp;

    public ControlPanel(int width,int height){
        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(width,height));
        this.setBackground(Color.BLACK);
        this.setBounds(0,0,width,height);

        rollLabel=new JLabel("Click Roll, to roll 6 dices. Click Dice to reroll.");
        reroll=new JLabel("You have 2 rerolls.");

        sumUp=new JLabel("");

        JButton roolButton=new JButton("Roll");
        roolButton.addActionListener(e->rollDicePool());

        JButton skipButton=new JButton("Skip and sum up");
        skipButton.addActionListener(e->sumUpDicePool());

        this.add(rollLabel);
        this.add(roolButton);
        this.add(reroll);
        this.add(skipButton);
        this.add(sumUp);
    }

    public void setMaster(DiceMaster master){
        this.master=master;
    }

    public void rerollsChange(){
        reroll.setText("You have "+master.getRerolls()+" rerolls.");
    }

    private void rollDicePool(){
        sumUp.setText("");
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
        sumUp.setText(res.toString());
        this.revalidate();
    }

}
