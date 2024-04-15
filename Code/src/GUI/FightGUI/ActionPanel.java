package GUI.FightGUI;

import GUI.GUISettings;
import main.FightModule;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ActionPanel extends JPanel {

    private FightModule fight;
    private CardLayout layout;
    private ActionListPanel actions;
    private DicePanel dice;
    private JPanel pauseScreen;

    public ActionPanel(Border border){
        //Set display
        this.setSize(GUISettings.PANEL_SIZE,GUISettings.SMALL_PANEL_SIZE);
        this.layout=new CardLayout();
        this.setLayout(layout);
        this.setBackground(Color.BLACK);

        //Card components
        actions=new ActionListPanel(border);
        dice =new DicePanel(border);
        //Pause Screen
        pauseScreen=new JPanel();
        pauseScreen.setSize(GUISettings.PANEL_SIZE,GUISettings.SMALL_PANEL_SIZE);
        pauseScreen.setLayout(new GridLayout(3,1));
        pauseScreen.setBackground(Color.BLACK);
        JButton pauseButton = new JButton("Roll");
        pauseButton.addActionListener(e->roll());
        pauseScreen.add(new JLabel());
        pauseScreen.add(pauseButton);

        this.add("Actions",actions);
        this.add("Pause",pauseScreen);
        this.add("Dice", dice);
    }

    public void setFight(FightModule fight){
        this.fight=fight;
        dice.setFight(fight);
        actions.setFight(fight);
    }

    public DicePanel getDicePanel(){return dice;}

    public void changePage(String pageName){layout.show(this,pageName);}

    private void roll(){
        changePage("Dice");
        fight.rollDices();
    }
}
