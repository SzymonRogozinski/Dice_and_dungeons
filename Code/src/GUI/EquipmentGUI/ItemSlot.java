package GUI.EquipmentGUI;

import Equipment.Items.Item;
import Equipment.Items.ItemQuality;
import GUI.Compents.GameLabel;
import GUI.GUISettings;
import Game.GameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ItemSlot extends JPanel {

    private final GameLabel label;
    private final ImageIcon emptySlotIcon;
    private final int slotNumber, slotType;
    private Item item;

    public ItemSlot(Item item, ImageIcon emptySlotIcon, int slotNumber, int slotType) {
        this.item = item;
        this.emptySlotIcon = emptySlotIcon;
        this.slotNumber = slotNumber;
        this.slotType = slotType;
        ItemSlotMouseListener mouseListener = new ItemSlotMouseListener(this);

        this.setPreferredSize(new Dimension(GUISettings.ITEM_ICON_SIZE, GUISettings.ITEM_ICON_SIZE));
        this.setBackground(Color.BLACK);
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        layout.setHgap(0);
        layout.setVgap(0);
        this.setLayout(layout);

        label = new GameLabel(
                item == null ? emptySlotIcon : item.getIcon(), SwingConstants.CENTER,
                GUISettings.ITEM_ICON_SIZE, GUISettings.ITEM_ICON_SIZE,
                Color.WHITE,
                SwingConstants.CENTER, SwingConstants.CENTER
        );

        this.add(label);
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(EquipmentView.getMouseMotionAdp());
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public int getSlotType() {
        return slotType;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
        label.setIcon(item == null ? emptySlotIcon : item.getIcon());
        if (item == null)
            this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        else if (item.getQuality() == ItemQuality.COMMON)
            this.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        else if (item.getQuality() == ItemQuality.RARE)
            this.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
        else if (item.getQuality() == ItemQuality.LEGENDARY)
            this.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 1));
    }

    private class ItemSlotMouseListener implements MouseListener {

        private final ItemSlot reference;

        public ItemSlotMouseListener(ItemSlot reference) {
            this.reference = reference;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            //Select item
            GameManager.getEquipment().setClickedItem(reference);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            //Send info
            GameManager.getEquipment().equipItem();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            GameManager.getEquipment().setPointedItem(reference);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            GameManager.getEquipment().setPointedItem(null);
        }
    }

}
