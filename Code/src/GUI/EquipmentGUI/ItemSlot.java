package GUI.EquipmentGUI;

import Equipment.Items.Item;
import GUI.GUISettings;

import javax.swing.*;
import java.awt.*;

public class ItemSlot extends JPanel {
    
    private Item item;
    private JLabel label;

    public ItemSlot() {
        this.setPreferredSize(new Dimension(GUISettings.ITEM_ICON_SIZE,GUISettings.ITEM_ICON_SIZE));
        this.setBackground(Color.BLACK);
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));

        label=new JLabel("Icon",SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setPreferredSize(new Dimension(GUISettings.ITEM_ICON_SIZE,GUISettings.ITEM_ICON_SIZE));

        this.add(label);
    }
}
