package GUI.FightGUI;

import Dice.DiceSide;
import GUI.FightGUI.FightView;

import java.util.ArrayList;

public class FightGUIState {
    public static final int PLAYER_CHOOSING_ACTION=0;
    public static final int PLAYER_CHOOSING_TARGET=1;
    public static final int PLAYER_PERFORMING_ACTION=2;
    public static final int ENEMY_PERFORMING_ACTION=3;
    private int currentState;
    private final FightView panel;

    public FightGUIState(FightView panel){
        currentState=PLAYER_CHOOSING_ACTION;
        this.panel=panel;
    }

    public void initState(){
        panel.init();
    }

    public void setState(int newState){
        panel.setState(newState,currentState);
        currentState=newState;
    }

    public void refreshRollPanel(){
        panel.getRollPanel().rerollsChange();
    }

    public void showDiceResult(ArrayList<DiceSide> result){panel.getActionPanel().getDicePanel().showDiceResults(result);}

    public void showStatusInfo(String info){
        panel.getStatusPanel().showStatusInfo(info);
    }

    public void hideStatusInfo(){
        panel.getStatusPanel().hideStatusInfo();
    }

    public void refreshCombatLog(){
        panel.getStatusPanel().refreshCombatLog();
    }

    public void showNextMove(String info){
        panel.getStatusPanel().showNextMove(info);
    }

    public void hideNextMove(){
        panel.getStatusPanel().hideNextMove();
    }

    public void refresh(){
        panel.refresh();
        panel.setState(currentState,currentState);
    }
}
