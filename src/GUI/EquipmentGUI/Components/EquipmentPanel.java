package GUI.EquipmentGUI.Components;

import Equipment.CharacterEquipment;
import GUI.Components.GameLabel;
import GUI.GUISettings;

import javax.swing.*;
import java.awt.*;

public class EquipmentPanel extends JPanel {

    private static final ImageIcon HELM_SLOT_ICON = new ImageIcon("Texture/EmptySlots/slot-helm.png");
    private static final ImageIcon CHEST_SLOT_ICON = new ImageIcon("Texture/EmptySlots/slot-chest.png");
    private static final ImageIcon GAUNTLET_SLOT_ICON = new ImageIcon("Texture/EmptySlots/slot-gauntlet.png");
    private static final ImageIcon LEG_SLOT_ICON = new ImageIcon("Texture/EmptySlots/slot-leg.png");
    private static final ImageIcon SCROLL_SLOT_ICON = new ImageIcon("Texture/EmptySlots/slot-scroll.png");
    private static final ImageIcon DICE_SLOT_ICON = new ImageIcon("Texture/EmptySlots/slot-dice.png");
    private final ItemSlotRow armor, items, spells;
    private final SmallBackpackItemsPanel smallBackpackItemsPanel;
    private final FlowLayout layout;
    private final GameLabel title;

    public EquipmentPanel() {
        this.setSize(GUISettings.PANEL_SIZE, GUISettings.PANEL_SIZE);
        layout = new FlowLayout(FlowLayout.CENTER);
        layout.setVgap(5);
        this.setLayout(layout);
        this.setBackground(Color.BLACK);

        title = new GameLabel(
                "Equipment", SwingConstants.CENTER,
                GUISettings.PANEL_SIZE * 4/5, GUISettings.PANEL_SIZE *2/15,
                Color.WHITE,GUISettings.BIG_FONT
        );

        armor = new ItemSlotRow("Armor", 4, new ImageIcon[]{HELM_SLOT_ICON, GAUNTLET_SLOT_ICON, CHEST_SLOT_ICON, LEG_SLOT_ICON}, CharacterEquipment.ARMOR_SLOT);
        items = new ItemSlotRow("Items", 3, new ImageIcon[]{DICE_SLOT_ICON, DICE_SLOT_ICON, DICE_SLOT_ICON}, CharacterEquipment.ACTION_SLOT);
        spells = new ItemSlotRow("spells", 3, new ImageIcon[]{SCROLL_SLOT_ICON, SCROLL_SLOT_ICON, SCROLL_SLOT_ICON}, CharacterEquipment.SPELL_SLOT);

        smallBackpackItemsPanel = new SmallBackpackItemsPanel();

        this.add(title);
        this.add(armor);
        this.add(items);
        this.add(spells);
        this.add(smallBackpackItemsPanel);
    }

    public void refresh() {
        armor.refresh();
        items.refresh();
        spells.refresh();
        smallBackpackItemsPanel.refresh();
    }

    public void resize(){
        this.setSize(GUISettings.getResizedValue(GUISettings.PANEL_SIZE), GUISettings.getResizedValue(GUISettings.PANEL_SIZE));

        layout.setVgap(GUISettings.getResizedValue(5));

        title.resize();
        armor.resize();
        items.resize();
        spells.resize();
        smallBackpackItemsPanel.resize();
    }
}
