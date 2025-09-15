package GUI.EquipmentGUI.Components;

import Equipment.CharacterEquipment;
import Equipment.Items.Item;
import GUI.Components.GameButton;
import GUI.GUISettings;
import GUI.Shared.Components.ItemSlot;
import Game.GameManager;
import Game.PlayerInfo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SmallBackpackItemsPanel extends JPanel {

    private static final ImageIcon BAG_SLOT_ICON = new ImageIcon("Texture/EmptySlots/slot-bag.png");
    private final ArrayList<ItemSlot> itemSlots;
    private final ArrayList<GameButton> buttons;

    public SmallBackpackItemsPanel() {
        this.setPreferredSize(new Dimension(GUISettings.ITEM_ICON_SIZE * 7, GUISettings.ITEM_ICON_SIZE * 2));
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        layout.setVgap(0);
        layout.setHgap(0);
        this.setLayout(layout);
        this.setBackground(Color.BLACK);

        itemSlots = new ArrayList<>();
        buttons = new ArrayList<>();

        for (int i = 0; i < 14; i++) {
            if (i % 7 != 6) {
                ItemSlot slot = new ItemSlot(null, BAG_SLOT_ICON, i, CharacterEquipment.BAG_SLOT, true);
                itemSlots.add(slot);
                this.add(slot);
            } else {
                GameButton button = new GameButton(
                        i < 7 ? "Next" : "Prev",
                        GUISettings.ITEM_ICON_SIZE, GUISettings.ITEM_ICON_SIZE,
                        i < 7 ? _ -> GameManager.getEquipment().changeBackpackPage(true) : _ -> GameManager.getEquipment().changeBackpackPage(false),
                        Color.WHITE, Color.BLACK,
                        BorderFactory.createLineBorder(Color.WHITE, 1)
                );
                buttons.add(button);
                this.add(button);
            }
        }
    }

    public void refresh() {
        int i, j;
        j = 0;
        ArrayList<Item> items = PlayerInfo.getParty().getBackpack().getPageOfItemsForCharacter(GameManager.getEquipment().getCurrentCharacter());
        for (i = 0; i < 14; i++) {
            if (i % 7 == 6)
                continue;
            else if (j < items.size())
                itemSlots.get(j).setItem(items.get(j));
            else
                itemSlots.get(j).setItem(null);
            j++;
        }
    }

    public void resize(){
        this.setPreferredSize(new Dimension(GUISettings.getResizedValue(GUISettings.ITEM_ICON_SIZE * 7), GUISettings.getResizedValue(GUISettings.ITEM_ICON_SIZE * 2)));

        for(ItemSlot slot:itemSlots)
            slot.resize();

        for(GameButton button:buttons)
            button.resize();
    }
}
