package GUI.TradeGUI;

import Equipment.CharacterEquipment;
import Equipment.Items.Item;
import GUI.Components.DimensionlessGameLabel;
import GUI.GUISettings;
import GUI.Shared.Components.ItemSlot;
import Game.GameManager;
import Game.PlayerInfo;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class ItemsPanel extends JPanel {

    private static final ImageIcon BAG_SLOT_ICON = new ImageIcon("Texture/EmptySlots/slot-bag.png");
    private final ItemSlot[] itemSlots;
    private final JPanel innerPanel;
    private final FlowLayout layout;
    private final DimensionlessGameLabel title;
    private boolean isBuying = true;

    public ItemsPanel(Border border) {
        this.setSize(GUISettings.PANEL_SIZE, GUISettings.PANEL_SIZE);
        this.setBorder(border);
        layout = new FlowLayout(FlowLayout.CENTER);
        layout.setVgap(25);
        this.setLayout(layout);
        this.setBackground(Color.BLACK);

        title = new DimensionlessGameLabel("Trader items", SwingConstants.CENTER, GUISettings.BIG_FONT, Color.WHITE);

        //Inner panel
        innerPanel = new JPanel();
        innerPanel.setPreferredSize(new Dimension(GUISettings.ITEM_ICON_SIZE * 7, GUISettings.ITEM_ICON_SIZE * 6));
        FlowLayout innerLayout = new FlowLayout(FlowLayout.CENTER);
        innerLayout.setVgap(0);
        innerLayout.setHgap(0);
        innerPanel.setLayout(innerLayout);
        innerPanel.setBackground(Color.BLACK);

        itemSlots = new ItemSlot[42];

        for (int i = 0; i < 42; i++) {
            itemSlots[i] = new ItemSlot(null, BAG_SLOT_ICON, i, CharacterEquipment.BAG_SLOT, false);
            innerPanel.add(itemSlots[i]);
        }

        this.add(title);
        this.add(innerPanel);
    }

    public void changeToBuy(){
        title.setText("Trader items");
        isBuying=true;
    }

    public void changeToSell(){
        title.setText("Your items");
        isBuying=false;
    }

    public void refresh(){
        int i = 0;
        ArrayList<Item> items = isBuying ? GameManager.getTradeModule().getTrader().getPageOfItems() : PlayerInfo.getParty().getBackpack().getPageOfItems();
        for (; i < items.size() && i < 42; i++)
            itemSlots[i].setItem(items.get(i));
        for (; i < 42; i++)
            itemSlots[i].setItem(null);
    }

    public void resize(){
        this.setSize(GUISettings.getResizedValue(GUISettings.PANEL_SIZE), GUISettings.getResizedValue(GUISettings.PANEL_SIZE));
        title.resize();
        innerPanel.setPreferredSize(new Dimension(GUISettings.getResizedValue(GUISettings.ITEM_ICON_SIZE * 7), GUISettings.getResizedValue(GUISettings.ITEM_ICON_SIZE * 6)));

        for (int i = 0; i < 42; i++)
            itemSlots[i].resize();
    }
}
