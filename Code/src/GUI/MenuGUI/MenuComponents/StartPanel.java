package GUI.MenuGUI.MenuComponents;

import GUI.Compents.GameButton;
import GUI.Compents.GameLabel;
import GUI.GUISettings;
import Game.GameManager;
import Game.GameStates;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class StartPanel extends JPanel {

    private final GameButton start;
    private final GameLabel status;

    public StartPanel(Border border) {
        FlowLayout layout = new FlowLayout();
        layout.setVgap(GUISettings.SMALL_PANEL_SIZE / 5);
        this.setLayout(layout);
        this.setSize(GUISettings.SMALL_PANEL_SIZE, GUISettings.SMALL_PANEL_SIZE);
        this.setBorder(border);
        this.setBackground(Color.BLACK);

        start = new GameButton(
                "Start game",
                GUISettings.SMALL_PANEL_SIZE * 2 / 3, GUISettings.SMALL_PANEL_SIZE / 4,
                _ -> GameManager.changeState(GameStates.WALKING)
        );
        start.setEnabled(false);

        status = new GameLabel(
                "Your Party is too small!", SwingConstants.CENTER,
                GUISettings.SMALL_PANEL_SIZE, GUISettings.SMALL_PANEL_SIZE / 4,
                Color.WHITE
        );

        this.add(start);
        this.add(status);
    }

    public void refresh() {
        if (GameManager.getMenuModule().getParty().size() == 3) {
            start.setEnabled(true);
            status.setVisible(false);
        } else {
            start.setEnabled(false);
            status.setVisible(true);
        }
    }
}
