package Game.Threads;

public class ThreadManager {

    private final GameMutex mutex;
    private final GUIRefreshThread gft;
    private EnemyThread et;

    public ThreadManager() {
        mutex = new GameMutex();
        gft = new GUIRefreshThread();
        et = new EnemyThread(mutex);
    }

    public void start(){
        gft.start();
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
        et.start();
    }

    public void resumeEnemyThread(){
        mutex.resumeEnemyThread();
    }

    public void stopEnemyThread(){
        mutex.pauseEnemyThread();
    }
}
