package GUI.WalkingGUI;

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
    }

    //Not used
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            //Up
            case 38, 87 -> GameManager.getWalkingManager().getWalking().playerMove(0, -1);
            //Down
            case 40, 83 -> GameManager.getWalkingManager().getWalking().playerMove(0, 1);
            //Right
            case 39, 68 -> GameManager.getWalkingManager().getWalking().playerMove(1, 0);
            //Left
            case 37, 65 -> GameManager.getWalkingManager().getWalking().playerMove(-1, 0);
        }
    }
}
