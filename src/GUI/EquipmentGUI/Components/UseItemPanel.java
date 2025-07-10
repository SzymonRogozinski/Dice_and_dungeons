package GUI.EquipmentGUI.Components;

import Equipment.CharacterEquipment;
import GUI.Components.GameButton;
import GUI.EquipmentGUI.ItemSlot;
import GUI.GUISettings;
import Game.GameManager;

import javax.swing.*;
import java.awt.*;

public class UseItemPanel extends JPanel {

    private static final ImageIcon BAG_SLOT_ICON = new ImageIcon("Texture/EmptySlots/slot-bag.png");
    private final ItemSlot itemSlot;
    private final FlowLayout layout;
    private final GameButton useItem;

    public UseItemPanel() {
        this.setPreferredSize(new Dimension(GUISettings.SMALL_PANEL_SIZE - 20, GUISettings.PANEL_SIZE * 25/100));
        layout = new FlowLayout(FlowLayout.CENTER);
        layout.setVgap(10);
        this.setLayout(layout);
        this.setBackground(Color.BLACK);

        useItem = new GameButton(
                "Use item",
                GUISettings.SMALL_PANEL_SIZE - 50,
                (int) (GUISettings.PANEL_SIZE * 0.05),
                _ -> GameManager.getEquipment().useChosenItem()
        );

        itemSlot = new ItemSlot(null, BAG_SLOT_ICON, 0, CharacterEquipment.USE_SLOT);

        this.add(itemSlot);
        this.add(useItem);
    }

    public void refresh() {
        itemSlot.setItem(GameManager.getEquipment().getUseItem());
    }

    public void resize(){
        this.setPreferredSize(new Dimension(GUISettings.getResizedValue(GUISettings.SMALL_PANEL_SIZE - 20), GUISettings.getResizedValue(GUISettings.PANEL_SIZE * 25/100)));

        layout.setVgap(GUISettings.getResizedValue(10));

        useItem.resize();
        itemSlot.resize();
    }

}
