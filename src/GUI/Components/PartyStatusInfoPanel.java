package GUI.Components;

import GUI.Components.DimensionlessGameLabel;
import GUI.Components.GameProgressBar;
import GUI.GUISettings;
import Game.PlayerInfo;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class PartyStatusInfoPanel extends JPanel {

    private final GameProgressBar healthBar, manaBar;
    private final DimensionlessGameLabel health,mana,keysLabel;

    public PartyStatusInfoPanel(Border border) {
        this.setSize(GUISettings.SMALL_PANEL_SIZE, GUISettings.PANEL_SIZE);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setBackground(Color.BLACK);
        this.setBorder(border);

        //Set health
        health = new DimensionlessGameLabel("Party health", SwingConstants.CENTER, Color.WHITE);
        this.add(health);

        healthBar = new GameProgressBar(Color.RED, GUISettings.SMALL_PANEL_SIZE - 6, GUISettings.SMALL_PANEL_SIZE / 8);
        this.add(healthBar);

        //Set mana
        mana = new DimensionlessGameLabel("Party mana", SwingConstants.CENTER, Color.WHITE);
        this.add(mana);

        manaBar = new GameProgressBar(Color.BLUE, GUISettings.SMALL_PANEL_SIZE - 6, GUISettings.SMALL_PANEL_SIZE / 8);
        this.add(manaBar);

        //Set keys
        keysLabel = new DimensionlessGameLabel("Keys: 0", SwingConstants.CENTER, Color.WHITE);
        this.add(keysLabel);
    }

    public void refresh(){
        healthBar.setMaximum(PlayerInfo.getParty().getMaxHealth());
        healthBar.setValue(PlayerInfo.getParty().getCurrentHealth());
        healthBar.setString(STR."\{PlayerInfo.getParty().getCurrentHealth()}/\{PlayerInfo.getParty().getMaxHealth()}");

        manaBar.setMaximum(PlayerInfo.getParty().getMaxMana());
        manaBar.setValue(PlayerInfo.getParty().getCurrentMana());
        manaBar.setString(STR."\{PlayerInfo.getParty().getCurrentMana()}/\{PlayerInfo.getParty().getMaxMana()}");

        keysLabel.setText(STR."Keys: \{PlayerInfo.getKeyCollected()}");
    }

    public void resize(){
        this.setSize(GUISettings.getResizedValue(GUISettings.SMALL_PANEL_SIZE), GUISettings.getResizedValue(GUISettings.PANEL_SIZE));

        health.resize();
        mana.resize();
        keysLabel.resize();
        healthBar.resize();
        manaBar.resize();
    }
}
