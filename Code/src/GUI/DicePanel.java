package GUI;

import Dice.DiceSide;
import main.DiceMaster;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class DicePanel extends JPanel {

    private final ArrayList<JButton> diceButtons;
    private DiceMaster master;
    private final int diceIconSize;

    public DicePanel(int width,int height){
        diceIconSize= Math.min(width / 6, height);

        this.setPreferredSize(new Dimension(width,height));
        this.setBackground(Color.WHITE);
        this.setBounds(0,height,width,height);
        this.setLayout(new GridLayout(1,10));

        diceButtons=new ArrayList<>();
    }

    public int getDiceIconSize(){
        return diceIconSize;
    }

    public void setMaster(DiceMaster master){
        this.master=master;
    }

    public void showDiceResults(ArrayList<DiceSide> diceResults){
        this.removeAll();
        int i= 0;
        for(DiceSide res:diceResults){
            DiceButton button=new DiceButton(i++,res.getIcon());
            this.add(button);
        }
        this.revalidate();
    }

    private class DiceButton extends JButton {
        private final int index;

        public DiceButton(int index,ImageIcon icon) {
            super(icon);
            this.index=index;
            this.addActionListener(e->master.reroll(index));
        }
    }
}
