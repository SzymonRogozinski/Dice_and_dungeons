package GUI.Shared.Components;

import Game.GameActionQueue;
import Game.GameManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EquipmentItemSlotMouseListener implements MouseListener {

    private final ItemSlot reference;

    public EquipmentItemSlotMouseListener(ItemSlot reference) {
        this.reference = reference;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //Select item
        GameActionQueue.action(()-> GameManager.getEquipment().setClickedItem(reference));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //Send info
        GameActionQueue.action(()->GameManager.getEquipment().equipItem());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        GameActionQueue.action(()->GameManager.getEquipment().setPointedItem(reference));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        GameActionQueue.action(()->GameManager.getEquipment().setPointedItem(null));
    }
}
