package GUI.Shared.Components;

import Game.GameActionQueue;
import Game.GameManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TradeItemSlotMouseListener implements MouseListener {

    private final ItemSlot reference;

    public TradeItemSlotMouseListener(ItemSlot reference) {
        this.reference = reference;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //Select item
        GameActionQueue.action(()-> GameManager.getTradeModule().setClickedSlot(reference));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //Send info
        GameActionQueue.action(()->GameManager.getTradeModule().setTradeItem());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        GameActionQueue.action(()->GameManager.getTradeModule().setPointedItem(reference));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        GameActionQueue.action(()->GameManager.getTradeModule().setPointedItem(null));
    }
}