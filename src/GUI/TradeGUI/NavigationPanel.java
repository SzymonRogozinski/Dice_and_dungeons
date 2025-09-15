package GUI.TradeGUI;

import GUI.Components.GameButton;
import GUI.GUISettings;
import Game.GameActionQueue;
import Game.GameManager;
import Game.GameStates;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class NavigationPanel extends JPanel {

    private final FlowLayout layout;
    private final GameButton buyButtom, sellButtom, backButton;

    public NavigationPanel(Border border) {
        //Set display
        this.setSize(GUISettings.SMALL_PANEL_SIZE, GUISettings.SMALL_PANEL_SIZE);
        layout = new FlowLayout(FlowLayout.CENTER);
        this.setLayout(layout);
        layout.setVgap(GUISettings.SMALL_PANEL_SIZE /5);
        this.setBackground(Color.BLACK);
        this.setBorder(border);

        buyButtom = new GameButton("Buy",
                GUISettings.SMALL_PANEL_SIZE * 8 / 10,
                GUISettings.SMALL_PANEL_SIZE * 2 / 10,
                _ -> GameActionQueue.action(()-> GameManager.getTradeModule().changeViewToBuy())
        );

        sellButtom = new GameButton("Sell",
                GUISettings.SMALL_PANEL_SIZE * 8 / 10,
                GUISettings.SMALL_PANEL_SIZE * 2 / 10,
                _ -> GameActionQueue.action(()-> GameManager.getTradeModule().changeViewToSell())
        );

        backButton = new GameButton("Close",
                GUISettings.SMALL_PANEL_SIZE * 8 / 10,
                GUISettings.SMALL_PANEL_SIZE * 2 / 10,
                _ -> GameActionQueue.action(()->GameManager.changeState(GameStates.DIALOG))
        );

        this.add(sellButtom);
        this.add(backButton);
    }

    public void resize(){
        this.setSize(GUISettings.getResizedValue(GUISettings.SMALL_PANEL_SIZE), GUISettings.getResizedValue(GUISettings.SMALL_PANEL_SIZE));

        layout.setVgap(GUISettings.getResizedValue(GUISettings.SMALL_PANEL_SIZE /5));
        buyButtom.resize();
        sellButtom.resize();
        backButton.resize();
    }

    public void removeBuyButton(){
        this.removeAll();

        this.add(buyButtom);
        this.add(backButton);
    }

    public void removeSellButton(){
        this.removeAll();

        this.add(sellButtom);
        this.add(backButton);
    }
}
