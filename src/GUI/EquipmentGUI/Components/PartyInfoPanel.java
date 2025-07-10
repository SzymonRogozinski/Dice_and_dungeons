package GUI.EquipmentGUI.Components;

import GUI.Components.DimensionlessGameLabel;
import GUI.Components.GameProgressBar;
import GUI.GUISettings;
import Game.PlayerInfo;

import javax.swing.*;
import java.awt.*;

public class PartyInfoPanel extends JPanel {

    private final GameProgressBar healthBar, manaBar;
    private final FlowLayout layout;
    private final DimensionlessGameLabel health,mana;

    public PartyInfoPanel() {
        this.setPreferredSize(new Dimension(GUISettings.SMALL_PANEL_SIZE - 20, GUISettings.PANEL_SIZE / 4));
        layout = new FlowLayout(FlowLayout.CENTER);
        layout.setVgap(1);
        this.setLayout(layout);
        this.setBackground(Color.BLACK);

        health = new DimensionlessGameLabel("Party health", SwingConstants.CENTER, Color.WHITE);
        this.add(health);

        healthBar = new GameProgressBar(Color.RED, GUISettings.SMALL_PANEL_SIZE - 6, GUISettings.SMALL_PANEL_SIZE / 8);
        this.add(healthBar);

        mana = new DimensionlessGameLabel("Party mana", SwingConstants.CENTER, Color.WHITE);
        this.add(mana);

        manaBar = new GameProgressBar(Color.BLUE, GUISettings.SMALL_PANEL_SIZE - 6, GUISettings.SMALL_PANEL_SIZE / 8);
        this.add(manaBar);

    }

    public void refresh() {
        healthBar.setMaximum(PlayerInfo.getParty().getMaxHealth());
        manaBar.setMaximum(PlayerInfo.getParty().getMaxMana());
        healthBar.setValue(PlayerInfo.getParty().getCurrentHealth());
        String healthString = STR."\{PlayerInfo.getParty().getCurrentHealth()}/\{PlayerInfo.getParty().getMaxHealth()}";
        if (PlayerInfo.getParty().getShield() > 0)
            healthString += STR." +\{PlayerInfo.getParty().getShield()}";
        healthBar.setString(healthString);
        manaBar.setValue(PlayerInfo.getParty().getCurrentMana());
        manaBar.setString(STR."\{PlayerInfo.getParty().getCurrentMana()}/\{PlayerInfo.getParty().getMaxMana()}");
    }

    public void resize(){
        this.setPreferredSize(new Dimension(GUISettings.getResizedValue(GUISettings.SMALL_PANEL_SIZE - 20), GUISettings.getResizedValue(GUISettings.PANEL_SIZE / 4)));

        layout.setVgap(GUISettings.getResizedValue(1));

        health.resize();
        healthBar.resize();
        mana.resize();
        manaBar.resize();
    }
}
