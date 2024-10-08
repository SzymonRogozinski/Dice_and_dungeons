package GUI.FightGUI;

import GUI.GUISettings;
import Game.GameManager;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class ActionPanel extends JPanel {
    private CardLayout layout;
    private ActionListPanel actions;
    private DicePanel dice;
    private JPanel pauseScreen,enemyScreen, goBackScreen;

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
        pauseScreen=new StopPanel(e->roll(),"roll");
        enemyScreen=new StopPanel(e->enemy(),"enemy attack");
        goBackScreen=new StopPanel(e->goBack(),"go back");

        this.add("Actions",actions);
        this.add("Pause",pauseScreen);
        this.add("Enemy",enemyScreen);
        this.add("Dice", dice);
        this.add("GoBack",goBackScreen);
    }

    public DicePanel getDicePanel(){return dice;}

    public void changePage(String pageName){layout.show(this,pageName);}

    public ActionListPanel getActions() {
        return actions;
    }

    private void roll(){
        if(GameManager.getFight().isNoRoll()){
            GameManager.getFight().endAction();
            return;
        }
        changePage("Dice");
        GameManager.getFight().rollDices();
    }

    private void goBack(){
        GameManager.getFight().goBackToChoose();
    }

    private void enemy(){
        GameManager.getFight().endAction();
    }

    private class StopPanel extends JPanel{

        StopPanel(ActionListener l,String buttonName){
            this.setSize(GUISettings.PANEL_SIZE,GUISettings.SMALL_PANEL_SIZE);
            this.setLayout(new GridLayout(3,1));
            this.setBackground(Color.BLACK);
            JButton pauseButton = new JButton(buttonName);
            pauseButton.addActionListener(l);
            this.add(new JLabel());
            this.add(pauseButton);
        }
    }
}
