package GUI.FightGUI;

import GUI.GUISettings;
import Game.GameManager;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class ActionPanel extends JPanel {
    private final CardLayout layout;
    private final ActionListPanel actions;
    private final DicePanel dice;

    public ActionPanel(Border border) {
        //Set display
        this.setSize(GUISettings.PANEL_SIZE, GUISettings.SMALL_PANEL_SIZE);
        this.layout = new CardLayout();
        this.setLayout(layout);
        this.setBackground(Color.BLACK);

        //Card components
        actions = new ActionListPanel(border);
        dice = new DicePanel(border);

        //Pause Screen
        JPanel pauseScreen = new StopPanel(_ -> roll(), "roll", border, false);
        JPanel enemyScreen = new StopPanel(_ -> enemy(), "enemy attack", border, false);
        JPanel goBackScreen = new StopPanel(_ -> goBack(), "go back", border, true);

        this.add("Actions", actions);
        this.add("Pause", pauseScreen);
        this.add("Enemy", enemyScreen);
        this.add("Dice", dice);
        this.add("GoBack", goBackScreen);
    }

    public DicePanel getDicePanel() {
        return dice;
    }

    public void changePage(String pageName) {
        layout.show(this, pageName);
    }

    public ActionListPanel getActions() {
        return actions;
    }

    private void roll() {
        if (GameManager.getFight().isNoRoll()) {
            GameManager.getFight().endAction();
            return;
        }
        changePage("Dice");
        GameManager.getFight().rollDices();
    }

    private void goBack() {
        GameManager.getFight().goBackToChoose();
    }

    private void enemy() {
        GameManager.getFight().endAction();
    }

    private class StopPanel extends JPanel {

        StopPanel(ActionListener l, String buttonName, Border border, boolean isSmall) {
            this.setSize(GUISettings.PANEL_SIZE, GUISettings.SMALL_PANEL_SIZE);
            this.setLayout(new GridLayout(3, 1));
            this.setBackground(Color.BLACK);
            this.setBorder(border);

            JButton pauseButton = new JButton(buttonName);
            pauseButton.addActionListener(l);
            this.add(new JLabel());
            if (isSmall) {
                pauseButton.setPreferredSize(new Dimension(GUISettings.PANEL_SIZE / 3, GUISettings.SMALL_PANEL_SIZE / 6));

                JPanel reducer = new JPanel();
                reducer.setLayout(new FlowLayout(FlowLayout.CENTER));
                reducer.setBackground(Color.BLACK);
                reducer.add(pauseButton);
                this.add(reducer);
            } else {
                this.add(pauseButton);
            }

        }
    }
}
