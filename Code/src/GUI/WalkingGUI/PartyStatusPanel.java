package GUI.WalkingGUI;

import GUI.Compents.DimensionlessGameLabel;
import GUI.Compents.GameButton;
import GUI.Compents.GameProgressBar;
import GUI.GUISettings;
import Game.GameManager;
import Game.GameStates;
import Game.PlayerInfo;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class PartyStatusPanel extends JPanel {

    private final GameProgressBar healthBar, manaBar;
    private final DimensionlessGameLabel keysLabel;


    public PartyStatusPanel(Border border) {
        //Set display
        this.setSize(GUISettings.SMALL_PANEL_SIZE, GUISettings.PANEL_SIZE);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setBackground(Color.BLACK);
        this.setBorder(border);

        //Set health
        DimensionlessGameLabel health = new DimensionlessGameLabel("Party health", SwingConstants.CENTER, Color.WHITE);
        this.add(health);

        healthBar = new GameProgressBar(Color.RED, GUISettings.SMALL_PANEL_SIZE - 6, GUISettings.SMALL_PANEL_SIZE / 8);
        this.add(healthBar);

        //Set mana
        DimensionlessGameLabel mana = new DimensionlessGameLabel("Party mana", SwingConstants.CENTER, Color.WHITE);
        this.add(mana);

        manaBar = new GameProgressBar(Color.BLUE, GUISettings.SMALL_PANEL_SIZE - 6, GUISettings.SMALL_PANEL_SIZE / 8);
        this.add(manaBar);

        //Set keys
        keysLabel = new DimensionlessGameLabel("Keys: 0", SwingConstants.CENTER, Color.WHITE);
        this.add(keysLabel);

        //Set equipment buttons
        GameButton eqButton = new GameButton("Equipment",
                GUISettings.SMALL_PANEL_SIZE * 8 / 10,
                GUISettings.SMALL_PANEL_SIZE / 5,
                _ -> {
                    GameManager.getEquipment().changeViewToEquipment();
                    GameManager.changeState(GameStates.EQUIPMENT);
                }
        );
        this.add(eqButton);

        GameButton backpackButton = new GameButton(
                "Backpack",
                GUISettings.SMALL_PANEL_SIZE * 8 / 10,
                GUISettings.SMALL_PANEL_SIZE / 5,
                _ -> {
                    GameManager.getEquipment().changeViewToBackpack();
                    GameManager.changeState(GameStates.EQUIPMENT);
                }
        );
        this.add(backpackButton);

    }

    public void refresh() {
        healthBar.setMaximum(PlayerInfo.getParty().getMaxHealth());
        healthBar.setValue(PlayerInfo.getParty().getCurrentHealth());
        healthBar.setString(PlayerInfo.getParty().getCurrentHealth() + "/" + PlayerInfo.getParty().getMaxHealth());

        manaBar.setMaximum(PlayerInfo.getParty().getMaxMana());
        manaBar.setValue(PlayerInfo.getParty().getCurrentMana());
        manaBar.setString(PlayerInfo.getParty().getCurrentMana() + "/" + PlayerInfo.getParty().getMaxMana());

        keysLabel.setText("Keys: " + PlayerInfo.getKeyCollected());

        this.revalidate();
        this.repaint();
    }
}
