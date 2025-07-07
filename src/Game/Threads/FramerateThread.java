package Game.Threads;

import Game.GameActionQueue;
import Game.GameManager;

public class FramerateThread extends Thread{

    private static final int FRAMERATE = 1000/60;

    @Override
    public void run() {
        while (true){
            GameActionQueue.action(GameManager::refresh);
            try {
                sleep(FRAMERATE);
            } catch (InterruptedException e) {
                throw new RuntimeException("GUIRefreshThread error: " + e.getMessage());
            }
        }
    }
}
