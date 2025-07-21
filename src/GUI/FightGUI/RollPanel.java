package GUI.FightGUI;

import GUI.Components.DimensionlessGameButton;
import GUI.Components.GameButton;
import GUI.Components.GameLabel;
import GUI.GUISettings;
import Game.GameActionQueue;
import Game.GameManager;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class RollPanel extends JPanel {
    private final GameLabel reroll;
    private final DimensionlessGameButton skipButton;

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

        skipButton = new DimensionlessGameButton("Skip and sum up", _ -> {
            if (!GameManager.getFight().isDiceResultNull())
                GameActionQueue.action(this::sumUpDicePool);
        });

        this.add(reroll);
        this.add(skipButton);
    }


    public void rerollsChange() {
        reroll.setText(STR."You have \{GameManager.getFight().getRerolls()} rerolls.");
    }

    private void sumUpDicePool() {
        GameManager.getFight().endAction();
    }

    public void resize(){
        this.setSize(GUISettings.getResizedValue(GUISettings.SMALL_PANEL_SIZE), GUISettings.getResizedValue(GUISettings.SMALL_PANEL_SIZE));

        reroll.resize();
        skipButton.resize();
    }

}
