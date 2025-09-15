package GUI.EquipmentGUI.Components;

import Equipment.CharacterEquipment;
import Equipment.Items.Item;
import GUI.Components.DimensionlessGameLabel;
import GUI.GUISettings;
import GUI.Shared.Components.ItemSlot;
import Game.GameManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ItemSlotRow extends JPanel {

    private final ItemSlot[] itemSlots;
    private final int slotType;
    private final FlowLayout layout;
    private final DimensionlessGameLabel title;

    public ItemSlotRow(String name, int slotNumber, ImageIcon[] emptySlotIcons, int slotType) {
        this.setPreferredSize(new Dimension(GUISettings.PANEL_SIZE * 4/5, GUISettings.PANEL_SIZE / 6));
        layout = new FlowLayout(FlowLayout.LEFT);
        layout.setHgap(10);
        layout.setVgap(0);
        this.setLayout(layout);
        this.setBackground(Color.BLACK);

        title = new DimensionlessGameLabel(name, SwingConstants.CENTER, Color.WHITE);

        this.add(title);

        itemSlots = new ItemSlot[slotNumber];
        this.slotType = slotType;

        for (int i = 0; i < slotNumber; i++) {
            itemSlots[i] = new ItemSlot(null, emptySlotIcons[i], i, slotType, true);
            this.add(itemSlots[i]);
        }
    }

    private static <T> ArrayList<Item> castArray(T[] array) {
        ArrayList<Item> target = new ArrayList<>();
        for (T T : array)
            target.add((Item) T);

        return target;
    }

    public void refresh() {
        ArrayList<Item> items;
        if (slotType == CharacterEquipment.ACTION_SLOT)
            items = castArray(GameManager.getEquipment().getCurrentCharacter().getEquipment().getActionItems());
        else if (slotType == CharacterEquipment.SPELL_SLOT)
            items = castArray(GameManager.getEquipment().getCurrentCharacter().getEquipment().getSpellItems());
        else
            items = castArray(GameManager.getEquipment().getCurrentCharacter().getEquipment().getArmorItems());

        for (int i = 0; i < itemSlots.length; i++)
            itemSlots[i].setItem(items.get(i));
    }

    public void resize(){
        this.setPreferredSize(new Dimension(GUISettings.getResizedValue(GUISettings.PANEL_SIZE * 4/5), GUISettings.getResizedValue(GUISettings.PANEL_SIZE / 6)));

        layout.setHgap(GUISettings.getResizedValue(10));

        title.resize();

        for(ItemSlot slot: itemSlots)
            slot.resize();
    }
}
