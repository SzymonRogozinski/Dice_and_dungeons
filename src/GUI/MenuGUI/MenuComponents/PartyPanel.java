package GUI.MenuGUI.MenuComponents;

import GUI.Compents.GameButton;
import GUI.Compents.GameLabel;
import GUI.GUISettings;
import Game.GameManager;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class PartyPanel extends JPanel {

    private final GameLabel[] characterLabels;

    public PartyPanel(Border border) {
        //Set display
        this.setSize(GUISettings.SMALL_PANEL_SIZE, GUISettings.PANEL_SIZE);
        FlowLayout layout = new FlowLayout();
        layout.setVgap(GUISettings.SMALL_PANEL_SIZE / 10);
        this.setLayout(layout);
        this.setBackground(Color.BLACK);
        this.setBorder(border);

        //Set Party elements
        GameLabel header = new GameLabel(
                "Party", SwingConstants.CENTER,
                GUISettings.SMALL_PANEL_SIZE, GUISettings.SMALL_PANEL_SIZE / 5,
                Color.WHITE
        );
        header.setFont(GUISettings.BIG_FONT);

        this.add(header);

        characterLabels = new GameLabel[3];
        for (int i = 0; i < 3; i++) {
            characterLabels[i] = new GameLabel(
                    "-", SwingConstants.CENTER,
                    GUISettings.SMALL_PANEL_SIZE, GUISettings.SMALL_PANEL_SIZE / 8,
                    Color.WHITE
            );
            this.add(characterLabels[i]);
        }

        // Set go back button
        GameButton goBack = new GameButton(
                "Go back",
                GUISettings.SMALL_PANEL_SIZE * 2 / 3, GUISettings.SMALL_PANEL_SIZE / 5,
                _ -> GameManager.getMenuModule().changeToStart()
        );
        this.add(goBack);
    }

    public void refresh() {
        for (int i = 0; i < 3; i++) {
            try {
                characterLabels[i].setText(GameManager.getMenuModule().getParty().get(i).getName());
            } catch (IndexOutOfBoundsException e) {
                characterLabels[i].setText("-");
            }
        }
        this.repaint();
        this.revalidate();
    }
}
