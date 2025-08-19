package Game.Threads;

import Game.GameManager;

public class ThreadManager {

    private final GameMutex mutex;
    private final FramerateThread ft;
    private EnemyThread et;

    public ThreadManager() {
        mutex = new GameMutex();
        ft = new FramerateThread();
        et = new EnemyThread(mutex);
    }

    public void start(){
        ft.start();
    }

    public void killEnemyThread(){
        try {
            mutex.endEnemyThread();
            et.join();
        } catch (InterruptedException ignored) {
        }
    }

    public boolean isEnemyThreadStopped(){
        return mutex.isStopEnemyThread();
    }

    public void makeNewEnemyThread(){
        et = new EnemyThread(mutex);
    }

    public EnemyThread getEnemyThread(){
        return et;
    }

    public void startEnemyThread(){
        if(!et.isThreadEnded())
            et.start();
    }

    public void resumeEnemyThread(){
        mutex.resumeEnemyThread();
    }

    public void stopEnemyThread(){
        mutex.pauseEnemyThread();
    }
}
