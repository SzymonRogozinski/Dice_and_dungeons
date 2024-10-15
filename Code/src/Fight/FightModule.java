package Fight;

import Character.Enemy.EnemyCharacter;
import Character.GameCharacter;
import Character.PlayerCharacter;
import Dice.DiceAction.DiceAction;
import Fight.GameActions.EnemyAction;
import Fight.GameActions.GameAction;
import Fight.GameActions.SpellAction;
import Fight.Statuses.BonusDiceStatus;
import Fight.Statuses.GameStatus;
import Fight.Statuses.StatusException;
import GUI.FightGUI.FightGUIState;
import Game.GameManager;
import Game.GameStates;
import Game.PlayerInfo;
import Game.Tags;

import java.util.ArrayList;
import java.util.List;

public class FightModule {
    private final DiceMaster master;
    private final FightGUIState state;
    private ArrayList<EnemyCharacter> enemies;
    private boolean playerTurn;
    private int characterTurn;
    private int targetId;
    private ActionTarget targetType;
    private boolean noRoll;
    private GameAction action;
    private StringBuilder combatLogInfo;
    private String statusLog, combatLog, combatInfo;

    public FightModule(FightGUIState state, ArrayList<EnemyCharacter> enemies) {
        this.combatLogInfo = new StringBuilder();
        this.combatLog = "";
        this.noRoll = false;
        this.playerTurn = true;
        this.characterTurn = 0;
        this.state = state;
        this.master = new DiceMaster();
        this.enemies = enemies;
    }

    public FightModule(FightGUIState state) {
        this.combatLogInfo = new StringBuilder();
        this.combatLog = "";
        this.noRoll = false;
        this.playerTurn = true;
        this.characterTurn = 0;
        this.state = state;
        this.master = new DiceMaster();
        this.enemies = new ArrayList<>();
    }

    public ActionTarget getTargetType() {
        return targetType;
    }

    public GameCharacter getCharacter() {
        return playerTurn ? PlayerInfo.getParty().getCharacters().get(characterTurn) : enemies.get(characterTurn);
    }

    public ArrayList<EnemyCharacter> getEnemies() {
        return enemies;
    }

    public String getCombatLog() {
        return combatLog;
    }

    public void refreshCombatLog() {
        combatLog = combatLogInfo.toString();
        combatLogInfo = new StringBuilder();
    }

    public void startFight(ArrayList<EnemyCharacter> enemies) {
        this.enemies = enemies;
        state.initState();
        state.refresh();
    }

    public int getRerolls() {
        return master.getRerolls();
    }

    public boolean isNoRoll() {
        return noRoll;
    }

    public void rerollDice(int diceId) {
        master.reroll(diceId);
        state.showDiceResult(master.getResults());
        state.refreshRollPanel();
    }

    public boolean isDiceResultNull() {
        return master.getResults() == null;
    }

    public void chosenAction(GameAction action) {
        this.targetType = action.getTarget();
        this.action = action;
        noRoll = action.haveTag(Tags.NO_ROLL);

        if (noRoll)
            master.setResult(action.getActionFactories());
        else {
            PlayerCharacter character = PlayerInfo.getParty().getCharacters().get(characterTurn);
            master.setDicePool(action.getDice(), action.getDiceNumber(character), character.getCharacterRerolls());
            ArrayList<GameStatus> bonuses = character.getStatusWithTag(Tags.BONUS_DICE);
            for (GameStatus bonus : bonuses) {
                BonusDiceStatus diceStatus = (BonusDiceStatus) bonus;
                diceStatus.addBonusDice(master, action);
            }
        }
        refreshCombatLog();
        state.setState(FightGUIState.PLAYER_CHOOSING_TARGET);
    }

    public void targetSelected(int targetId) {
        this.targetId = targetId;
        //If action is spell, then spend mana. Mana check was in action listener.
        if (action instanceof SpellAction spellAction)
            PlayerInfo.getParty().spendMana(spellAction.getManaCost());
        state.setState(FightGUIState.PLAYER_PERFORMING_ACTION);
    }

    public void rollDices() {
        master.roll();
        state.showDiceResult(master.getResults());
    }

    public String getStatusLog() {
        return statusLog;
    }

    public void setStatusLog(String statusLog) {
        this.statusLog = statusLog;
        state.refresh();
    }

    public String getCombatInfo() {
        return combatInfo;
    }

    public void setCombatInfo(String info) {
        combatInfo = info;
        state.refresh();
    }

    public void clear() {
        for (PlayerCharacter p : PlayerInfo.getParty().getCharacters())
            p.resetStatus();
        for (EnemyCharacter e : enemies)
            e.resetStatus();
    }

    public void goBackToChoose() {
        state.setState(FightGUIState.PLAYER_CHOOSING_ACTION);
    }

    public void endAction() {
        if (playerTurn && !noRoll)
            master.sumUpResults();
        else if (!playerTurn) {
            EnemyCharacter enemy = enemies.get(characterTurn);
            EnemyAction enemyAction = enemy.action(enemies, PlayerInfo.getParty().getCharacters());
            targetType = enemyAction.getTarget();
            targetId = enemy.getTargetId();
            master.setResult(enemyAction.getConstActions(enemy));
            action = enemyAction;
        }
        performAction();
        refreshCombatLog();
        if (action == null || !action.haveTag(Tags.FREE_ACTION)) {
            setNextCharacterTurn();
            state.setState(playerTurn ? FightGUIState.PLAYER_CHOOSING_ACTION : FightGUIState.ENEMY_PERFORMING_ACTION);
            //Substate. Do it before next state.
            startAction();
        } else
            state.setState(playerTurn ? FightGUIState.PLAYER_CHOOSING_ACTION : FightGUIState.ENEMY_PERFORMING_ACTION);
    }

    public int getEnemyCount() {
        return enemies.size();
    }

    private void performAction() {
        ArrayList<DiceAction> actions = master.getSumUpResults();
        ArrayList<GameCharacter> characters;
        //Set Attacker
        GameCharacter attacker = playerTurn ? PlayerInfo.getParty().getCharacters().get(characterTurn) : enemies.get(characterTurn);
        //Set Defender
        switch (targetType) {
            case PLAYER_CHARACTER ->
                    characters = new ArrayList<>(List.of(new GameCharacter[]{PlayerInfo.getParty().getCharacters().get(targetId)}));
            case ENEMY_CHARACTER -> characters = new ArrayList<>(List.of(new GameCharacter[]{enemies.get(targetId)}));
            case ALL_ENEMIES -> characters = new ArrayList<>(enemies);
            case PLAYER_PARTY -> characters = new ArrayList<>(PlayerInfo.getParty().getCharacters());
            default -> characters = null;
        }
        for (DiceAction diceAction : actions) {
            if (diceAction.onSelf()) {
                diceAction.doAction(attacker);
                combatLogInfo.append(diceAction.actionDescription(attacker.getName(), null)).append(" ");
            } else {
                for (GameCharacter character : characters) {
                    diceAction.doAction(character);
                    combatLogInfo.append(diceAction.actionDescription(attacker.getName(), character.getName())).append(" ");
                }
            }
        }
        //Counter
        for (GameCharacter character : characters) {
            if (action.haveTag(Tags.ATTACK)) {
                ArrayList<GameStatus> defendingStatus = character.getStatusWithTag(Tags.ON_DEFEND);
                for (GameStatus st : defendingStatus) {
                    try {
                        st.effect(attacker);
                    } catch (StatusException ignore) {
                    }
                    combatLogInfo.append(st.effectCommunicate(character.getName())).append(" ");
                }
            }
        }
    }

    private void setNextCharacterTurn() {
        characterTurn++;
        if ((playerTurn && characterTurn >= PlayerInfo.getParty().getCharacters().size()) || (!playerTurn && characterTurn >= enemies.size())) {
            if (!playerTurn)
                PlayerInfo.getParty().onTurnStart();
            characterTurn = 0;
            playerTurn = !playerTurn;
        }
        if (!playerTurn && enemies.get(characterTurn).getCurrentHealth() == 0)
            setNextCharacterTurn();
        if (!playerTurn)
            enemies.get(characterTurn).clearShield();
    }

    private void endFight(boolean playerWin) {
        clear();
        this.combatLogInfo = new StringBuilder();
        setCombatInfo("");
        refreshCombatLog();
        this.noRoll = false;
        this.playerTurn = true;
        this.characterTurn = 0;
        this.targetId = 0;

        if (playerWin && GameManager.isBossFight())
            GameManager.gameWin();
        else if (playerWin) {
            GameManager.getLootModule().getLoot(GameManager.getCurrentLevel().lootSettings(), true);
            GameManager.changeState(GameStates.WALKING);
        } else
            GameManager.gameOver();
    }

    private boolean checkIfFightEnds() {
        boolean enemiesDead = true;
        for (EnemyCharacter enemy : enemies) {
            if (enemy.getCurrentHealth() > 0) {
                enemiesDead = false;
                break;
            }
        }
        if (enemiesDead) {
            endFight(true);
            return true;
        } else if (PlayerInfo.getParty().getCurrentHealth() == 0) {
            endFight(false);
            return true;
        }
        return false;
    }

    private void startAction() {
        if (checkIfFightEnds())
            return;
        boolean skip = false;
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //Check statuses
        GameCharacter character = playerTurn ? PlayerInfo.getParty().getCharacters().get(characterTurn) : enemies.get(characterTurn);
        character.resetMod();

        ArrayList<GameStatus> statusOnStart = character.getStatusWithTag(Tags.ON_TURN_START);

        for (GameStatus status : statusOnStart) {
            try {
                status.effect(character);
            } catch (StatusException e) {
                setNextCharacterTurn();
                skip = true;
            }
            combatLogInfo.append(status.effectCommunicate(character.getName())).append(" ");
        }
        if (checkIfFightEnds())
            return;
        character.statusEvaporate();

        ArrayList<GameStatus> statusAfterStart = character.getStatusWithTag(Tags.AFTER_TURN_START);

        for (GameStatus status : statusAfterStart) {
            try {
                status.effect(character);
            } catch (StatusException ignore) {
            }
        }
        //Refresh
        if (skip)
            startAction();
        else
            state.setState(playerTurn ? FightGUIState.PLAYER_CHOOSING_ACTION : FightGUIState.ENEMY_PERFORMING_ACTION);
    }

}
