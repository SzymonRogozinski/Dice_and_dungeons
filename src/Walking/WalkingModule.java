package Walking;

import GUI.WalkingGUI.WalkingGUIState;
import Game.GameConst;
import Game.GameManager;

public class WalkingModule {
    private WalkingLevel walking;

    public WalkingModule() {
        walking = new WalkingLevel(GameManager.getCurrentLevel());
    }

    public WalkingLevel getWalking() {
        return walking;
    }

    public void setNextMap() throws Exception {
        if (GameManager.getLevelPointer() + 1 >= GameConst.LEVELS.size())
            throw new Exception("Cannot load new map!");
        try {
            GameManager.getThreadManager().killEnemyThread();

            GameManager.setNextLevel();
            walking = new WalkingLevel(GameManager.getCurrentLevel());
            GameManager.getThreadManager().startEnemyThread();
        } catch (Exception ignore) {
        }
    }

    public void setMap(WalkingLevel walking) {
        this.walking = walking;
    }

    public void startWalking() {
        if (GameManager.getThreadManager().getEnemyThread().isAlive() || GameManager.getThreadManager().getEnemyThread().isThreadEnded())
            GameManager.getThreadManager().resumeEnemyThread();
        else
            GameManager.getThreadManager().startEnemyThread();
    }

    public void stopWalking() {
        GameManager.getThreadManager().stopEnemyThread();
    }
}
