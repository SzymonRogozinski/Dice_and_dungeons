package GUI.TradeGUI;

import GUI.Components.GameLabel;
import GUI.Shared.Components.ItemSlot;
import GUI.GUISettings;
import GUI.Shared.ItemInfoPanel;
import GUI.ViewPanel;
import Game.GameManager;
import Game.GameUtils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TradeView extends ViewPanel {

    private final NavigationPanel navigationPanel;
    private final TradePanel tradePanel;
    private final ItemInfoPanel itemInfoPanel;
    private final ItemsPanel itemsPanel;
    private final GameLabel dragableIcon;
    private ImageIcon dragIcon;

    public TradeView() {
        super(new ItemsPanel(getSharedBorder()),new NavigationPanel(getSharedBorder()),
                new ItemInfoPanel(getSharedBorder(),()-> GameManager.getTradeModule().getPointedItem()),
                new TradePanel(getSharedBorder()));

        //Get child component
        ArrayList<JPanel> panels = getChildPanels();
        itemsPanel = (ItemsPanel) panels.get(0);
        navigationPanel = (NavigationPanel) panels.get(1);
        itemInfoPanel = (ItemInfoPanel) panels.get(2);
        tradePanel = (TradePanel) panels.get(3);

        dragableIcon = new GameLabel(null,GUISettings.ITEM_ICON_SIZE,GUISettings.ITEM_ICON_SIZE);
        dragableIcon.setSize(dragableIcon.getPreferredSize());
        this.add(dragableIcon,JLayeredPane.DRAG_LAYER);
    }

    public void setState(int newState) {
        switch (newState) {
            case TradeGUIState.BUY -> {
                tradePanel.changeToBuy();
                navigationPanel.removeSellButton();
                itemsPanel.changeToBuy();
            }
            case TradeGUIState.SELL -> {
                tradePanel.changeToSell();
                navigationPanel.removeBuyButton();
                itemsPanel.changeToSell();
            }
        }
    }

    public void refresh(){
        if(GameManager.getTradeModule()==null)
            return;

        itemsPanel.refresh();
        itemInfoPanel.refresh();
        tradePanel.refresh();

        //Set pointed item
        ItemSlot it = GameManager.getTradeModule().getClickedSlot();
        if(it == null || it.getItem() == null){
            dragableIcon.setIcon(null);
            dragIcon=null;
            this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        } else if (it.getItem().getIcon()!=dragIcon) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
            dragIcon=it.getItem().getIcon();
            dragableIcon.setIcon(GameUtils.resizeIcon(dragIcon, GUISettings.getResizedValue(GUISettings.ITEM_ICON_SIZE)));
        }
        //Set position
        Point position = MouseInfo.getPointerInfo().getLocation();
        int resizedItemSize = GUISettings.getResizedValue(GUISettings.ITEM_ICON_SIZE);
        dragableIcon.setLocation((int) position.getX() - resizedItemSize / 2 - this.getLocationOnScreen().x,
                (int) position.getY() - resizedItemSize / 2 - this.getLocationOnScreen().y);
    }

    public void resize(){
        super.resize();

        itemsPanel.resize();
        navigationPanel.resize();
        itemInfoPanel.resize();
        tradePanel.resize();

        dragableIcon.resize();
        dragableIcon.setSize(dragableIcon.getPreferredSize());
        dragableIcon.setIcon(GameUtils.resizeIcon(dragIcon,GUISettings.getResizedValue(GUISettings.ITEM_ICON_SIZE)));
    }
}
