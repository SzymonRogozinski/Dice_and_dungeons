package GUI.EquipmentGUI;

import GUI.GUISettings;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ItemManagementPanel extends JPanel {

    private BackpackPanel backpackPanel;
    private EquipmentPanel equipmentPanel;

    public ItemManagementPanel(Border border) {
        this.setSize(GUISettings.PANEL_SIZE,GUISettings.PANEL_SIZE);
        CardLayout layout=new CardLayout();
        this.setLayout(layout);
        this.setBorder(border);

        backpackPanel=new BackpackPanel();
        equipmentPanel=new EquipmentPanel();

        this.add(backpackPanel,"Backpack");
        this.add(equipmentPanel,"Equipment");

        layout.show(this,"Equipment");
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

            armor=new ItemSlotRow("Armor",4);
            items=new ItemSlotRow("Items",3);
            spells=new ItemSlotRow("spells",3);

            smallBackpackItemsPanel=new SmallBackpackItemsPanel();

            this.add(title);
            this.add(armor);
            this.add(items);
            this.add(spells);
            this.add(smallBackpackItemsPanel);
        }
    }

    private class ItemSlotRow extends JPanel{
        public ItemSlotRow(String name, int slotNumber) {
            this.setPreferredSize(new Dimension((int)(GUISettings.PANEL_SIZE*0.8),(int)(GUISettings.PANEL_SIZE/6)));
            FlowLayout layout=new FlowLayout(FlowLayout.LEFT);
            layout.setHgap(10);
            this.setLayout(layout);
            this.setBackground(Color.BLACK);

            JLabel title =new JLabel(name, SwingConstants.CENTER);
            title.setForeground(Color.WHITE);
            this.add(title);

            for(int i=0;i<slotNumber;i++){
                this.add(new ItemSlot());
            }
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
    }

    private class BackpackItemsPanel extends JPanel{

        public BackpackItemsPanel() {
            this.setPreferredSize(new Dimension(GUISettings.ITEM_ICON_SIZE*7,GUISettings.ITEM_ICON_SIZE*6));
            FlowLayout layout=new FlowLayout(FlowLayout.CENTER);
            layout.setVgap(0);
            layout.setHgap(0);
            this.setLayout(layout);
            this.setBackground(Color.BLACK);

            for(int i=0;i<42;i++){
                this.add(new ItemSlot());
            }
        }
    }

    private class SmallBackpackItemsPanel extends JPanel{

        public SmallBackpackItemsPanel() {
            this.setPreferredSize(new Dimension(GUISettings.ITEM_ICON_SIZE*7,GUISettings.ITEM_ICON_SIZE*2));
            FlowLayout layout=new FlowLayout(FlowLayout.CENTER);
            layout.setVgap(0);
            layout.setHgap(0);
            this.setLayout(layout);
            this.setBackground(Color.BLACK);

            for(int i=0;i<14;i++){
                if(i%7!=6)
                    this.add(new ItemSlot());
                else{
                    JButton button = new JButton(i<7?"Next":"Prev");
                    button.setForeground(Color.WHITE);
                    button.setPreferredSize(new Dimension(GUISettings.ITEM_ICON_SIZE,GUISettings.ITEM_ICON_SIZE));
                    button.setBackground(Color.BLACK);
                    button.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
                    this.add(button);
                }
            }
        }
    }
}
