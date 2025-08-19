package GUI.DialogGUI;

import GUI.Components.GameButton;
import GUI.GUISettings;
import Game.GameActionQueue;
import Game.GameManager;
import Game.GameStates;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class NavigationPanel extends JPanel {

    private final GameButton trade, goBack;
    private final FlowLayout layout;

    public NavigationPanel(Border border) {
        this.setSize(GUISettings.SMALL_PANEL_SIZE, GUISettings.SMALL_PANEL_SIZE);
        this.setBackground(Color.BLACK);
        this.setBorder(border);

        layout=new FlowLayout(FlowLayout.CENTER);
        layout.setVgap(GUISettings.SMALL_PANEL_SIZE/5);
        this.setLayout(layout);

        trade = new GameButton("Trade",GUISettings.SMALL_PANEL_SIZE*8/10,GUISettings.SMALL_PANEL_SIZE/5);   //TODO
        goBack = new GameButton("End",GUISettings.SMALL_PANEL_SIZE*8/10,GUISettings.SMALL_PANEL_SIZE/5,
                _ -> GameActionQueue.action(()->GameManager.changeState(GameStates.WALKING)));

        this.add(trade);
        this.add(goBack);
    }

    public void resize(){
        this.setSize(GUISettings.getResizedValue(GUISettings.SMALL_PANEL_SIZE), GUISettings.getResizedValue(GUISettings.SMALL_PANEL_SIZE));

        layout.setVgap(GUISettings.getResizedValue(GUISettings.SMALL_PANEL_SIZE/5));

        trade.resize();
        goBack.resize();
    }
}
