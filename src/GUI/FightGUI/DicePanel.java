package GUI.FightGUI;

import Dice.DiceSide;
import GUI.FightGUI.Components.DiceButton;
import GUI.GUISettings;
import Game.GameActionQueue;
import Game.GameManager;
import Game.GameUtils;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class DicePanel extends JPanel {

    private static final int diceNumber = 12;
    private static final int diceRow = 2;
    private static final int diceColumns = 6;
    private static final Border defaultBorder = new JButton().getBorder();
    private final ArrayList<DiceButton> diceButtons;

    public DicePanel(Border border) {
        this.setSize(GUISettings.PANEL_SIZE, GUISettings.SMALL_PANEL_SIZE);
        this.setLayout(new GridLayout(diceRow, diceColumns));
        this.setBorder(border);
        this.setBackground(Color.BLACK);

        diceButtons = new ArrayList<>();
        for(int i=0;i<diceNumber;i++) {
            diceButtons.add(new DiceButton(i));
            this.add(diceButtons.get(i));
        }
    }

    public void showDiceResults(ArrayList<DiceSide> diceResults, int normalDice) {
        int i = 0;
        for (DiceSide res : diceResults) {
            diceButtons.get(i).setBorder(i<normalDice ? defaultBorder : BorderFactory.createLineBorder(Color.CYAN,1));
            diceButtons.get(i++).setIcon(res.getIcon());
        }
        //Fulfill
        while (i < diceNumber) {
            diceButtons.get(i).setBorder(defaultBorder);
            diceButtons.get(i++).setIcon(null);
        }
    }

    public void resize(){
        this.setSize(GUISettings.getResizedValue(GUISettings.PANEL_SIZE), GUISettings.getResizedValue(GUISettings.SMALL_PANEL_SIZE));

        for(DiceButton button:diceButtons)
            button.resize();
    }
}
