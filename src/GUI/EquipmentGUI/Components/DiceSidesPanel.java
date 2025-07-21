package GUI.EquipmentGUI.Components;

import Dice.DiceSide;
import GUI.Components.GameLabel;
import GUI.GUISettings;
import Game.GameUtils;

import javax.swing.*;
import java.awt.*;

public class DiceSidesPanel extends JPanel {

    private static final int DICE_ICON_SIZE = 60;
    private final GameLabel[] diceSides;
    private final FlowLayout diceLayout;
    private final int diceSize;

    public DiceSidesPanel() {
        diceSize = Math.min((GUISettings.PANEL_SIZE - 10) / 6, GUISettings.SMALL_PANEL_SIZE / 2);
        this.setPreferredSize(new Dimension(diceSize * 6 + 6, diceSize));
        this.setBackground(Color.BLACK);
        diceLayout = new FlowLayout(FlowLayout.CENTER);
        diceLayout.setHgap(1);
        this.setLayout(diceLayout);

        this.diceSides = new GameLabel[6];

        for (int i = 0; i < 6; i++) {
            diceSides[i] = new GameLabel(SwingConstants.CENTER,diceSize, diceSize,Color.BLACK,
                    SwingConstants.CENTER, SwingConstants.CENTER);
            this.add(diceSides[i]);
        }

    }

    void setDiceSides(DiceSide[] sides) {
        for (int i = 0; i < 6; i++)
            diceSides[i].setIcon(GameUtils.resizeIcon(sides[i].getIcon(),GUISettings.getResizedValue(DICE_ICON_SIZE)));
    }

    public void resize(){
        this.setPreferredSize(new Dimension(GUISettings.getResizedValue(diceSize * 6 + 6), GUISettings.getResizedValue(diceSize)));

        diceLayout.setHgap(GUISettings.getResizedValue(1));

        for (int i = 0; i < 6; i++){
            diceSides[i].resize();
            if(diceSides[i].getIcon()!=null)
                diceSides[i].setIcon(GameUtils.resizeIcon((ImageIcon) diceSides[i].getIcon(),GUISettings.getResizedValue(DICE_ICON_SIZE)));
        }
    }

}
