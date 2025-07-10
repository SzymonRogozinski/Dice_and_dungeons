package GUI.EquipmentGUI.Components;

import Equipment.Items.ArmorItem;
import GUI.Components.GameLabel;
import GUI.Components.GameTextArea;
import GUI.GUISettings;
import Game.GameManager;
import Game.Tags;

import javax.swing.*;
import java.awt.*;

public class ArmorInfoPanel extends JPanel {

    private final static String[] statsName = new String[]{"Strength", "Endurance", "Intelligence", "Charisma", "Cunning", "Luck"};
    private final GameLabel nameLabel, requirementsLabel;
    private final GameTextArea bonusLabel;

    public ArmorInfoPanel() {
        this.setSize(GUISettings.PANEL_SIZE, GUISettings.SMALL_PANEL_SIZE);
        this.setBackground(Color.BLACK);

        nameLabel = new GameLabel(
                "", SwingConstants.CENTER,
                GUISettings.PANEL_SIZE - 10, GUISettings.SMALL_PANEL_SIZE / 5,
                Color.WHITE, SwingConstants.CENTER, SwingConstants.CENTER,GUISettings.BIG_FONT
        );

        requirementsLabel = new GameLabel(
                "", SwingConstants.LEFT,
                GUISettings.PANEL_SIZE - 10, GUISettings.SMALL_PANEL_SIZE / 7,
                Color.WHITE
        );

        bonusLabel = new GameTextArea(
                GUISettings.PANEL_SIZE - 10, GUISettings.SMALL_PANEL_SIZE *2 / 7
        );

        this.add(nameLabel);
        this.add(bonusLabel);
        this.add(requirementsLabel);
    }

    public void refresh() {
        ArmorItem item = (ArmorItem) GameManager.getEquipment().getPointedItem();

        StringBuilder statsBuilder = new StringBuilder("Statistics:");
        StringBuilder requirementsBuilder = new StringBuilder("Requirements:");

        int[] stats = item.getStats();

        for (int i = 0; i < stats.length; i++) {
            if (stats[i] != 0) {
                statsBuilder.append(" ").append(statsName[i]).append(" ").append(stats[i]).append(",");
            }
        }
        if (statsBuilder.toString().equals("Statistics:")) {
            statsBuilder.append(" None");
        } else {
            statsBuilder.deleteCharAt(statsBuilder.toString().length() - 1);
        }

        Tags[] tags = item.tags;
        for (Tags tag : tags) {
            String s = tag.name().toLowerCase();
            s = s.substring(0, 1).toUpperCase() + s.substring(1);
            requirementsBuilder.append(" ").append(s).append(",");
        }
        if (requirementsBuilder.toString().equals("Requirements:")) {
            requirementsBuilder.append(" None");
        } else {
            requirementsBuilder.deleteCharAt(requirementsBuilder.toString().length() - 1);
        }

        nameLabel.setText(item.name);
        bonusLabel.setText(statsBuilder.toString());
        requirementsLabel.setText(requirementsBuilder.toString());
    }

    public void resize(){
        this.setSize(GUISettings.getResizedValue(GUISettings.PANEL_SIZE), GUISettings.getResizedValue(GUISettings.SMALL_PANEL_SIZE));

        nameLabel.resize();
        requirementsLabel.resize();
        bonusLabel.resize();
    }
}
