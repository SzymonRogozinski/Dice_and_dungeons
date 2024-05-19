package Fight;

import Character.PlayerParty;

import Dice.Dice;
import Dice.DiceAction.DiceAction;
import Fight.GameActions.GameAction;
import Fight.Statuses.BonusDiceStatus;
import Fight.Statuses.GameStatus;
import Fight.Statuses.StatusException;
import GUI.GUIState;
import GUI.MainPanel;
import Character.GameCharacter;
import Character.EnemyCharacter;
import Character.PlayerCharacter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FightModule {
    private final PlayerParty party;
    private final DiceMaster master;
    private final GUIState state;
    private final ArrayList<EnemyCharacter> enemies;
    private boolean playerTurn;
    private int characterTurn;
    private int targetId;
    private ActionTarget targetType;
    private boolean noRoll;
    private GameAction action;

    public FightModule(MainPanel panel,GUIState state,PlayerParty party,ArrayList<EnemyCharacter> enemies) {
        this.playerTurn=true;
        this.characterTurn=0;
        this.state=state;
        this.master = new DiceMaster();
        this.enemies=enemies;
        this.party=party;
        panel.setFightModule(this);
        this.noRoll=false;
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

    public boolean isNoRoll() {
        return noRoll;
    }

    public void rerollDice(int diceId){
        master.reroll(diceId);
        state.showDiceResult(master.getResults());
        state.refreshRollPanel();
    }

    public boolean isDiceResultNull(){
        return master.getResults()==null;
    }

    public void choosedAction(GameAction action){
        this.targetType=action.getTarget();
        this.action=action;
        noRoll=action.haveTag(Tags.NO_ROLL);

        if(noRoll){
            master.setResult(action.getConstActions());
        }else{
            PlayerCharacter character = party.getCharacters().get(characterTurn);
            master.setDicePool(action.getDice(),action.getDiceNumber(character),character.getCharacterRerolls());
            ArrayList<GameStatus> bonuses=character.getStatusWithTag(Tags.BONUS_DICE);
            for(GameStatus bonus:bonuses){
                BonusDiceStatus diceStatus = (BonusDiceStatus) bonus;
                diceStatus.addBonusDice(master,action);
            }
        }

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

    public void showStatusInfo(String info){
        state.showStatusInfo(info);
    }

    public void hideStatusInfo(){
        state.hideStatusInfo();
    }

    private void startAction(){
        boolean skip=false;
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //Check statuses
        GameCharacter character = playerTurn?party.getCharacters().get(characterTurn):enemies.get(characterTurn);
        character.resetMod();

        ArrayList<GameStatus> statusOnStart = character.getStatusWithTag(Tags.ON_TURN_START);

        for(GameStatus status:statusOnStart){
            try {
                status.effect(character);
            }catch (StatusException e){
                //e.code==StatusException.DEATH || e.code==StatusException.STUN
                setNextCharacterTurn();
                skip=true;
            }
        }
        character.statusEvaporate();

        ArrayList<GameStatus> statusAfterStart = character.getStatusWithTag(Tags.AFTER_TURN_START);

        for(GameStatus status:statusAfterStart){
            try {
                status.effect(character);
            }catch (StatusException ignore){}
        }

        //Refresh
        if(skip)
            startAction();
        else
            state.setState(playerTurn?GUIState.PLAYER_CHOOSING_ACTION:GUIState.ENEMY_PERFORMING_ACTION);
    }


    public void endAction(){
        if(playerTurn && !noRoll)
            master.sumUpResults();
        else if(!playerTurn){
            //TODO tymczasowe rozwiązanie
            action=null;
            targetType=ActionTarget.PLAYER_CHARACTER;
            targetId=new Random().nextInt(party.getCharacters().size());
            master.setResult(enemies.get(characterTurn).action());
        }
        performAction();
        if(action==null || !action.haveTag(Tags.FREE_ACTION)){
            setNextCharacterTurn();
            state.setState(playerTurn?GUIState.PLAYER_CHOOSING_ACTION:GUIState.ENEMY_PERFORMING_ACTION);
            //Sub state. Do it before next state.
            startAction();
        }else{
            state.setState(playerTurn?GUIState.PLAYER_CHOOSING_ACTION:GUIState.ENEMY_PERFORMING_ACTION);
        }
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
        for (DiceAction diceAction : actions){
            if(diceAction.onSelf())
                diceAction.doAction(attacker);
            else{
                for(GameCharacter character:characters)
                    diceAction.doAction(character);
            }
        }
        //Counter
        for(GameCharacter character:characters) {
            //TODO not null!
            if(action==null || action.haveTag(Tags.ATTACK)){
                ArrayList<GameStatus> defendingStatus = character.getStatusWithTag(Tags.ON_DEFEND);
                for(GameStatus st:defendingStatus){
                    try{
                        st.effect(attacker);
                    }catch (StatusException ignore){}
                }
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
