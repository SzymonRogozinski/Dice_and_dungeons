package GUI;

import GUI.FightGUI.*;
import Fight.FightModule;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MainPanel extends JPanel {

    private RollPanel rollPanel;
    private FightPanel fightPanel;
    private ActionPanel actionPanel;
    private StatusPanel statusPanel;

    public MainPanel( ){
        //Setting panel
        this.setLayout(null);
        this.setPreferredSize(new Dimension(GUISettings.heightAndWidth,GUISettings.heightAndWidth));
        this.setBackground(Color.BLACK);

        //Panel components
        //Big four
        JPanel bigPanel=new JPanel();
        JPanel smallPanel = new JPanel();
        JPanel leftInfoPanel = new JPanel();
        JPanel downInfoPanel = new JPanel();
        Border border = BorderFactory.createLineBorder(Color.WHITE, 2);
        //Real components
        rollPanel =new RollPanel(border);
        fightPanel = new FightPanel(border);
        actionPanel =  new ActionPanel(border);
        statusPanel=new StatusPanel(border);

        //Settings components
        bigPanel.setBounds(0,0, GUISettings.PANEL_SIZE,GUISettings.PANEL_SIZE);
        bigPanel.setLayout(null);
        bigPanel.setBackground(Color.BLACK);
        bigPanel.setBorder(border);
        bigPanel.add(fightPanel);

        smallPanel.setBounds(GUISettings.PANEL_SIZE,GUISettings.PANEL_SIZE,GUISettings.SMALL_PANEL_SIZE,GUISettings.SMALL_PANEL_SIZE);
        smallPanel.setLayout(null);
        smallPanel.setBackground(Color.BLACK);
        smallPanel.setBorder(border);
        smallPanel.add(rollPanel);

        leftInfoPanel.setBounds(GUISettings.PANEL_SIZE,0,GUISettings.SMALL_PANEL_SIZE,GUISettings.PANEL_SIZE);
        leftInfoPanel.setLayout(null);
        leftInfoPanel.setBackground(Color.BLACK);
        leftInfoPanel.setBorder(border);
        leftInfoPanel.add(statusPanel);

        downInfoPanel.setBounds(0,GUISettings.PANEL_SIZE,GUISettings.PANEL_SIZE,GUISettings.SMALL_PANEL_SIZE);
        downInfoPanel.setLayout(null);
        downInfoPanel.setBackground(Color.BLACK);
        downInfoPanel.setBorder(border);
        downInfoPanel.add(actionPanel);

        //Adding component to panel
        this.add(bigPanel);
        this.add(smallPanel);
        this.add(leftInfoPanel);
        this.add(downInfoPanel);

        //Refresh
        this.revalidate();
        this.repaint();
    }

    public void setFightModule(FightModule fightModule){
        rollPanel.setFight(fightModule);
        fightPanel.setFight(fightModule);
        actionPanel.setFight(fightModule);
        statusPanel.setFight(fightModule);
    }

    public ActionPanel getActionPanel(){ return actionPanel;}

    public RollPanel getRollPanel() {
        return rollPanel;
    }

    public void init(){
        actionPanel.getActions().loadAction();
        //Refresh
        this.revalidate();
        this.repaint();
    }

    public void setState(int newState,int currentState){
        switch (newState){
            case GUIState.PLAYER_CHOOSING_ACTION -> {
                if(currentState==GUIState.PLAYER_PERFORMING_ACTION || currentState==GUIState.ENEMY_PERFORMING_ACTION){
                    actionPanel.getActions().loadAction();
                }
                rollPanel.setVisible(false);
                actionPanel.setVisible(true);
                actionPanel.changePage("Actions");
            }
            case GUIState.PLAYER_CHOOSING_TARGET -> {
                rollPanel.setVisible(false);
                rollPanel.rerollsChange();
                actionPanel.setVisible(false);
                fightPanel.enemySelectable(true);
            }
            case GUIState.PLAYER_PERFORMING_ACTION -> {
                rollPanel.setVisible(true);
                fightPanel.enemySelectable(false);
                actionPanel.setVisible(true);
                actionPanel.changePage("Pause");
            }
            case GUIState.ENEMY_PERFORMING_ACTION -> {
                rollPanel.setVisible(true);
                actionPanel.setVisible(true);
                actionPanel.changePage("Enemy");
            }
        }
        //Refresh
        statusPanel.refresh();
        fightPanel.refresh();
        //this.revalidate();
        //this.repaint();

    }
}
