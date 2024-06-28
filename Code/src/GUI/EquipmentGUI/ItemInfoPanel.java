package GUI.EquipmentGUI;

import Dice.DiceAction.DiceAction;
import Dice.DiceSide;
import Equipment.EquipmentModule;
import Equipment.Items.*;
import GUI.GUISettings;
import Game.Tags;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ItemInfoPanel extends JPanel {

    private CardLayout layout;
    private DiceItemInfoPanel diceItemInfoPanel;
    private ArmorInfoPanel armorInfoPanel;
    private DiceLessItemInfoPanel diceLessItemInfoPanel;
    private EquipmentModule equipment;

    public ItemInfoPanel(Border border){
        this.setSize(GUISettings.PANEL_SIZE,GUISettings.SMALL_PANEL_SIZE);
        this.layout=new CardLayout();
        this.setLayout(layout);
        this.setBorder(border);
        this.setBackground(Color.BLACK);

        JPanel emptyPanel=new JPanel();
        emptyPanel.setBackground(Color.BLACK);

        diceItemInfoPanel=new DiceItemInfoPanel();
        armorInfoPanel=new ArmorInfoPanel();
        diceLessItemInfoPanel=new DiceLessItemInfoPanel();

        this.add(emptyPanel,"Empty");
        this.add(diceItemInfoPanel,"Dice");
        this.add(armorInfoPanel,"Armor");
        this.add(diceLessItemInfoPanel,"Diceless");
    }

    public void refresh(){
        Item item = equipment.getPointedItem();
        if(item==null){
            layout.show(this,"Empty");
        }else if(item instanceof ArmorItem){
            layout.show(this,"Armor");
            armorInfoPanel.refresh();
        }else if(item instanceof ActionItem || item instanceof SpellItem){
            layout.show(this,"Dice");
            diceItemInfoPanel.refresh();
        }else if (item instanceof UsableItem){
            layout.show(this,"Diceless");
            diceLessItemInfoPanel.refresh();
        }
    }

    private class DiceItemInfoPanel extends JPanel{

        private JLabel nameLabel;
        private DiceSidesPanel diceSidesPanel;

        public DiceItemInfoPanel() {
            this.setSize(GUISettings.PANEL_SIZE,GUISettings.SMALL_PANEL_SIZE);
            this.setBackground(Color.BLACK);

            nameLabel = new JLabel("", SwingConstants.CENTER);
            nameLabel.setFont(GUISettings.BIG_FONT);
            nameLabel.setForeground(Color.WHITE);
            nameLabel.setPreferredSize(new Dimension(GUISettings.PANEL_SIZE-10,GUISettings.SMALL_PANEL_SIZE/5));
            nameLabel.setVerticalAlignment(SwingConstants.CENTER);

            diceSidesPanel=new DiceSidesPanel();

            this.add(nameLabel);
            this.add(diceSidesPanel);
        }

        public void refresh(){
            //TODO not always without dice
            if(equipment.getPointedItem() instanceof ActionItem aItem) {
                nameLabel.setText(aItem.name);
                diceSidesPanel.setDiceSides(aItem.getAction().getDice().getSides());
            } else if (equipment.getPointedItem() instanceof SpellItem sItem) {
                nameLabel.setText(sItem.name);
                diceSidesPanel.setDiceSides(sItem.getAction().getDice().getSides());
            }
        }
    }

    public void setEquipment(EquipmentModule equipment) {
        this.equipment = equipment;
    }

    private class ArmorInfoPanel extends JPanel{

        private JLabel nameLabel, requirementsLabel, bonusLabel;
        private final static String[] statsName=new String[]{"Strength","Endurance","Intelligence","Charisma","Cunning","Luck"};

        public ArmorInfoPanel() {
            this.setSize(GUISettings.PANEL_SIZE,GUISettings.SMALL_PANEL_SIZE);
            this.setBackground(Color.BLACK);

            nameLabel = new JLabel("", SwingConstants.CENTER);
            nameLabel.setFont(GUISettings.BIG_FONT);
            nameLabel.setForeground(Color.WHITE);
            nameLabel.setPreferredSize(new Dimension(GUISettings.PANEL_SIZE-10,GUISettings.SMALL_PANEL_SIZE/5));
            nameLabel.setVerticalAlignment(SwingConstants.CENTER);

            requirementsLabel = new JLabel("", SwingConstants.LEFT);
            requirementsLabel.setForeground(Color.WHITE);
            requirementsLabel.setPreferredSize(new Dimension(GUISettings.PANEL_SIZE-10,GUISettings.SMALL_PANEL_SIZE/7));

            bonusLabel = new JLabel("", SwingConstants.LEFT);
            bonusLabel.setForeground(Color.WHITE);
            bonusLabel.setPreferredSize(new Dimension(GUISettings.PANEL_SIZE-10,GUISettings.SMALL_PANEL_SIZE/7));

            this.add(nameLabel);
            this.add(requirementsLabel);
            this.add(bonusLabel);
        }

        public void refresh(){
            ArmorItem item=(ArmorItem) equipment.getPointedItem();

            StringBuilder statsBuilder=new StringBuilder("Statistics:");
            StringBuilder requirementsBuilder=new StringBuilder("Requirements:");

            int[] stats=item.getStats();

            for(int i=0;i<stats.length;i++) {
                if (stats[i] != 0){
                    statsBuilder.append(" ").append(statsName[i]).append(" ").append(stats[i]).append(",");
                }
            }
            if(statsBuilder.toString().equals("Statistics:")){
                statsBuilder.append(" None");
            }else{
                statsBuilder.deleteCharAt(statsBuilder.toString().length()-1);
            }

            Tags[] tags=item.tags;
            for(Tags tag:tags) {
                String s=tag.name().toLowerCase();
                s=s.substring(0,1).toUpperCase()+s.substring(1);
                requirementsBuilder.append(" ").append(s).append(",");
            }
            if(requirementsBuilder.toString().equals("Requirements:")){
                requirementsBuilder.append(" None");
            }else{
                requirementsBuilder.deleteCharAt(requirementsBuilder.toString().length()-1);
            }

            nameLabel.setText(item.name);
            requirementsLabel.setText(statsBuilder.toString());
            bonusLabel.setText(requirementsBuilder.toString());
        }
    }

    private class DiceLessItemInfoPanel extends JPanel{

        private JLabel nameLabel, effectLabel,quantityLabel;

        public DiceLessItemInfoPanel() {
            this.setSize(GUISettings.PANEL_SIZE,GUISettings.SMALL_PANEL_SIZE);
            this.setBackground(Color.BLACK);
            this.setLayout(new FlowLayout(FlowLayout.CENTER));

            nameLabel = new JLabel("", SwingConstants.CENTER);
            nameLabel.setFont(GUISettings.BIG_FONT);
            nameLabel.setPreferredSize(new Dimension(GUISettings.PANEL_SIZE-10,GUISettings.SMALL_PANEL_SIZE/5));
            nameLabel.setForeground(Color.WHITE);

            effectLabel = new JLabel("", SwingConstants.LEFT);
            effectLabel.setPreferredSize(new Dimension(GUISettings.PANEL_SIZE-20,GUISettings.SMALL_PANEL_SIZE/7));
            effectLabel.setForeground(Color.WHITE);

            quantityLabel = new JLabel("",SwingConstants.LEFT);
            quantityLabel.setPreferredSize(new Dimension(GUISettings.PANEL_SIZE-20,GUISettings.SMALL_PANEL_SIZE/7));
            quantityLabel.setForeground(Color.WHITE);

            this.add(nameLabel);
            this.add(effectLabel);
            this.add(quantityLabel);
        }

        void refresh(){
            //TODO not always usable item
            UsableItem item = (UsableItem) equipment.getPointedItem();
            StringBuilder builder=new StringBuilder("Effects:");

            var x = item.getAction().getActionFactories();

            for(DiceAction action: x) {
                builder.append(" ").append(action.getIdentification()).append(" ").append(action.getValue()).append(",");
            }
            if(builder.toString().equals("Effects:")){
                builder.append(" None");
            }else{
                builder.deleteCharAt(builder.toString().length()-1);
            }

            nameLabel.setText(item.name);
            effectLabel.setText(builder.toString());
            quantityLabel.setText("Uses: "+item.getNumberOfItems());
        }
    }

    private class DiceSidesPanel extends JPanel{

        private JLabel[] diceSides;

        public DiceSidesPanel() {
            this.setSize(GUISettings.PANEL_SIZE-10,GUISettings.SMALL_PANEL_SIZE/2);
            this.setBackground(Color.BLACK);
            FlowLayout diceLayout = new FlowLayout(FlowLayout.CENTER);
            diceLayout.setHgap(1);
            this.setLayout(diceLayout);
            int diceSize= Math.min((GUISettings.PANEL_SIZE-10)/6,GUISettings.SMALL_PANEL_SIZE/2);
            this.diceSides = new JLabel[6];

            for(int i=0;i<6;i++){
                diceSides[i]=new JLabel();
                diceSides[i].setPreferredSize(new Dimension(diceSize,diceSize));
                diceSides[i].setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
                this.add(diceSides[i]);
            }

        }

        void setDiceSides(DiceSide[] sides){
            for(int i=0;i<6;i++){
                diceSides[i].setIcon(sides[i].getIcon());
            }
        }

        private static ImageIcon resizeIcon(String path,int size){
            return new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(size,size,java.awt.Image.SCALE_SMOOTH));
        }
    }

}
