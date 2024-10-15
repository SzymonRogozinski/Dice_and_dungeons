package GUI.FightGUI;

import GUI.Compents.DimensionlessGameLabel;
import GUI.Compents.GameLabel;
import GUI.Compents.GameProgressBar;
import GUI.Compents.GameTextArea;
import GUI.GUISettings;
import Game.GameManager;
import Game.PlayerInfo;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class StatusPanel extends JPanel {

    private final GameProgressBar healthBar, manaBar;
    private final GameLabel characterName, statusInfo;
    private final GameTextArea combatLog, combatInfo;

    public StatusPanel(Border border) {
        //Set display
        this.setSize(GUISettings.SMALL_PANEL_SIZE, GUISettings.PANEL_SIZE);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setBackground(Color.BLACK);
        this.setBorder(border);

        //Set health
        DimensionlessGameLabel health = new DimensionlessGameLabel("Party health", SwingConstants.CENTER, Color.WHITE);
        this.add(health);

        healthBar = new GameProgressBar(
                Color.RED,
                GUISettings.SMALL_PANEL_SIZE - 6,
                GUISettings.SMALL_PANEL_SIZE / 8
        );
        this.add(healthBar);

        //Set mana
        DimensionlessGameLabel mana = new DimensionlessGameLabel("Party mana", SwingConstants.CENTER, Color.WHITE);
        this.add(mana);

        manaBar = new GameProgressBar(
                Color.BLUE,
                GUISettings.SMALL_PANEL_SIZE - 6,
                GUISettings.SMALL_PANEL_SIZE / 8
        );
        this.add(manaBar);

        //Set character
        GameLabel character = new GameLabel(
                "Character turn:",
                SwingConstants.CENTER,
                GUISettings.SMALL_PANEL_SIZE - 6,
                GUISettings.SMALL_PANEL_SIZE / 10,
                Color.WHITE
        );
        this.add(character);

        characterName = new GameLabel(
                "",
                SwingConstants.CENTER,
                GUISettings.SMALL_PANEL_SIZE - 6,
                GUISettings.SMALL_PANEL_SIZE / 10,
                Color.WHITE
        );
        this.add(characterName);

        //Set status
        statusInfo = new GameLabel(
                "",
                SwingConstants.CENTER,
                GUISettings.SMALL_PANEL_SIZE - 6,
                GUISettings.SMALL_PANEL_SIZE / 10,
                Color.WHITE
        );
        this.add(statusInfo);

        //Set next move
        combatInfo = new GameTextArea(GUISettings.SMALL_PANEL_SIZE - 6, GUISettings.PANEL_SIZE / 3);
        combatInfo.setEditable(false);
        this.add(combatInfo);

        combatLog = new GameTextArea(GUISettings.SMALL_PANEL_SIZE - 6, GUISettings.PANEL_SIZE / 3);
        combatLog.setEditable(false);
        this.add(combatLog);
    }

    public void refresh() {
        healthBar.setMaximum(PlayerInfo.getParty().getMaxHealth());
        manaBar.setMaximum(PlayerInfo.getParty().getMaxMana());
        healthBar.setValue(PlayerInfo.getParty().getCurrentHealth());
        String healthString = PlayerInfo.getParty().getCurrentHealth() + "/" + PlayerInfo.getParty().getMaxHealth();
        if (PlayerInfo.getParty().getShield() > 0)
            healthString += " +" + PlayerInfo.getParty().getShield();
        healthBar.setString(healthString);
        manaBar.setValue(PlayerInfo.getParty().getCurrentMana());
        manaBar.setString(PlayerInfo.getParty().getCurrentMana() + "/" + PlayerInfo.getParty().getMaxMana());
        characterName.setText(GameManager.getFight().getCharacter().getName());

        statusInfo.setText(GameManager.getFight().getStatusLog());

        //Refresh combat logs
        combatLog.setText(GameManager.getFight().getCombatLog());
        combatInfo.setText(GameManager.getFight().getCombatInfo());

        this.revalidate();
        this.repaint();
    }
}
