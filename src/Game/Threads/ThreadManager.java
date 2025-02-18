package Game.Threads;

public class ThreadManager {

    private final GameMutex mutex;
    private final GUIRefreshThread gft;
    private EnemyThread et;

    public ThreadManager() {
        mutex = new GameMutex();
        gft = new GUIRefreshThread(mutex);
        et = new EnemyThread(mutex);

        gft.start();
    }

    public void killEnemyThread(){
        try {
            mutex.endEnemyThread();
            et.join();
        } catch (InterruptedException ignored) {
        }
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

    public void joinEnemyThread() throws InterruptedException {
        et.join();
    }
}
