package GUI.TradeGUI;

import Equipment.CharacterEquipment;
import Equipment.Items.Item;
import Equipment.Items.UsableItem;
import GUI.Components.GameButton;
import GUI.Components.GameTextArea;
import GUI.GUISettings;
import GUI.Shared.Components.ItemSlot;
import Game.GameManager;
import Game.PlayerInfo;

import javax.swing.*;
import java.awt.*;

public class TradeItemPanel extends JPanel {

    private static final ImageIcon BAG_SLOT_ICON = new ImageIcon("Texture/EmptySlots/slot-bag.png");
    private final ItemSlot itemSlot;
    private final FlowLayout layout;
    private final GameButton tradeItem, tradeAllItem;
    private final GameTextArea canBuy, canBuyAll;

    public TradeItemPanel() {
        this.setPreferredSize(new Dimension(GUISettings.SMALL_PANEL_SIZE - 20, GUISettings.PANEL_SIZE/2));
        layout = new FlowLayout(FlowLayout.CENTER);
        layout.setVgap(10);
        this.setLayout(layout);
        this.setBackground(Color.BLACK);

        itemSlot = new ItemSlot(null, BAG_SLOT_ICON, 0, CharacterEquipment.TRADE_SLOT, false);

        tradeItem = new GameButton(
                "Buy",
                GUISettings.SMALL_PANEL_SIZE - 50,
                GUISettings.PANEL_SIZE/20,
                _ -> GameManager.getTradeModule().tradeItem()
        );

        canBuy = new GameTextArea(GUISettings.SMALL_PANEL_SIZE-20, GUISettings.SMALL_PANEL_SIZE / 8);
        canBuy.setText("You lack gold!");
        canBuy.setForeground(Color.RED);

        tradeAllItem = new GameButton(
                "Buy all",
                GUISettings.SMALL_PANEL_SIZE - 50,
                GUISettings.PANEL_SIZE/20,
                _ -> GameManager.getTradeModule().tradeAllItem()
        );

        canBuyAll = new GameTextArea(GUISettings.SMALL_PANEL_SIZE-20, GUISettings.SMALL_PANEL_SIZE / 8);
        canBuyAll.setText("You lack gold!");
        canBuyAll.setForeground(Color.RED);

        this.add(itemSlot);
        this.add(tradeItem);
        this.add(canBuy);
        this.add(tradeAllItem);
        this.add(canBuyAll);
    }

    public void setButtonText(String s){
        tradeItem.setText(s);
        tradeAllItem.setText(STR."\{s} all");
    }

    public void refresh() {
        Item item = GameManager.getTradeModule().getTradeItem();
        itemSlot.setItem(item);

        tradeItem.setEnabled(!(item!=null && GameManager.getTradeModule().getCurrentState()==TradeGUIState.BUY
                && item.getCost() > PlayerInfo.getGold()));
        canBuy.setVisible(item!=null && GameManager.getTradeModule().getCurrentState()==TradeGUIState.BUY
                && item.getCost() > PlayerInfo.getGold());
        tradeAllItem.setVisible(item instanceof UsableItem);
        tradeAllItem.setEnabled(!(item instanceof UsableItem usableItem
                && GameManager.getTradeModule().getCurrentState()==TradeGUIState.BUY
                && usableItem.getCost() * usableItem.getNumberOfItems() > PlayerInfo.getGold()));
        canBuyAll.setVisible(item instanceof UsableItem usableItem
                && GameManager.getTradeModule().getCurrentState()==TradeGUIState.BUY
                && usableItem.getCost() * usableItem.getNumberOfItems() > PlayerInfo.getGold());
    }

    public void resize(){
        this.setPreferredSize(new Dimension(GUISettings.getResizedValue(GUISettings.SMALL_PANEL_SIZE - 20), GUISettings.getResizedValue(GUISettings.PANEL_SIZE/2)));

        layout.setVgap(GUISettings.getResizedValue(10));

        tradeItem.resize();
        itemSlot.resize();
        canBuy.resize();
        tradeAllItem.resize();
        canBuyAll.resize();
    }
}
