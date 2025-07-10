package GUI.EquipmentGUI;

import GUI.GUISettings;
import Game.GameManager;
import GUI.EquipmentGUI.Components.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ItemManagementPanel extends JPanel {

    private final BackpackPanel backpackPanel;
    private final EquipmentPanel equipmentPanel;
    private final CardLayout layout;

    public ItemManagementPanel(Border border) {
        this.setSize(GUISettings.PANEL_SIZE, GUISettings.PANEL_SIZE);
        layout = new CardLayout();
        this.setLayout(layout);
        this.setBorder(border);

        backpackPanel = new BackpackPanel();
        equipmentPanel = new EquipmentPanel();

        this.add(backpackPanel, "Backpack");
        this.add(equipmentPanel, "Equipment");

        layout.show(this, "Equipment");
    }

    public void changeCard(String name) {
        layout.show(this, name);
    }

    public void refresh() {
        if (GameManager.getEquipment() == null)
            return;
        backpackPanel.refresh();
        equipmentPanel.refresh();
    }

    public void resize(){
        this.setSize(GUISettings.getResizedValue(GUISettings.PANEL_SIZE), GUISettings.getResizedValue(GUISettings.PANEL_SIZE));

        backpackPanel.resize();
        equipmentPanel.resize();
    }

}
