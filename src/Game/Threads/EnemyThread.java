package Game.Threads;

import Game.GameManager;

public class EnemyThread extends Thread {

    private final static int oneRoundTime = 500;
    private final GameMutex mutex;

    public EnemyThread(GameMutex mutex) {
        this.mutex = mutex;
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
            mutex.gameChanging();
            GameManager.getWalkingManager().getWalking().enemiesMove();
            mutex.gameChanged();
            try {
                Thread.sleep(oneRoundTime / GameManager.getWalkingManager().getWalking().getEnemies().countEnemy());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
