package GUI.EquipmentGUI;

import Equipment.EquipmentModule;
import Equipment.Items.Item;
import GUI.GUISettings;
import Game.GameCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ItemSlot extends JPanel {
    
    private Item item;
    private JLabel label;
    private ImageIcon emptySlotIcon;
    private ItemSlotMouseListener mouseListener;

    public ItemSlot(Item item, ImageIcon emptySlotIcon) {
        this.item=item;
        this.emptySlotIcon=emptySlotIcon;
        mouseListener=new ItemSlotMouseListener();

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
    }


    public void setItem(Item item){
        this.item=item;
        label.setIcon(item==null?emptySlotIcon:item.getIcon());
    }
    private class ItemSlotMouseListener implements MouseListener{



        public ItemSlotMouseListener() {}

        @Override
        public void mouseClicked(MouseEvent e) {}

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {GameCollection.getEquipment().setPointedItem(item);}

        @Override
        public void mouseExited(MouseEvent e) {
            GameCollection.getEquipment().setPointedItem(null);
        }
    }


}
