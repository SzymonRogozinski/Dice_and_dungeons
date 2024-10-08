package GUI.EquipmentGUI;

import Dice.DiceAction.DiceAction;
import Dice.DiceSide;
import Equipment.Items.*;
import GUI.GUISettings;
import Game.GameManager;
import Game.Tags;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ItemInfoPanel extends JPanel {

    private CardLayout layout;
    private DiceItemInfoPanel diceItemInfoPanel;
    private ArmorInfoPanel armorInfoPanel;
    private DiceLessItemInfoPanel diceLessItemInfoPanel;

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
        Item item = GameManager.getEquipment().getPointedItem();
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
        private final static double MANA_COST_TO_ALL_PROPORTION=0.3;
        private JLabel nameLabel, requirements, manaCost;
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

            requirements = new JLabel();
            requirements.setForeground(Color.WHITE);
            requirements.setPreferredSize(new Dimension((int)((GUISettings.PANEL_SIZE)*(1-MANA_COST_TO_ALL_PROPORTION)-20),GUISettings.SMALL_PANEL_SIZE/10));

            manaCost = new JLabel();
            manaCost.setForeground(Color.WHITE);
            manaCost.setPreferredSize(new Dimension((int)((GUISettings.PANEL_SIZE-10)*MANA_COST_TO_ALL_PROPORTION),GUISettings.SMALL_PANEL_SIZE/10));

            this.add(nameLabel);
            this.add(diceSidesPanel);
            this.add(requirements);
            this.add(manaCost);
        }

        public void refresh(){
            Item item = GameManager.getEquipment().getPointedItem();

            if(item instanceof ActionItem aItem) {
                nameLabel.setText(aItem.name);
                diceSidesPanel.setDiceSides(aItem.getAction().getDice().getSides());
                manaCost.setText("");
            } else if (item instanceof SpellItem sItem) {
                nameLabel.setText(sItem.name);
                diceSidesPanel.setDiceSides(sItem.getAction().getDice().getSides());
                manaCost.setText("Mana: "+sItem.getAction().getManaCost());
            }

            StringBuilder requirementsBuilder=new StringBuilder("Requirements:");

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

            requirements.setText(requirementsBuilder.toString());
        }
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
            ArmorItem item=(ArmorItem) GameManager.getEquipment().getPointedItem();

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
            UsableItem item = (UsableItem) GameManager.getEquipment().getPointedItem();
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
                this.add(diceSides[i]);
            }

        }

        void setDiceSides(DiceSide[] sides){
            for(int i=0;i<6;i++){
                diceSides[i].setIcon(sides[i].getIcon());
            }
        }
    }

}
