package GUI.EquipmentGUI;

import GUI.Components.GameButton;
import GUI.GUISettings;
import Game.GameActionQueue;
import Game.GameManager;
import Game.GameStates;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class SwitchPanel extends JPanel {

    private final FlowLayout layout;
    private final GameButton eqButton,backButton,returnButton;

    public SwitchPanel(Border border) {
        //Set display
        this.setSize(GUISettings.SMALL_PANEL_SIZE, GUISettings.SMALL_PANEL_SIZE);
        layout = new FlowLayout(FlowLayout.CENTER);
        this.setLayout(layout);
        layout.setVgap(GUISettings.SMALL_PANEL_SIZE /10);
        this.setBackground(Color.BLACK);
        this.setBorder(border);

        eqButton = new GameButton("Equipment",
                (int) (GUISettings.SMALL_PANEL_SIZE * 0.8),
                (int) (GUISettings.SMALL_PANEL_SIZE * 0.20),
                _ -> GameActionQueue.action(()->GameManager.getEquipment().changeViewToEquipment())
        );

        backButton = new GameButton("Backpack",
                (int) (GUISettings.SMALL_PANEL_SIZE * 0.8),
                (int) (GUISettings.SMALL_PANEL_SIZE * 0.20),
                _ -> GameActionQueue.action(()->GameManager.getEquipment().changeViewToBackpack())
        );

        returnButton = new GameButton("Close",
                (int) (GUISettings.SMALL_PANEL_SIZE * 0.8),
                (int) (GUISettings.SMALL_PANEL_SIZE * 0.20),
                _ -> GameActionQueue.action(()->GameManager.changeState(GameStates.WALKING))
        );

        this.add(eqButton);
        this.add(backButton);
        this.add(returnButton);
    }

    public void resize(){
        this.setSize(GUISettings.getResizedValue(GUISettings.SMALL_PANEL_SIZE), GUISettings.getResizedValue(GUISettings.SMALL_PANEL_SIZE));

        layout.setVgap(GUISettings.getResizedValue(GUISettings.SMALL_PANEL_SIZE /10));
        eqButton.resize();
        backButton.resize();
        returnButton.resize();
    }
}
