package GUI.EquipmentGUI.Components;

import Equipment.CharacterEquipment;
import Equipment.Items.Item;
import GUI.GUISettings;
import GUI.Shared.Components.ItemSlot;
import Game.PlayerInfo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BackpackItemsPanel extends JPanel {

    private static final ImageIcon BAG_SLOT_ICON = new ImageIcon("Texture/EmptySlots/slot-bag.png");
    private final ItemSlot[] itemSlots;

    public BackpackItemsPanel() {
        this.setPreferredSize(new Dimension(GUISettings.ITEM_ICON_SIZE * 7, GUISettings.ITEM_ICON_SIZE * 6));
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        layout.setVgap(0);
        layout.setHgap(0);
        this.setLayout(layout);
        this.setBackground(Color.BLACK);

        itemSlots = new ItemSlot[42];

        for (int i = 0; i < 42; i++) {
            itemSlots[i] = new ItemSlot(null, BAG_SLOT_ICON, i, CharacterEquipment.BAG_SLOT, true);
            this.add(itemSlots[i]);
        }
    }

    public void refresh() {
        int i = 0;
        ArrayList<Item> items = PlayerInfo.getParty().getBackpack().getPageOfItems();
        for (; i < items.size() && i < 42; i++) {
            itemSlots[i].setItem(items.get(i));
        }
        for (; i < 42; i++) {
            itemSlots[i].setItem(null);
        }
    }

    public void resize(){
        this.setPreferredSize(new Dimension(GUISettings.getResizedValue(GUISettings.ITEM_ICON_SIZE * 7), GUISettings.getResizedValue(GUISettings.ITEM_ICON_SIZE * 6)));

        for (int i = 0; i < 42; i++)
            itemSlots[i].resize();
    }
}
