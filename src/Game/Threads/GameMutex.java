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

    public synchronized void gameChanged(){
        countChange--;
        if(countChange==0){
            this.gameChanging=false;
            this.screenLock.notify();
        }
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
        this.stopEnemyThread =false;
        this.enemyWalkingLock.notify();
    }

}
