package GUI.Shared.Components;

import Equipment.Items.Item;
import Equipment.Items.ItemQuality;
import GUI.Components.GameLabel;
import GUI.GUISettings;
import Game.GameActionQueue;
import Game.GameManager;
import Game.GameUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ItemSlot extends JPanel {

    private static final int ITEM_ICON_REAL_SIZE = 40;
    private final GameLabel label;
    private final ImageIcon emptySlotIcon;
    private final int slotNumber, slotType;
    private Item item;

    public ItemSlot(Item item, ImageIcon emptySlotIcon, int slotNumber, int slotType, boolean equipmentSlot) {
        this.item = item;
        this.emptySlotIcon = emptySlotIcon;
        this.slotNumber = slotNumber;
        this.slotType = slotType;
        MouseListener mouseListener = equipmentSlot ? new EquipmentItemSlotMouseListener(this) : new TradeItemSlotMouseListener(this);

        this.setPreferredSize(new Dimension(GUISettings.ITEM_ICON_SIZE, GUISettings.ITEM_ICON_SIZE));
        this.setBackground(Color.BLACK);
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        layout.setHgap(0);
        layout.setVgap(0);
        this.setLayout(layout);

        label = new GameLabel(
                item == null ? emptySlotIcon : item.getIcon(), SwingConstants.CENTER,
                GUISettings.ITEM_ICON_SIZE, GUISettings.ITEM_ICON_SIZE,
                Color.WHITE,
                SwingConstants.CENTER, SwingConstants.CENTER
        );

        this.add(label);
        this.addMouseListener(mouseListener);
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public int getSlotType() {
        return slotType;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
        label.setIcon(GameUtils.resizeIcon(item == null ? emptySlotIcon : item.getIcon(),
                GUISettings.getResizedValue(ITEM_ICON_REAL_SIZE)));
        if (item == null)
            this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        else if (item.getQuality() == ItemQuality.COMMON)
            this.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        else if (item.getQuality() == ItemQuality.RARE)
            this.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
        else if (item.getQuality() == ItemQuality.LEGENDARY)
            this.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 1));
    }

    public void resize(){
        this.setPreferredSize(new Dimension(GUISettings.getResizedValue(GUISettings.ITEM_ICON_SIZE), GUISettings.getResizedValue(GUISettings.ITEM_ICON_SIZE)));
        label.setIcon(GameUtils.resizeIcon(item == null ? emptySlotIcon : item.getIcon(),
                GUISettings.getResizedValue(ITEM_ICON_REAL_SIZE)));
        label.resize();
    }

}
