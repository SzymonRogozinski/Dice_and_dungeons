package GUI.WalkingGUI;

import Game.GameActionQueue;
import Game.GameManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ArrowMouseListener implements MouseListener {

    private final int dx,dy;

    public ArrowMouseListener(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        GameActionQueue.action(()->GameManager.getWalkingManager().getWalking().playerMove(dx, dy));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        GameActionQueue.action(() -> GameManager.getWalkingManager().getWalking().playerMove(0, 0));
    }

    //ignored
    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
