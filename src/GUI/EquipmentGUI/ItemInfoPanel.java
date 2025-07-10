package GUI.EquipmentGUI;

import Equipment.Items.*;
import GUI.EquipmentGUI.Components.ArmorInfoPanel;
import GUI.EquipmentGUI.Components.DiceItemInfoPanel;
import GUI.EquipmentGUI.Components.DiceLessItemInfoPanel;
import GUI.GUISettings;
import Game.GameManager;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ItemInfoPanel extends JPanel {

    private final CardLayout layout;
    private final DiceItemInfoPanel diceItemInfoPanel;
    private final ArmorInfoPanel armorInfoPanel;
    private final DiceLessItemInfoPanel diceLessItemInfoPanel;

    public ItemInfoPanel(Border border) {
        this.setSize(GUISettings.PANEL_SIZE, GUISettings.SMALL_PANEL_SIZE);
        this.layout = new CardLayout();
        this.setLayout(layout);
        this.setBorder(border);
        this.setBackground(Color.BLACK);

        JPanel emptyPanel = new JPanel();
        emptyPanel.setBackground(Color.BLACK);

        diceItemInfoPanel = new DiceItemInfoPanel();
        armorInfoPanel = new ArmorInfoPanel();
        diceLessItemInfoPanel = new DiceLessItemInfoPanel();

        this.add(emptyPanel, "Empty");
        this.add(diceItemInfoPanel, "Dice");
        this.add(armorInfoPanel, "Armor");
        this.add(diceLessItemInfoPanel, "Diceless");
    }

    public void refresh() {
        Item item = GameManager.getEquipment().getPointedItem();
        if (item == null) {
            layout.show(this, "Empty");
        } else if (item instanceof ArmorItem) {
            layout.show(this, "Armor");
            armorInfoPanel.refresh();
        } else if (item instanceof ActionItem || item instanceof SpellItem) {
            layout.show(this, "Dice");
            diceItemInfoPanel.refresh();
        } else if (item instanceof UsableItem) {
            layout.show(this, "Diceless");
            diceLessItemInfoPanel.refresh();
        }
    }

    public void resize(){
        this.setSize(GUISettings.getResizedValue(GUISettings.PANEL_SIZE), GUISettings.getResizedValue(GUISettings.SMALL_PANEL_SIZE));

        armorInfoPanel.resize();
        diceLessItemInfoPanel.resize();
        diceItemInfoPanel.resize();
    }

}
