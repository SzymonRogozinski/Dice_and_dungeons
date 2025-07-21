package GUI.FightGUI;

import GUI.FightGUI.Components.StopPanel;
import GUI.GUISettings;
import Game.GameActionQueue;
import Game.GameManager;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class ActionPanel extends JPanel {
    private final CardLayout layout;
    private final ActionListPanel actions;
    private final DicePanel dice;
    private final StopPanel pauseScreen, enemyScreen, goBackScreen;

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
        pauseScreen = new StopPanel(_ -> GameActionQueue.action(this::roll), "roll", border, false);
        enemyScreen = new StopPanel(_ -> GameActionQueue.action(this::enemy), "enemy attack", border, false);
        goBackScreen = new StopPanel(_ -> GameActionQueue.action(this::goBack), "go back", border, true);

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

    public void resize(){
        this.setSize(GUISettings.getResizedValue(GUISettings.PANEL_SIZE), GUISettings.getResizedValue(GUISettings.SMALL_PANEL_SIZE));

        pauseScreen.resize();
        enemyScreen.resize();
        goBackScreen.resize();
        dice.resize();
        actions.resize();
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
}
