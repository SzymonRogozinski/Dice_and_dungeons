package GUI.FightGUI.Components;

import Game.GameActionQueue;
import Game.GameManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ButtonItemMouseListener implements MouseListener {

    private final String itemName;

    public ButtonItemMouseListener(String itemName) {
        this.itemName = itemName;
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
        GameActionQueue.action(()-> GameManager.getFight().setCombatInfo(STR."Pointed item: \{itemName}"));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        GameActionQueue.action(()->GameManager.getFight().setCombatInfo(""));
    }
}
