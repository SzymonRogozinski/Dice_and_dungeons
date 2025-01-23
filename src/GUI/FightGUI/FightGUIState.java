package GUI.FightGUI;

import Dice.DiceSide;

import java.util.ArrayList;

public class FightGUIState {
    public static final int PLAYER_CHOOSING_ACTION = 0;
    public static final int PLAYER_CHOOSING_TARGET = 1;
    public static final int PLAYER_PERFORMING_ACTION = 2;
    public static final int ENEMY_PERFORMING_ACTION = 3;
    private final FightView panel;
    private int currentState;

    public FightGUIState(FightView panel) {
        currentState = PLAYER_CHOOSING_ACTION;
        this.panel = panel;
    }

    public void initState() {
        setState(PLAYER_CHOOSING_ACTION);
        panel.init();
    }

    public void setState(int newState) {
        panel.setState(newState, currentState);
        currentState = newState;
    }

    public void refreshRollPanel() {
        panel.getRollPanel().rerollsChange();
    }

    public void showDiceResult(ArrayList<DiceSide> result) {
        panel.getActionPanel().getDicePanel().showDiceResults(result);
    }

    public void refresh() {
        panel.refresh();
        panel.setState(currentState, currentState);
    }
}
