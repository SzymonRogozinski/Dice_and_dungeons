package GUI.EquipmentGUI;

import Equipment.Items.Item;
import GUI.GUISettings;

import javax.swing.*;
import java.awt.*;

public class ItemSlot extends JPanel {
    
    private Item item;
    private JLabel label;
    private ImageIcon emptySlotIcon;

    public ItemSlot(Item item, ImageIcon emptySlotIcon) {
        this.item=item;
        this.emptySlotIcon=emptySlotIcon;

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
    }


    public void setItem(Item item){
        this.item=item;
        label.setIcon(item==null?emptySlotIcon:item.getIcon());
    }


}
