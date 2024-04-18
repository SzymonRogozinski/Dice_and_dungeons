package main;

import Character.PlayerParty;

import Dice.Dice;
import GUI.GUIState;
import GUI.MainPanel;

public class FightModule {
    private final PlayerParty party;
    private final DiceMaster master;
    private final MainPanel panel;
    private final GUIState state;
    private int playerTurn;

    public FightModule(MainPanel panel,GUIState state,PlayerParty party) {
        this.party=party;
        this.state=state;
        this.panel=panel;
        this.master = new DiceMaster(this);
        this.panel.setFightModule(this);
        this.playerTurn=0;
    }

    public void initFight(){
        state.initState(party.getCharacters().get(0));
    }

    public PlayerParty getParty() {
        return party;
    }

    public int getRerolls(){
        return master.getRerolls();
    }

    public void rerollDice(int diceId){
        master.reroll(diceId);
        state.showDiceResult(master.getResults());
        state.refreshRollPanel();
    }

    public void choosedAction(Dice dice, int numDice,int rerolls){
        master.setDicePool(dice,numDice,rerolls);
        state.setState(GUIState.PLAYER_CHOOSING_TARGET);
    }

    public void performAction(){state.setState(GUIState.PLAYER_PERFORMING_ACTION);}

    public void rollDices(){
        master.roll();
        state.showDiceResult(master.getResults());
    }

    public void endAction(){
        master.sumUpResults();
        state.setState(GUIState.PLAYER_CHOOSING_ACTION);
    }

}
