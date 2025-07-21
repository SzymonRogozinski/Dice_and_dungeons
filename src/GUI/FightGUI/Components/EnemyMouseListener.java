package GUI.FightGUI.Components;

import Fight.ActionTarget;
import Game.GameActionQueue;
import Game.GameManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EnemyMouseListener implements MouseListener {

    private final int characterId;
    private final boolean isEnemy;
    private final FightPanelState state;

    EnemyMouseListener(int characterId, boolean isEnemy, FightPanelState state) {
        this.isEnemy = isEnemy;
        this.characterId = characterId;
        this.state=state;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!state.selectableFlag || !setBorderFlashing(false))
            return;
        GameActionQueue.action(()-> GameManager.getFight().targetSelected(state.selectedEnemy));
        state.selectedEnemy = -1;
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (isEnemy)
            GameActionQueue.action(()->GameManager.getFight().setCombatInfo(state.enemyPanelList.get(characterId).enemy.getNextAction()));
        if (!state.selectableFlag)
            return;
        setBorderFlashing(true);
        state.selectedEnemy = characterId;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (isEnemy)
            GameActionQueue.action(()->GameManager.getFight().setCombatInfo(""));
        if (!state.selectableFlag)
            return;
        setBorderFlashing(false);
        state.selectedEnemy = -1;
    }

    private boolean setBorderFlashing(boolean flashing) {
        if (isEnemy && GameManager.getFight().getTargetType() == ActionTarget.ENEMY_CHARACTER) {
            state.enemyPanelList.get(characterId).setBorder(flashing ? state.selectedLabelBorder : FightPanelState.labelBorder);
            return true;
        } else if (!isEnemy && GameManager.getFight().getTargetType() == ActionTarget.PLAYER_CHARACTER) {
            state.playerPanelList.get(characterId).setBorder(flashing ? state.selectedLabelBorder : FightPanelState.labelBorder);
            return true;
        } else if (isEnemy && GameManager.getFight().getTargetType() == ActionTarget.ALL_ENEMIES) {
            for (EnemyPanel label : state.enemyPanelList)
                label.setBorder(flashing ? state.selectedLabelBorder : FightPanelState.labelBorder);
            return true;
        } else if (!isEnemy && GameManager.getFight().getTargetType() == ActionTarget.PLAYER_PARTY) {
            for (PlayerPanel label : state.playerPanelList)
                label.setBorder(flashing ? state.selectedLabelBorder : FightPanelState.labelBorder);
            return true;
        }
        return false;
    }
}
