package Game.Threads;

public class GameMutex {

    public final Object screenLock;
    public final Object enemyWalkingLock;
    public boolean gameChanging, endEnemyThread, stopEnemyThread;
    private int countChange=0;

    public GameMutex(){
        screenLock =new Object();
        enemyWalkingLock =new Object();
    }

    public synchronized void gameChanging(){
        countChange++;
        this.gameChanging=true;
    }

    public void gameChanged(){
        countChange--;
        if(countChange==0){
            this.gameChanging=false;
            synchronized (screenLock) {
                this.screenLock.notify();
            }
        }
    }

    public void frameStop(){
        pauseEnemyThread();
        try {
            if(gameChanging) {
                synchronized (screenLock) {

                    screenLock.wait();
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("Thread interrupted!?");
        }
    }

    public void frameUnstop(){
        resumeEnemyThread();
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
