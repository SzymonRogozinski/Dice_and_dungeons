package Game.Threads;

import Game.GameActionQueue;
import Game.GameManager;

public class MovementThread extends Thread{

    private static final int FRAMERATE = 1000/60;

    @Override
    public void run() {
        while (true) {
            GameManager.getWalkingManager().getWalking().makePlayerMove();
            try {
                Thread.sleep(FRAMERATE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
