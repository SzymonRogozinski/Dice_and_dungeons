package GUI.FightGUI;

import GUI.ViewPanel;

import javax.swing.*;
import java.util.ArrayList;

public class FightView extends ViewPanel {

    private RollPanel rollPanel;
    private FightPanel fightPanel;
    private ActionPanel actionPanel;
    private StatusPanel statusPanel;

    public FightView( ){
        super(new FightPanel(getSharedBorder()), new RollPanel(getSharedBorder()), new ActionPanel(getSharedBorder()), new StatusPanel(getSharedBorder()));

        //Get child component
        ArrayList<JPanel> panels = getChildPanels();
        fightPanel = (FightPanel) panels.get(0);
        rollPanel =(RollPanel) panels.get(1);
        actionPanel = (ActionPanel) panels.get(2);
        statusPanel= (StatusPanel) panels.get(3);

        //Refresh
        this.revalidate();
        this.repaint();
    }

    public void refresh(){
        statusPanel.refresh();
        fightPanel.refresh();
    }

    public ActionPanel getActionPanel(){ return actionPanel;}

    public RollPanel getRollPanel() {
        return rollPanel;
    }

    public StatusPanel getStatusPanel() {
        return statusPanel;
    }

    public void init(){
        actionPanel.getActions().loadAction();
        //Refresh
        this.revalidate();
        this.repaint();
    }

    public void setState(int newState,int currentState){
        switch (newState){
            case FightGUIState.PLAYER_CHOOSING_ACTION -> {
                if(currentState== FightGUIState.PLAYER_PERFORMING_ACTION || currentState== FightGUIState.ENEMY_PERFORMING_ACTION){
                    actionPanel.getActions().loadAction();
                }
                rollPanel.setVisible(false);
                actionPanel.setVisible(true);
                actionPanel.changePage("Actions");
            }
            case FightGUIState.PLAYER_CHOOSING_TARGET -> {
                rollPanel.setVisible(false);
                rollPanel.rerollsChange();
                actionPanel.setVisible(false);
                fightPanel.enemySelectable(true);
            }
            case FightGUIState.PLAYER_PERFORMING_ACTION -> {
                rollPanel.setVisible(true);
                fightPanel.enemySelectable(false);
                actionPanel.setVisible(true);
                actionPanel.changePage("Pause");
            }
            case FightGUIState.ENEMY_PERFORMING_ACTION -> {
                rollPanel.setVisible(false);
                actionPanel.setVisible(true);
                actionPanel.changePage("Enemy");
            }
        }
        //Refresh
        statusPanel.refresh();
        fightPanel.refresh();
        this.revalidate();
        this.repaint();
    }
}
