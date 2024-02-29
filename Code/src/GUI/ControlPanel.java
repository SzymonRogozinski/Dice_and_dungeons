package GUI;

import main.DiceMaster;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {

    private DiceMaster master;
    private JLabel reroll,rollLabel;

    public ControlPanel(int width,int height){
        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(width,height));
        this.setBackground(Color.BLACK);
        this.setBounds(0,0,width,height);

        rollLabel=new JLabel("Click Roll, to roll 6 dices. Click Dice to reroll.");
        reroll=new JLabel("You have 2 rerolls.");

        JButton roolButton=new JButton("Roll");
        roolButton.addActionListener(e->rollDicePool());

        this.add(rollLabel);
        this.add(roolButton);
        this.add(reroll);
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

}
