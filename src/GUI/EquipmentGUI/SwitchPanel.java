package GUI.EquipmentGUI;

import GUI.Compents.GameButton;
import GUI.GUISettings;
import Game.GameManager;
import Game.GameStates;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class SwitchPanel extends JPanel {

    //private EquipmentModule equipment;

    public SwitchPanel(Border border) {
        //Set display
        this.setSize(GUISettings.SMALL_PANEL_SIZE, GUISettings.SMALL_PANEL_SIZE);
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        this.setLayout(layout);
        layout.setVgap((int) (GUISettings.SMALL_PANEL_SIZE * 0.1));
        this.setBackground(Color.BLACK);
        this.setBorder(border);

        GameButton eqButton = new GameButton("Equipment",
                (int) (GUISettings.SMALL_PANEL_SIZE * 0.8),
                (int) (GUISettings.SMALL_PANEL_SIZE * 0.20),
                _ -> GameManager.getEquipment().changeViewToEquipment()
        );

        GameButton backButton = new GameButton("Backpack",
                (int) (GUISettings.SMALL_PANEL_SIZE * 0.8),
                (int) (GUISettings.SMALL_PANEL_SIZE * 0.20),
                _ -> GameManager.getEquipment().changeViewToBackpack()
        );

        GameButton returnButton = new GameButton("Close",
                (int) (GUISettings.SMALL_PANEL_SIZE * 0.8),
                (int) (GUISettings.SMALL_PANEL_SIZE * 0.20),
                _ -> GameManager.changeState(GameStates.WALKING)
        );

        this.add(eqButton);
        this.add(backButton);
        this.add(returnButton);

    }
}
