package Fight;

import Character.Enemy.EnemyCharacter;
import Character.GameCharacter;
import Character.PlayerCharacter;
import Character.PlayerParty;
import Dice.DiceAction.DiceAction;
import Fight.GameActions.EnemyAction;
import Fight.GameActions.GameAction;
import Fight.Statuses.BonusDiceStatus;
import Fight.Statuses.GameStatus;
import Fight.Statuses.StatusException;
import GUI.GUIState;
import GUI.FightGUI.FightView;
import Game.Tags;

import java.util.ArrayList;
import java.util.List;

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
    private String combatLogInfo;

    public FightModule(FightView panel, GUIState state, PlayerParty party, ArrayList<EnemyCharacter> enemies) {
        this.combatLogInfo="";
        this.noRoll=false;
        this.playerTurn=true;
        this.characterTurn=0;
        this.state=state;
        this.master = new DiceMaster();
        this.enemies=enemies;
        this.party=party;
        panel.setFightModule(this);
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

    public String getCombatLogInfo() {
        String respond = combatLogInfo;
        combatLogInfo="";
        return respond;
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
            master.setResult(action.getActionFactories());
        }else{
            PlayerCharacter character = party.getCharacters().get(characterTurn);
            master.setDicePool(action.getDice(),action.getDiceNumber(character),character.getCharacterRerolls());
            ArrayList<GameStatus> bonuses=character.getStatusWithTag(Tags.BONUS_DICE);
            for(GameStatus bonus:bonuses){
                BonusDiceStatus diceStatus = (BonusDiceStatus) bonus;
                diceStatus.addBonusDice(master,action);
            }
        }
        state.refreshCombatLog();
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

    public void showNextMove(String info){
        state.showNextMove(info);
    }

    public void hideNextMove(){
        state.hideNextMove();
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
            combatLogInfo+=status.effectCommunicate(character.getName())+" ";
        }
        character.statusEvaporate();

        ArrayList<GameStatus> statusAfterStart = character.getStatusWithTag(Tags.AFTER_TURN_START);

        for(GameStatus status:statusAfterStart){
            try {
                status.effect(character);
            }catch (StatusException ignore){}
        }
        //Refresh
        if(skip) {
            startAction();
        }else {
            state.setState(playerTurn ? GUIState.PLAYER_CHOOSING_ACTION : GUIState.ENEMY_PERFORMING_ACTION);
        }
    }

    public void endAction(){
        if(playerTurn && !noRoll)
            master.sumUpResults();
        else if(!playerTurn){
            EnemyCharacter enemy=enemies.get(characterTurn);
            EnemyAction enemyAction=enemy.action(enemies,party.getCharacters());
            targetType=enemyAction.getTarget();
            targetId=enemy.getTargetId();
            master.setResult(enemyAction.getConstActions(enemy));
            action=enemyAction;
        }
        performAction();
        state.refreshCombatLog();
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
            if(diceAction.onSelf()){
                diceAction.doAction(attacker);
                combatLogInfo+=diceAction.actionDescription(attacker.getName(),null)+" ";
            }
            else{
                for(GameCharacter character:characters) {
                    diceAction.doAction(character);
                    combatLogInfo+=diceAction.actionDescription(attacker.getName(), character.getName())+" ";
                }
            }
        }
        //Counter
        for(GameCharacter character:characters) {
            if(action.haveTag(Tags.ATTACK)){
                ArrayList<GameStatus> defendingStatus = character.getStatusWithTag(Tags.ON_DEFEND);
                for(GameStatus st:defendingStatus){
                    try{
                        st.effect(attacker);
                    }catch (StatusException ignore){}
                    combatLogInfo+=st.effectCommunicate(character.getName())+" ";
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
