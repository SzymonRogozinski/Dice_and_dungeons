package Game.Threads;

import Game.GameActionQueue;
import Game.GameManager;

public class EnemyThread extends Thread {

    private final static int oneRoundTime = 500;
    private final GameMutex mutex;
    private boolean hasEnded;

    public EnemyThread(GameMutex mutex) {
        this.mutex = mutex;
        hasEnded=false;
    }

    @Override
    public void run() {
        while (GameManager.getWalkingManager().getWalking().getEnemies().countEnemy() > 0 && !mutex.isEndEnemyThread()) {
            synchronized (mutex.enemyWalkingLock) {
                if (mutex.isStopEnemyThread()) {
                    try {
                        mutex.enemyWalkingLock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException("Thread interrupted!?");
                    }
                }
            }
            GameActionQueue.action(()->GameManager.getWalkingManager().getWalking().enemiesMove());
            try {
                Thread.sleep(oneRoundTime / Math.max(GameManager.getWalkingManager().getWalking().getEnemies().countEnemy(),1));    //Avoid dividing by zero
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        hasEnded=true;
    }

    public boolean isThreadEnded() {
        return hasEnded;
    }
}
