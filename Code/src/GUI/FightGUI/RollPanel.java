package GUI.FightGUI;

import GUI.GUISettings;
import Game.GameManager;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class RollPanel extends JPanel {
    private JLabel reroll;

    public RollPanel(Border border){
        this.setLayout(new GridLayout(3,1));
        this.setSize(GUISettings.SMALL_PANEL_SIZE,GUISettings.SMALL_PANEL_SIZE);
        this.setBackground(Color.BLACK);
        this.setBorder(border);

        reroll=new JLabel("",SwingConstants.CENTER);
        reroll.setSize(GUISettings.SMALL_PANEL_SIZE,GUISettings.SMALL_PANEL_SIZE/3);
        reroll.setForeground(Color.WHITE);

        JButton skipButton=new JButton("Skip and sum up");
        skipButton.addActionListener(e->{
            if(!GameManager.getFight().isDiceResultNull())
                sumUpDicePool();
        });

        this.add(reroll);
        this.add(skipButton);
    }


    public void rerollsChange(){
        reroll.setText("You have "+ GameManager.getFight().getRerolls()+" rerolls.");
    }

    private void sumUpDicePool(){
        GameManager.getFight().endAction();
    }

}
