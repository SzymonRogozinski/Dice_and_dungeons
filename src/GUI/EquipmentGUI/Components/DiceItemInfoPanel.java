package GUI.EquipmentGUI.Components;

import Equipment.Items.ActionItem;
import Equipment.Items.Item;
import Equipment.Items.SpellItem;
import GUI.Components.GameLabel;
import GUI.GUISettings;
import Game.GameManager;
import Game.Tags;

import javax.swing.*;
import java.awt.*;

public class DiceItemInfoPanel extends JPanel {

    private final static int MANA_COST_TO_ALL_PROPORTION = 2;
    private final GameLabel nameLabel, requirements, manaCost, target, attribute;
    private final DiceSidesPanel diceSidesPanel;

    public DiceItemInfoPanel() {
        this.setSize(GUISettings.PANEL_SIZE, GUISettings.SMALL_PANEL_SIZE);
        this.setBackground(Color.BLACK);

        nameLabel = new GameLabel(
                "", SwingConstants.CENTER,
                GUISettings.PANEL_SIZE - 10, GUISettings.SMALL_PANEL_SIZE / 7,
                Color.WHITE, SwingConstants.CENTER, SwingConstants.CENTER,GUISettings.BIG_FONT
        );

        diceSidesPanel = new DiceSidesPanel();

        requirements = new GameLabel(
                "", SwingConstants.LEFT,
                GUISettings.PANEL_SIZE / MANA_COST_TO_ALL_PROPORTION - 20,
                GUISettings.SMALL_PANEL_SIZE / 10,
                Color.WHITE
        );

        target = new GameLabel(
                "", SwingConstants.LEFT,
                GUISettings.PANEL_SIZE / MANA_COST_TO_ALL_PROPORTION - 20,
                GUISettings.SMALL_PANEL_SIZE / 10,
                Color.WHITE
        );

        attribute = new GameLabel(
                "", SwingConstants.LEFT,
                GUISettings.PANEL_SIZE / MANA_COST_TO_ALL_PROPORTION - 20,
                GUISettings.SMALL_PANEL_SIZE / 10,
                Color.WHITE
        );

        manaCost = new GameLabel(
                "", SwingConstants.LEFT,
                GUISettings.PANEL_SIZE / MANA_COST_TO_ALL_PROPORTION - 20,
                GUISettings.SMALL_PANEL_SIZE / 10,
                Color.WHITE
        );

        this.add(nameLabel);
        this.add(diceSidesPanel);
        this.add(requirements);
        this.add(target);
        this.add(attribute);
        this.add(manaCost);
    }

    public void refresh() {
        Item item = GameManager.getEquipment().getPointedItem();

        nameLabel.setText(item.name);

        if (item instanceof ActionItem aItem) {
            diceSidesPanel.setDiceSides(aItem.getAction().getDice().sides());
            manaCost.setText("");
            target.setText(STR."Target: \{aItem.getAction().getTarget().toString()}");
            attribute.setText(STR."Amplify by: \{aItem.getScaleAttribute()}");
        } else if (item instanceof SpellItem sItem) {
            diceSidesPanel.setDiceSides(sItem.getAction().getDice().sides());
            manaCost.setText(STR."Mana: \{sItem.getAction().getManaCost()}");
            target.setText(STR."target: \{sItem.getAction().getTarget().toString()}");
            attribute.setText(STR."amplify by: \{sItem.getScaleAttribute()}");
        }

        StringBuilder requirementsBuilder = new StringBuilder("Requirements:");

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

        requirements.setText(requirementsBuilder.toString());
    }

    public void resize(){
        this.setSize(GUISettings.getResizedValue(GUISettings.PANEL_SIZE), GUISettings.getResizedValue(GUISettings.SMALL_PANEL_SIZE));

        nameLabel.resize();
        diceSidesPanel.resize();
        requirements.resize();
        target.resize();
        attribute.resize();
        manaCost.resize();
    }
}
