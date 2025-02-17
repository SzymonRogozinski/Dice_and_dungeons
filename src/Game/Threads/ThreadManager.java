package Game.Threads;

public class ThreadManager {

    private final GameMutex mutex;
    private final GUIRefreshThread gft;
    private EnemyThread et;

    public ThreadManager() {
        mutex = new GameMutex();
        gft = new GUIRefreshThread(mutex,this);
        et = new EnemyThread(mutex);
    }

    public void frameStop(){
        mutex.pauseEnemyThread();
        try {
            synchronized (mutex){
                if(mutex.gameChanging)
                    mutex.screenLock.wait();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("Thread interrupted!?");
        }
    }

    public void frameUnstop(){
        et.resumeThread();
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
}
