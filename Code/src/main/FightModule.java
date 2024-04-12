package main;

import Dice.DiceSide;
import GUI.GUIState;
import GUI.MainPanel;

import java.util.ArrayList;

public class FightModule {
    private final DiceMaster master;
    private final MainPanel panel;
    private final GUIState state;

    public FightModule(MainPanel panel,GUIState state) {
        this.state=state;
        this.panel=panel;
        this.master = new DiceMaster(this);
        this.panel.setFightModule(this);
    }

    public int getRerolls(){
        return master.getRerolls();
    }

    public void rollDices(){
        master.roll();
        state.showDiceResult(master.getResults());
    }

    public void rerollDice(int diceId){
        master.reroll(diceId);
        state.showDiceResult(master.getResults());
        state.refreshRollPanel();
    }

    public void showDiceResult(ArrayList<DiceSide> result){
        state.showDiceResult(result);
    }

    public void performAction(){
        state.setState(GUIState.PLAYER_PERFORMING_ACTION);
    }

    public void endAction(){
        master.sumUpResults();
        state.setState(GUIState.PLAYER_CHOOSING_ACTION);
    }

}
