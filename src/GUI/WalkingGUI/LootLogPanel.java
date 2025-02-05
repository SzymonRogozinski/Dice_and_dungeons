package GUI.WalkingGUI;

import GUI.Compents.GameTextArea;
import GUI.GUISettings;
import Game.GameManager;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class LootLogPanel extends JPanel {

    private final GameTextArea lootLog;

    public LootLogPanel(Border border) {
        //Set display
        this.setSize(GUISettings.PANEL_SIZE, GUISettings.SMALL_PANEL_SIZE);
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        layout.setVgap(1);
        this.setLayout(layout);
        this.setBackground(Color.BLACK);
        this.setBorder(border);

        lootLog = new GameTextArea(GUISettings.PANEL_SIZE - 5, GUISettings.SMALL_PANEL_SIZE - 8);

        this.add(lootLog);
    }

    public void refresh() {
        lootLog.setText(GameManager.getLootModule().getLootLogText());

        this.repaint();
        this.revalidate();
    }
}
