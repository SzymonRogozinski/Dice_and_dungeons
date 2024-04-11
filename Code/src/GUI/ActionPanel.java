package GUI;

import main.FightModule;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ActionPanel extends JPanel {

    private FightModule fight;

    public ActionPanel(Border border){
        //Sizes and placement of button
        int buttonWidth=GUISettings.SMALL_PANEL_SIZE*2/3;
        int buttonHeight=GUISettings.SMALL_PANEL_SIZE/3;

        //Set display
        this.setSize(GUISettings.SMALL_PANEL_SIZE,GUISettings.PANEL_SIZE);
        FlowLayout layout=new FlowLayout();
        layout.setVgap(GUISettings.PANEL_SIZE/4-buttonHeight/2);
        this.setLayout(layout);
        this.setBorder(border);
        this.setBackground(Color.BLACK);

        //Setting buttons
        JButton action1=new JButton("Action 1");
        action1.setSize(buttonWidth,buttonHeight);
        action1.addActionListener(e->fight.hideRollPanel());

        JButton action2=new JButton("Action 2");
        action2.setSize(buttonWidth,buttonHeight);

        JButton action3=new JButton("Action 3");
        action3.setSize(buttonWidth,buttonHeight);

        this.add(action1);
        this.add(action2);
        this.add(action3);
    }

    public void setFight(FightModule fight){
        this.fight=fight;
    }
}
