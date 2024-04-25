package Fight;

import Character.PlayerParty;

import Dice.Dice;
import GUI.GUIState;
import GUI.MainPanel;
import Character.GameCharacter;
import Character.EnemyCharacter;

import java.util.ArrayList;

public class FightModule {
    private final PlayerParty party;
    private final DiceMaster master;
    private final MainPanel panel;
    private final GUIState state;
    private final ArrayList<EnemyCharacter> enemies;
    private boolean playerTurn;
    private int characterTurn;
    private int targetId;
    private ActionTarget targetType;

    public FightModule(MainPanel panel,GUIState state,PlayerParty party,ArrayList<EnemyCharacter> enemies) {
        this.playerTurn=true;
        this.characterTurn=0;
        this.state=state;
        this.panel=panel;
        this.master = new DiceMaster();
        this.enemies=enemies;
        this.party=party;
        this.panel.setFightModule(this);
    }

    public ActionTarget getTargetType() {
        return targetType;
    }

    public GameCharacter getCharacter(){
        return playerTurn?party.getCharacters().get(characterTurn) : enemies.get(characterTurn);
    }

    public PlayerParty getParty(){
        return party;
    }

    public ArrayList<EnemyCharacter> getEnemies() {
        return enemies;
    }

    public void initFight(){
        state.initState();
    }

    public int getRerolls(){
        return master.getRerolls();
    }

    public void rerollDice(int diceId){
        master.reroll(diceId);
        state.showDiceResult(master.getResults());
        state.refreshRollPanel();
    }

    public void choosedAction(Dice dice, int numDice,int rerolls, ActionTarget targetType){
        this.targetType=targetType;
        master.setDicePool(dice,numDice,rerolls);
        state.setState(GUIState.PLAYER_CHOOSING_TARGET);
    }

    public void performAction(int targetId){
        this.targetId=targetId;
        state.setState(GUIState.PLAYER_PERFORMING_ACTION);
    }

    public void rollDices(){
        master.roll();
        state.showDiceResult(master.getResults());
    }

    public void endAction(){
        if(master.getResults()==null)
            return;
        if(playerTurn)
            master.sumUpResults();
        characterTurn++;
        if((playerTurn && characterTurn>=party.getCharacters().size()) || (!playerTurn && characterTurn>=enemies.size())) {
            characterTurn=0;
            playerTurn=!playerTurn;
        }
        state.setState(playerTurn?GUIState.PLAYER_CHOOSING_ACTION:GUIState.ENEMY_PERFORMING_ACTION);
    }

    public int getEnemyCount(){
        return enemies.size();
    }

    public void enemyAction(){
        enemies.get(characterTurn).action();
        endAction();
    }

}
