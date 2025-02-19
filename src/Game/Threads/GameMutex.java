package Game.Threads;

public class GameMutex {

    public final Object enemyWalkingLock;
    public boolean endEnemyThread, stopEnemyThread;

    public GameMutex(){
        enemyWalkingLock =new Object();
    }

    public synchronized boolean isEndEnemyThread() {
        return endEnemyThread;
    }

    public synchronized boolean isStopEnemyThread() {
        return stopEnemyThread;
    }

    public synchronized void endEnemyThread() {
        endEnemyThread = true;
    }

    public synchronized void pauseEnemyThread() {
        stopEnemyThread = true;
    }

    public synchronized void resumeEnemyThread() {
        stopEnemyThread =false;
        synchronized (enemyWalkingLock) {
            enemyWalkingLock.notify();
        }
    }

}
