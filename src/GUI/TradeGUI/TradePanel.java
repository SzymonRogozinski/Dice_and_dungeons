package GUI.TradeGUI;

import GUI.Components.GameLabel;
import GUI.GUISettings;
import GUI.Shared.Components.ChangePanel;
import Game.GameLevel;
import Game.GameManager;
import Game.PlayerInfo;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class TradePanel extends JPanel {

    private final FlowLayout layout;
    private final ChangePanel changeItemPagePanel;
    private final TradeItemPanel tradeItemPanel;
    private final GameLabel gold;

    public TradePanel(Border border) {
        this.setSize(GUISettings.SMALL_PANEL_SIZE, GUISettings.PANEL_SIZE);
        layout = new FlowLayout(FlowLayout.CENTER);
        layout.setVgap(15);
        this.setLayout(layout);
        this.setBackground(Color.BLACK);
        this.setBorder(border);

        tradeItemPanel = new TradeItemPanel();
        changeItemPagePanel = new ChangePanel("Next page", "Prev page", _ -> GameManager.getTradeModule().changePage(true), _ -> GameManager.getTradeModule().changePage(false));
        gold=new GameLabel("",SwingConstants.LEFT,
                GUISettings.SMALL_PANEL_SIZE - 20,GUISettings.PANEL_SIZE/20,Color.YELLOW);

        this.add(tradeItemPanel);
        this.add(gold);
        this.add(changeItemPagePanel);
    }

    public void changeToSell(){
        tradeItemPanel.setButtonText("Sell");
    }

    public void changeToBuy(){
        tradeItemPanel.setButtonText("Buy");
    }

    public void refresh(){
        tradeItemPanel.refresh();
        gold.setText(STR."Gold: \{PlayerInfo.getGold()}");
    }

    public void resize(){
        this.setSize(GUISettings.getResizedValue(GUISettings.SMALL_PANEL_SIZE), GUISettings.getResizedValue(GUISettings.PANEL_SIZE));

        layout.setVgap(GUISettings.getResizedValue(15));

        tradeItemPanel.resize();
        gold.resize();
        changeItemPagePanel.resize();
    }
}
