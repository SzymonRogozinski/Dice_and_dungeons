package Game.Threads;

import Game.GameManager;

public class GUIRefreshThread extends Thread{

    private static final int FRAMERATE = 1000/60;
    private final GameMutex mutex;
    private final ThreadManager manager;

    public GUIRefreshThread(GameMutex mutex, ThreadManager manager) {
        this.mutex = mutex;
        this.manager=manager;
    }

    @Override
    public void run() {
        while (true){
            manager.frameStop();
            GameManager.refresh();
            manager.frameUnstop();
            try {
                sleep(FRAMERATE);
            } catch (InterruptedException e) {
                throw new RuntimeException(STR."GUIRefreshThread error: \{e.getMessage()}");
            }
        }
    }
}
