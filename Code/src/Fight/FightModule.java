package Fight;

import Character.PlayerParty;

import Dice.Dice;
import Dice.DiceAction.DiceAction;
import Fight.Statuses.GameStatus;
import Fight.Statuses.StatusException;
import GUI.GUIState;
import GUI.MainPanel;
import Character.GameCharacter;
import Character.EnemyCharacter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public void targetSelected(int targetId){
        this.targetId=targetId;
        state.setState(GUIState.PLAYER_PERFORMING_ACTION);
    }

    public void rollDices(){
        master.roll();
        state.showDiceResult(master.getResults());
    }

    private void startAction(){
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //Check statuses
        GameCharacter character = playerTurn?party.getCharacters().get(characterTurn):enemies.get(characterTurn);

        for(GameStatus status:character.getStatuses()){
            if(status.haveTag(Tags.ON_TURN_START)) {
                try {
                    status.effect(character);
                }catch (StatusException e){
                    if(e.code==StatusException.DEATH){
                        setNextCharacterTurn();
                        break;
                    } else if (e.code==StatusException.STUN) {

                    }
                }
            }
        }
        character.statusEvaporate();
        //Refresh
        state.setState(playerTurn?GUIState.PLAYER_CHOOSING_ACTION:GUIState.ENEMY_PERFORMING_ACTION);
    }

    public void endAction(){
        if(playerTurn)
            master.sumUpResults();
        else {
            //TODO tymczasowe rozwiązanie
            targetType=ActionTarget.PLAYER_CHARACTER;
            targetId=new Random().nextInt(party.getCharacters().size());
            master.setResult(enemies.get(characterTurn).action());
        }
        performAction();
        setNextCharacterTurn();
        state.setState(playerTurn?GUIState.PLAYER_CHOOSING_ACTION:GUIState.ENEMY_PERFORMING_ACTION);
        //Sub state. Do it before next state.
        startAction();
    }

    private void performAction(){
        ArrayList<DiceAction> actions = master.getSumUpResults();
        ArrayList<GameCharacter> characters;
        //Set Attacker
        GameCharacter attacker = playerTurn?party.getCharacters().get(characterTurn):enemies.get(characterTurn);
        //Set Defender
        switch (targetType){
            case PLAYER_CHARACTER -> characters=new ArrayList<>(List.of(new GameCharacter[]{party.getCharacters().get(targetId)}));
            case ENEMY_CHARACTER -> characters=new ArrayList<>(List.of(new GameCharacter[]{enemies.get(targetId)}));
            case ALL_ENEMIES -> characters=new ArrayList<>(enemies);
            case PLAYER_PARTY -> characters=new ArrayList<>(party.getCharacters());
            default -> characters=null;
        }
        for (DiceAction action : actions){
            if(action.onSelf())
                action.doAction(attacker);
            else{
                for(GameCharacter character:characters)
                    action.doAction(character);
            }
        }
    }

    private void setNextCharacterTurn(){
        characterTurn++;
        if((playerTurn && characterTurn>=party.getCharacters().size()) || (!playerTurn && characterTurn>=enemies.size())) {
            if(!playerTurn)
                party.onTurnStart();
            characterTurn=0;
            playerTurn=!playerTurn;
        }
        if(!playerTurn && enemies.get(characterTurn).getCurrentHealth()==0)
            setNextCharacterTurn();
        if(!playerTurn)
            enemies.get(characterTurn).onTurnStart();
    }

    public int getEnemyCount(){
        return enemies.size();
    }

}
