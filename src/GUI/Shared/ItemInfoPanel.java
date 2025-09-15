package GUI.Shared;

import Equipment.Items.*;
import GUI.Shared.Components.ArmorInfoPanel;
import GUI.Shared.Components.DiceItemInfoPanel;
import GUI.Shared.Components.DiceLessItemInfoPanel;
import GUI.GUISettings;
import GUI.Shared.Components.PointedItemLambda;
import Game.GameManager;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ItemInfoPanel extends JPanel {

    private final CardLayout layout;
    private final DiceItemInfoPanel diceItemInfoPanel;
    private final ArmorInfoPanel armorInfoPanel;
    private final DiceLessItemInfoPanel diceLessItemInfoPanel;
    private final PointedItemLambda lambda;

    public ItemInfoPanel(Border border, PointedItemLambda lambda) {
        this.setSize(GUISettings.PANEL_SIZE, GUISettings.SMALL_PANEL_SIZE);
        this.layout = new CardLayout();
        this.setLayout(layout);
        this.setBorder(border);
        this.setBackground(Color.BLACK);
        this.lambda=lambda;

        JPanel emptyPanel = new JPanel();
        emptyPanel.setBackground(Color.BLACK);

        diceItemInfoPanel = new DiceItemInfoPanel(lambda);
        armorInfoPanel = new ArmorInfoPanel(lambda);
        diceLessItemInfoPanel = new DiceLessItemInfoPanel(lambda);

        this.add(emptyPanel, "Empty");
        this.add(diceItemInfoPanel, "Dice");
        this.add(armorInfoPanel, "Armor");
        this.add(diceLessItemInfoPanel, "Diceless");
    }

    public void refresh() {
        Item item = lambda.getPointedItem();
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
