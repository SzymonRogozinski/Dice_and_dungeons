package GUI.EquipmentGUI;

import Equipment.CharacterEquipment;
import Equipment.Items.Item;
import GUI.GUISettings;
import Game.Game;
import Game.PlayerInfo;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class ItemManagementPanel extends JPanel {

    private static ImageIcon HELM_SLOT_ICON =new ImageIcon("Texture/EmptySlots/slot-helm.png");
    private static ImageIcon CHEST_SLOT_ICON =new ImageIcon("Texture/EmptySlots/slot-chest.png");
    private static ImageIcon GAUNTLET_SLOT_ICON =new ImageIcon("Texture/EmptySlots/slot-gauntlet.png");
    private static ImageIcon LEG_SLOT_ICON =new ImageIcon("Texture/EmptySlots/slot-leg.png");
    private static ImageIcon SCROLL_SLOT_ICON =new ImageIcon("Texture/EmptySlots/slot-scroll.png");
    private static ImageIcon DICE_SLOT_ICON =new ImageIcon("Texture/EmptySlots/slot-dice.png");
    private static ImageIcon BAG_SLOT_ICON =new ImageIcon("Texture/EmptySlots/slot-bag.png");

    private BackpackPanel backpackPanel;
    private EquipmentPanel equipmentPanel;
    private CardLayout layout;

    public ItemManagementPanel(Border border) {
        this.setSize(GUISettings.PANEL_SIZE,GUISettings.PANEL_SIZE);
        layout=new CardLayout();
        this.setLayout(layout);
        this.setBorder(border);

        backpackPanel=new BackpackPanel();
        equipmentPanel=new EquipmentPanel();

        this.add(backpackPanel,"Backpack");
        this.add(equipmentPanel,"Equipment");

        layout.show(this,"Equipment");
    }

    public void changeCard(String name){
        layout.show(this,name);
    }

    public void refresh(){
        if(Game.getEquipment()==null)
            return;
        backpackPanel.refresh();
        equipmentPanel.refresh();
    }

    private class EquipmentPanel extends JPanel{

        private JLabel title;
        private ItemSlotRow armor,items,spells;
        private SmallBackpackItemsPanel smallBackpackItemsPanel;

        public EquipmentPanel() {
            this.setSize(GUISettings.PANEL_SIZE,GUISettings.PANEL_SIZE);
            FlowLayout layout=new FlowLayout(FlowLayout.CENTER);
            layout.setVgap(5);
            this.setLayout(layout);
            this.setBackground(Color.BLACK);

            title = new JLabel("Equipment", SwingConstants.CENTER);
            title.setPreferredSize(new Dimension((int)(GUISettings.PANEL_SIZE*0.8),(int)(GUISettings.PANEL_SIZE/7.5)));
            title.setFont(GUISettings.BIG_FONT);
            title.setForeground(Color.WHITE);

            armor=new ItemSlotRow("Armor",4,new ImageIcon[]{HELM_SLOT_ICON,GAUNTLET_SLOT_ICON,CHEST_SLOT_ICON,LEG_SLOT_ICON},CharacterEquipment.ARMOR_SLOT);
            items=new ItemSlotRow("Items",3,new ImageIcon[]{DICE_SLOT_ICON,DICE_SLOT_ICON,DICE_SLOT_ICON},CharacterEquipment.ACTION_SLOT);
            spells=new ItemSlotRow("spells",3,new ImageIcon[]{SCROLL_SLOT_ICON,SCROLL_SLOT_ICON,SCROLL_SLOT_ICON},CharacterEquipment.SPELL_SLOT);

            smallBackpackItemsPanel=new SmallBackpackItemsPanel();

            this.add(title);
            this.add(armor);
            this.add(items);
            this.add(spells);
            this.add(smallBackpackItemsPanel);
        }

        void refresh(){
            armor.refresh();
            items.refresh();
            spells.refresh();
            smallBackpackItemsPanel.refresh();
        }
    }

    private class ItemSlotRow extends JPanel{

        private ItemSlot[] itemSlots;
        private final int slotType;

        public ItemSlotRow(String name, int slotNumber,ImageIcon[] emptySlotIcons,int slotType) {
            this.setPreferredSize(new Dimension((int)(GUISettings.PANEL_SIZE*0.8),(int)(GUISettings.PANEL_SIZE/6)));
            FlowLayout layout=new FlowLayout(FlowLayout.LEFT);
            layout.setHgap(10);
            layout.setVgap(0);
            this.setLayout(layout);
            this.setBackground(Color.BLACK);

            JLabel title =new JLabel(name, SwingConstants.CENTER);
            title.setForeground(Color.WHITE);
            this.add(title);

            itemSlots=new ItemSlot[slotNumber];
            this.slotType=slotType;

            for(int i=0;i<slotNumber;i++){
                itemSlots[i]=new ItemSlot(null,emptySlotIcons[i],i,slotType);
                this.add(itemSlots[i]);
            }
        }

        void refresh(){
            ArrayList<Item> items;
            if(slotType == CharacterEquipment.ACTION_SLOT){
                items=castArray(Game.getEquipment().getCurrentCharacter().getEquipment().getActionItems());
            }else if(slotType == CharacterEquipment.SPELL_SLOT){
                items=castArray(Game.getEquipment().getCurrentCharacter().getEquipment().getSpellItems());
            }else{
                items=castArray(Game.getEquipment().getCurrentCharacter().getEquipment().getArmorItems());
            }
            for(int i=0;i<itemSlots.length;i++){
                itemSlots[i].setItem(items.get(i));
            }
        }

        private static <T> ArrayList<Item> castArray(T[] array) {
            ArrayList<Item> target=new ArrayList<>();
            for (T T : array) {
                target.add((Item) T);
            }
            return target;
        }
    }


    private class BackpackPanel extends JPanel{

        private JLabel title;
        private BackpackItemsPanel backpackItemsPanel;

        public BackpackPanel() {
            this.setSize(GUISettings.PANEL_SIZE,GUISettings.PANEL_SIZE);
            FlowLayout layout=new FlowLayout(FlowLayout.CENTER);
            layout.setVgap(25);
            this.setLayout(layout);
            this.setBackground(Color.BLACK);

            title = new JLabel("Backpack", SwingConstants.CENTER);
            title.setFont(GUISettings.BIG_FONT);
            title.setForeground(Color.WHITE);

            backpackItemsPanel=new BackpackItemsPanel();

            this.add(title);
            this.add(backpackItemsPanel);
        }

        void refresh(){
            backpackItemsPanel.refresh();
        }
    }

    private class BackpackItemsPanel extends JPanel{

        private ItemSlot[] itemSlots;

        public BackpackItemsPanel() {
            this.setPreferredSize(new Dimension(GUISettings.ITEM_ICON_SIZE*7,GUISettings.ITEM_ICON_SIZE*6));
            FlowLayout layout=new FlowLayout(FlowLayout.CENTER);
            layout.setVgap(0);
            layout.setHgap(0);
            this.setLayout(layout);
            this.setBackground(Color.BLACK);

            itemSlots=new ItemSlot[42];

            for(int i=0;i<42;i++){
                itemSlots[i]=new ItemSlot(null, BAG_SLOT_ICON,i,CharacterEquipment.BAG_SLOT);
                this.add(itemSlots[i]);
            }
        }

        public void refresh(){
            int i=0;
            ArrayList<Item> items= PlayerInfo.getParty().getBackpack().getPageOfItems();
            for(;i<items.size() && i<42;i++){
                itemSlots[i].setItem(items.get(i));
            }
            for(;i<42;i++){
                itemSlots[i].setItem(null);
            }
        }
    }

    private class SmallBackpackItemsPanel extends JPanel{

        private ArrayList<ItemSlot> itemSlots;

        public SmallBackpackItemsPanel() {
            this.setPreferredSize(new Dimension(GUISettings.ITEM_ICON_SIZE*7,GUISettings.ITEM_ICON_SIZE*2));
            FlowLayout layout=new FlowLayout(FlowLayout.CENTER);
            layout.setVgap(0);
            layout.setHgap(0);
            this.setLayout(layout);
            this.setBackground(Color.BLACK);

            itemSlots=new ArrayList<>();

            for(int i=0;i<14;i++){
                if(i%7!=6) {
                    ItemSlot slot=new ItemSlot(null, BAG_SLOT_ICON,i,CharacterEquipment.BAG_SLOT);
                    itemSlots.add(slot);
                    this.add(slot);
                }else{
                    JButton button = new JButton(i<7?"Next":"Prev");
                    button.addActionListener(i<7?e-> Game.getEquipment().changeBackpackPage(true): e-> Game.getEquipment().changeBackpackPage(false));
                    button.setForeground(Color.WHITE);
                    button.setPreferredSize(new Dimension(GUISettings.ITEM_ICON_SIZE,GUISettings.ITEM_ICON_SIZE));
                    button.setBackground(Color.BLACK);
                    button.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
                    this.add(button);
                }
            }
        }

        public void refresh(){
            int i,j;
            j=0;
            ArrayList<Item> items= PlayerInfo.getParty().getBackpack().getPageOfItemsForCharacter(Game.getEquipment().getCurrentCharacter());
            for(i=0;i<14;i++){
                if(i%7==6)
                    continue;
                else if(j<items.size()){
                    itemSlots.get(j).setItem(items.get(j));
                }else{
                    itemSlots.get(j).setItem(null);
                }
                j++;
            }
        }
    }
}
