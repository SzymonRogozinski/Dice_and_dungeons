package GUI.WalkingGUI;

import Game.GameActionQueue;
import Game.GameManager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class WalkingKeyListener implements KeyListener {

    public WalkingKeyListener() {
        super();
    }

    //Not used
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            //Up
            case 38, 87 -> GameActionQueue.action(()->GameManager.getWalkingManager().getWalking().playerMove(0, -1));
            //Down
            case 40, 83 -> GameActionQueue.action(()->GameManager.getWalkingManager().getWalking().playerMove(0, 1));
            //Right
            case 39, 68 -> GameActionQueue.action(()->GameManager.getWalkingManager().getWalking().playerMove(1, 0));
            //Left
            case 37, 65 -> GameActionQueue.action(()->GameManager.getWalkingManager().getWalking().playerMove(-1, 0));
        }
    }

    //Not used
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //Reset
        switch (e.getKeyCode()) {
            case 38, 87,40, 83,39, 68,37, 65 -> GameActionQueue.action(() -> GameManager.getWalkingManager().getWalking().playerMove(0, 0));
        }
    }
}
