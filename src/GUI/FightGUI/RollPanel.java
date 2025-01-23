package GUI.FightGUI;

import GUI.Compents.GameLabel;
import GUI.GUISettings;
import Game.GameManager;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class RollPanel extends JPanel {
    private final GameLabel reroll;

    public RollPanel(Border border) {
        this.setLayout(new GridLayout(3, 1));
        this.setSize(GUISettings.SMALL_PANEL_SIZE, GUISettings.SMALL_PANEL_SIZE);
        this.setBackground(Color.BLACK);
        this.setBorder(border);

        reroll = new GameLabel(
                "", SwingConstants.CENTER,
                GUISettings.SMALL_PANEL_SIZE, GUISettings.SMALL_PANEL_SIZE / 3,
                Color.WHITE
        );

        JButton skipButton = new JButton("Skip and sum up");
        skipButton.addActionListener(_ -> {
            if (!GameManager.getFight().isDiceResultNull())
                sumUpDicePool();
        });

        this.add(reroll);
        this.add(skipButton);
    }


    public void rerollsChange() {
        reroll.setText("You have " + GameManager.getFight().getRerolls() + " rerolls.");
    }

    private void sumUpDicePool() {
        GameManager.getFight().endAction();
    }

}
