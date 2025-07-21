package GUI.FightGUI.Components;

import Equipment.Items.SpellItem;
import GUI.Components.DimensionlessGameLabel;
import GUI.Components.GameButton;
import GUI.GUISettings;
import Game.GameActionQueue;
import Game.GameManager;
import Game.PlayerInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SpellButton extends GameButton {

    private final DimensionlessGameLabel spellName,spellCost;

    public SpellButton(String name, int width, int height, ActionListener l, SpellItem spell) {
        super(name, width, height, l);

        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER);

        spellName = new DimensionlessGameLabel(
                spell.shortName, SwingConstants.CENTER,
                GUISettings.BUTTON_FONT,
                Color.BLACK
        );
        spellName.setBackground(GUISettings.TRANSPARENT);

        spellCost = new DimensionlessGameLabel(
                STR." \{spell.getAction().getManaCost()}", SwingConstants.CENTER,
                GUISettings.BUTTON_FONT,
                Color.BLUE
        );
        spellCost.setBackground(GUISettings.TRANSPARENT);

        JPanel buttonTextPanel = new JPanel();
        buttonTextPanel.setLayout(flowLayout);
        buttonTextPanel.setBackground(GUISettings.TRANSPARENT);
        buttonTextPanel.add(spellName);
        buttonTextPanel.add(spellCost);

        this.add(buttonTextPanel);
    }

    @Override
    public void resize(){
        super.resize();

        spellName.resize();
        spellCost.resize();
    }
}
