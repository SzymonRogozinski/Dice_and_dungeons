package GUI.FightGUI.Components;

import Fight.Statuses.GameStatus;
import Game.GameActionQueue;
import Game.GameManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class StatusMouseListener implements MouseListener {

    private final GameStatus status;

    public StatusMouseListener(GameStatus status) {
        this.status = status;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        GameActionQueue.action(()-> GameManager.getFight().setStatusLog(status.info()));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        GameActionQueue.action(()->GameManager.getFight().setStatusLog(""));
    }
}
