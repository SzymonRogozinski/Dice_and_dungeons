package GUI.EquipmentGUI.Components;

import Dice.DiceAction.DiceAction;
import Equipment.Items.UsableItem;
import GUI.Components.GameLabel;
import GUI.GUISettings;
import Game.GameManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DiceLessItemInfoPanel extends JPanel{

    private final GameLabel nameLabel, effectLabel, quantityLabel, targetLabel;

    public DiceLessItemInfoPanel() {
        this.setSize(GUISettings.PANEL_SIZE, GUISettings.SMALL_PANEL_SIZE);
        this.setBackground(Color.BLACK);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));

        nameLabel = new GameLabel(
                "", SwingConstants.CENTER,
                GUISettings.PANEL_SIZE - 10, GUISettings.SMALL_PANEL_SIZE / 5,
                Color.WHITE,GUISettings.BIG_FONT
        );

        effectLabel = new GameLabel(
                "", SwingConstants.LEFT,
                GUISettings.PANEL_SIZE - 10, GUISettings.SMALL_PANEL_SIZE / 7,
                Color.WHITE
        );

        targetLabel = new GameLabel(
                "", SwingConstants.LEFT,
                GUISettings.PANEL_SIZE - 10, GUISettings.SMALL_PANEL_SIZE / 7,
                Color.WHITE
        );

        quantityLabel = new GameLabel(
                "", SwingConstants.LEFT,
                GUISettings.PANEL_SIZE - 10, GUISettings.SMALL_PANEL_SIZE / 7,
                Color.WHITE
        );

        this.add(nameLabel);
        this.add(effectLabel);
        this.add(targetLabel);
        this.add(quantityLabel);
    }

    public void refresh() {
        UsableItem item = (UsableItem) GameManager.getEquipment().getPointedItem();
        targetLabel.setText(STR."Target: \{item.getAction().getTarget().toString()}");
        StringBuilder builder = new StringBuilder("Effects:");

        ArrayList<DiceAction> actions = item.getAction().getActionFactories();

        for (DiceAction action : actions) {
            builder.append(" ").append(action.getIdentification()).append(" ").append(action.getValue()).append(",");
        }
        if (builder.toString().equals("Effects:")) {
            builder.append(" None");
        } else {
            builder.deleteCharAt(builder.toString().length() - 1);
        }

        nameLabel.setText(item.name);
        effectLabel.setText(builder.toString());
        quantityLabel.setText(STR."Uses: \{item.getNumberOfItems()}");
    }

    public void resize(){
        this.setSize(GUISettings.getResizedValue(GUISettings.PANEL_SIZE), GUISettings.getResizedValue(GUISettings.SMALL_PANEL_SIZE));

        nameLabel.resize();
        effectLabel.resize();
        quantityLabel.resize();
        targetLabel.resize();
    }
}
