package GUI.EquipmentGUI.Components;

import GUI.Components.DimensionlessGameLabel;
import GUI.EquipmentGUI.ItemManagementPanel;
import GUI.GUISettings;

import javax.swing.*;
import java.awt.*;

public class BackpackPanel extends JPanel {

    private final BackpackItemsPanel backpackItemsPanel;
    private final FlowLayout layout;
    private final DimensionlessGameLabel title;

    public BackpackPanel() {
        this.setSize(GUISettings.PANEL_SIZE, GUISettings.PANEL_SIZE);
        layout = new FlowLayout(FlowLayout.CENTER);
        layout.setVgap(25);
        this.setLayout(layout);
        this.setBackground(Color.BLACK);

        title = new DimensionlessGameLabel("Backpack", SwingConstants.CENTER,GUISettings.BIG_FONT, Color.WHITE);

        backpackItemsPanel = new BackpackItemsPanel();

        this.add(title);
        this.add(backpackItemsPanel);
    }

    public void refresh() {
        backpackItemsPanel.refresh();
    }

    public void resize(){
        this.setSize(GUISettings.getResizedValue(GUISettings.PANEL_SIZE), GUISettings.getResizedValue(GUISettings.PANEL_SIZE));

        layout.setVgap(GUISettings.getResizedValue(25));

        title.resize();
        backpackItemsPanel.resize();
    }
}
