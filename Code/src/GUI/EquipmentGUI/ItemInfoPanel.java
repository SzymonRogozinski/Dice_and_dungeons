package GUI.EquipmentGUI;

import GUI.GUISettings;

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

        diceItemInfoPanel=new DiceItemInfoPanel();
        armorInfoPanel=new ArmorInfoPanel();
        diceLessItemInfoPanel=new DiceLessItemInfoPanel();

        this.add(diceItemInfoPanel,"Dice");
        this.add(armorInfoPanel,"Armor");
        this.add(diceLessItemInfoPanel,"Diceless");

        //Test
        layout.show(this,"Dice");
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

            //TEST DATA
            nameLabel.setText("Sword");

            this.add(nameLabel);
            this.add(diceSidesPanel);
        }
    }

    private class ArmorInfoPanel extends JPanel{

        private JLabel nameLabel, requirementsLabel, bonusLabel;

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

            //TEST DATA
            nameLabel.setText("Helmet");
            requirementsLabel.setText("Requirements: Warrior");
            bonusLabel.setText("Statistics: Strength 6, Endurance 4, Charisma 3");

            this.add(nameLabel);
            this.add(requirementsLabel);
            this.add(bonusLabel);
        }
    }

    private class DiceLessItemInfoPanel extends JPanel{

        private JLabel nameLabel, effectLabel;

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

            //TEST DATA
            nameLabel.setText("Rock");
            effectLabel.setText("Effects: Stun");

            this.add(nameLabel);
            this.add(effectLabel);
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

            for(JLabel side:diceSides){
                //TEST DATA
                side=new JLabel(resizeIcon("DiceIcons/D4.png",diceSize));
                side.setPreferredSize(new Dimension(diceSize,diceSize));
                side.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
                this.add(side);
            }

        }

        private static ImageIcon resizeIcon(String path,int size){
            return new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(size,size,java.awt.Image.SCALE_SMOOTH));
        }
    }

}
