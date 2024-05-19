package GUI;

import Dice.DiceSide;

import Character.PlayerCharacter;

import java.util.ArrayList;

public class GUIState {
    public static final int PLAYER_CHOOSING_ACTION=0;
    public static final int PLAYER_CHOOSING_TARGET=1;
    public static final int PLAYER_PERFORMING_ACTION=2;
    public static final int ENEMY_PERFORMING_ACTION=3;
    private int currentState;
    private final MainPanel panel;

    public GUIState(MainPanel panel){
        currentState=PLAYER_CHOOSING_ACTION;
        this.panel=panel;
        panel.setState(currentState,currentState);
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
}
