package Game.Threads;

import Game.GameManager;

public class GUIRefreshThread extends Thread{

    private static final int FRAMERATE = 1000/60;
    private final GameMutex mutex;

    public GUIRefreshThread(GameMutex mutex) {
        this.mutex = mutex;
    }

    @Override
    public void run() {
        while (true){
            mutex.frameStop();
            GameManager.refresh();
            mutex.frameUnstop();
            try {
                sleep(FRAMERATE);
            } catch (InterruptedException e) {
                throw new RuntimeException(STR."GUIRefreshThread error: \{e.getMessage()}");
            }
        }
    }
}
