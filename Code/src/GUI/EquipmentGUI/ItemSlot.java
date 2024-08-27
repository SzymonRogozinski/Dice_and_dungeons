package GUI.EquipmentGUI;

import Equipment.Items.Item;
import Equipment.Items.ItemQuality;
import GUI.GUISettings;
import Game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ItemSlot extends JPanel {
    
    private Item item;
    private JLabel label;
    private ImageIcon emptySlotIcon;
    private ItemSlotMouseListener mouseListener;
    private final int slotNumber,slotType;

    public ItemSlot(Item item, ImageIcon emptySlotIcon,int slotNumber,int slotType) {
        this.item=item;
        this.emptySlotIcon=emptySlotIcon;
        this.slotNumber=slotNumber;
        this.slotType=slotType;
        mouseListener=new ItemSlotMouseListener(this);

        this.setPreferredSize(new Dimension(GUISettings.ITEM_ICON_SIZE,GUISettings.ITEM_ICON_SIZE));
        this.setBackground(Color.BLACK);
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
        FlowLayout layout=new FlowLayout(FlowLayout.CENTER);
        layout.setHgap(0);
        layout.setVgap(0);
        this.setLayout(layout);

        label=new JLabel(item==null?emptySlotIcon:item.getIcon());
        label.setPreferredSize(new Dimension(GUISettings.ITEM_ICON_SIZE,GUISettings.ITEM_ICON_SIZE));
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(Color.WHITE);

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

    public void setItem(Item item){
        this.item=item;
        label.setIcon(item==null?emptySlotIcon:item.getIcon());
        if(item==null)
            this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,1));
        else if (item.getQuality()== ItemQuality.COMMON)
            this.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
        else if (item.getQuality()== ItemQuality.RARE)
            this.setBorder(BorderFactory.createLineBorder(Color.BLUE,1));
        else if (item.getQuality()== ItemQuality.LEGENDARY)
            this.setBorder(BorderFactory.createLineBorder(Color.MAGENTA,1));
    }
    private class ItemSlotMouseListener implements MouseListener{

        private final ItemSlot reference;

        public ItemSlotMouseListener(ItemSlot reference) { this.reference=reference;}

        @Override
        public void mouseClicked(MouseEvent e) {}

        @Override
        public void mousePressed(MouseEvent e) {
            //Select item
            System.out.println("Clicked "+item.name);
            Game.getEquipment().setClickedItem(reference);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            //Send info
            System.out.println("Drop on "+item.name);
            Game.getEquipment().equipItem();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            Game.getEquipment().setPointedItem(reference);}

        @Override
        public void mouseExited(MouseEvent e) {
            Game.getEquipment().setPointedItem(null);
        }
    }


}
