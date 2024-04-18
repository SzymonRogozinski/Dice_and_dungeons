package GUI;

import Dice.DiceSide;

import Character.PlayerCharacter;

import java.util.ArrayList;

public class GUIState {
    public static final int PLAYER_CHOOSING_ACTION=0;
    public static final int PLAYER_CHOOSING_TARGET=1;
    public static final int PLAYER_PERFORMING_ACTION=2;
    private int currentState;
    private final MainPanel panel;

    public GUIState(MainPanel panel){
        currentState=PLAYER_CHOOSING_ACTION;
        this.panel=panel;
        panel.setState(currentState);
    }

    public void initState(PlayerCharacter firstCharacter){
        panel.init(firstCharacter);
    }

    public void setState(int newState){
        currentState=newState;
        panel.setState(currentState);
    }

    public void refreshRollPanel(){
        panel.getRollPanel().rerollsChange();
    }

    public void showDiceResult(ArrayList<DiceSide> result){panel.getActionPanel().getDicePanel().showDiceResults(result);}
}
