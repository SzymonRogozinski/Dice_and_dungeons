package GUI.FightGUI.Components;

import GUI.GUISettings;
import Game.GameActionQueue;
import Game.GameManager;
import Game.GameUtils;

import javax.swing.*;
import java.awt.*;

public class DiceButton extends JButton {

    private static final int diceIconSize = GUISettings.SMALL_PANEL_SIZE/2;
    private ImageIcon icon;

    public DiceButton(int index) {
        this.addActionListener(_ -> GameActionQueue.action(()-> GameManager.getFight().rerollDice(index)));

        this.setBackground(Color.BLACK);
    }

    public void setIcon(ImageIcon icon) {
        if(icon==null){
            super.setIcon(null);
            this.icon=null;
        }else {
            super.setIcon(GameUtils.resizeIcon(icon, GUISettings.getResizedValue(diceIconSize)));
            this.icon = icon;
        }
    }

    public void resize(){
        if(icon!=null)
            super.setIcon(GameUtils.resizeIcon(icon, GUISettings.getResizedValue(diceIconSize)));
    }
}
