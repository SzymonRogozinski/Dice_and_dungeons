package GUI.EquipmentGUI;

import Character.PlayerCharacter;
import Equipment.CharacterEquipment;
import GUI.GUISettings;
import Game.GameCollection;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class CharactersInfoPanel extends JPanel {

    private static ImageIcon BAG_SLOT_ICON =new ImageIcon("Texture/EmptySlots/slot-bag.png");

    private JLabel headline;
    private CharacterInfoPanel charactersInfoPanel;
    private PartyInfoPanel partyInfoPanel;
    private ChangePanel changeCharacterPanel;
    private UseItemPanel useItemPanel;
    private ChangePanel changeBackpackPagePanel;

    public CharactersInfoPanel(Border border){
        this.setSize(GUISettings.SMALL_PANEL_SIZE,GUISettings.PANEL_SIZE);
        FlowLayout layout=new FlowLayout(FlowLayout.CENTER);
        layout.setVgap(15);
        this.setLayout(layout);
        this.setBackground(Color.BLACK);
        this.setBorder(border);

        headline = new JLabel("Info", SwingConstants.CENTER);
        headline.setFont(GUISettings.BIG_FONT);
        headline.setForeground(Color.WHITE);

        charactersInfoPanel=new CharacterInfoPanel();
        partyInfoPanel=new PartyInfoPanel();
        changeCharacterPanel=new ChangePanel("Next character","Previous character",e->GameCollection.getEquipment().changeCharacter(true),e->GameCollection.getEquipment().changeCharacter(false));
        useItemPanel=new UseItemPanel();
        changeBackpackPagePanel=new ChangePanel("Next page","Previous page",e->GameCollection.getEquipment().changeBackpackPage(true),e->GameCollection.getEquipment().changeBackpackPage(false));

        setEquipmentVisibility(true);

        this.add(headline);
        this.add(charactersInfoPanel);
        this.add(partyInfoPanel);
        this.add(changeCharacterPanel);
        this.add(useItemPanel);
        this.add(changeBackpackPagePanel);
    }

    public void setEquipmentVisibility(boolean isVisible){
        charactersInfoPanel.setVisible(isVisible);
        changeCharacterPanel.setVisible(isVisible);

        useItemPanel.setVisible(!isVisible);
        changeBackpackPagePanel.setVisible(!isVisible);
    }

    public void refresh(){
        partyInfoPanel.refresh();
        charactersInfoPanel.refresh();
        useItemPanel.refresh();
    }

    public class CharacterInfoPanel extends JPanel{

        //name,strength,endurance,intelligence,charisma, cunning,luck
        private JLabel[] statisticLabels;

        public CharacterInfoPanel() {
            this.setPreferredSize(new Dimension(GUISettings.SMALL_PANEL_SIZE-20,(int)(GUISettings.PANEL_SIZE*0.25)));
            FlowLayout layout=new FlowLayout(FlowLayout.CENTER);
            layout.setVgap(1);
            this.setLayout(layout);
            this.setBackground(Color.BLACK);

            statisticLabels=new JLabel[7];
            for(int i=0;i<7;i++){
                statisticLabels[i]=new JLabel("",SwingConstants.LEFT);
                statisticLabels[i].setForeground(Color.WHITE);
                statisticLabels[i].setPreferredSize(new Dimension(GUISettings.SMALL_PANEL_SIZE-20,GUISettings.SMALL_PANEL_SIZE/10));
            }
            //TEST DATA
            statisticLabels[0].setText("");
            statisticLabels[1].setText("");
            statisticLabels[2].setText("");
            statisticLabels[3].setText("");
            statisticLabels[4].setText("");
            statisticLabels[5].setText("");
            statisticLabels[6].setText("");

            for(int i=0;i<7;i++){
                this.add(statisticLabels[i]);
            }
        }

        public void refresh(){
            PlayerCharacter player= GameCollection.getEquipment().getCurrentCharacter();
            statisticLabels[0].setText(player.getName());
            statisticLabels[1].setText("Strength: "+player.getStrength());
            statisticLabels[2].setText("Endurance: "+player.getEndurance());
            statisticLabels[3].setText("Intelligence: "+player.getIntelligence());
            statisticLabels[4].setText("Charisma: "+player.getCharisma());
            statisticLabels[5].setText("Cunning: "+player.getCunning());
            statisticLabels[6].setText("Luck: "+player.getLuck());
        }
    }

    public class PartyInfoPanel extends JPanel{

        private JLabel[] statisticLabels;

        public PartyInfoPanel() {
            this.setPreferredSize(new Dimension(GUISettings.SMALL_PANEL_SIZE-20,GUISettings.PANEL_SIZE/4));
            FlowLayout layout=new FlowLayout(FlowLayout.CENTER);
            layout.setVgap(1);
            this.setLayout(layout);
            this.setBackground(Color.BLACK);

            statisticLabels=new JLabel[3];
            for(int i=0;i<3;i++){
                statisticLabels[i]=new JLabel("",SwingConstants.LEFT);
                statisticLabels[i].setForeground(Color.WHITE);
                statisticLabels[i].setPreferredSize(new Dimension(GUISettings.SMALL_PANEL_SIZE-20,GUISettings.SMALL_PANEL_SIZE/10));
            }
            statisticLabels[0].setText("Party");
            statisticLabels[1].setText("0/0");
            statisticLabels[2].setText("0/0");

            for(int i=0;i<3;i++){
                this.add(statisticLabels[i]);
            }
        }

        public void refresh(){
            statisticLabels[1].setText("Health: "+GameCollection.getParty().getCurrentHealth()+"/"+GameCollection.getParty().getMaxHealth());
            statisticLabels[2].setText("Mana: "+GameCollection.getParty().getCurrentMana()+"/"+GameCollection.getParty().getMaxMana());
        }
    }

    public class ChangePanel extends JPanel{

        private JButton next,prev;

        public ChangePanel(String nextButtonText, String prevButtonText, ActionListener nextButtonAction,ActionListener prevButtonAction) {
            this.setPreferredSize(new Dimension(GUISettings.SMALL_PANEL_SIZE-20,GUISettings.PANEL_SIZE/4));
            FlowLayout layout=new FlowLayout(FlowLayout.CENTER);
            layout.setVgap(1);
            this.setLayout(layout);
            this.setBackground(Color.BLACK);

            next=new JButton(nextButtonText);
            next.addActionListener(nextButtonAction);
            next.setPreferredSize(new Dimension(GUISettings.SMALL_PANEL_SIZE-20,(int)(GUISettings.PANEL_SIZE*0.075)));
            next.setMargin(new Insets(0,0,0,0));

            prev=new JButton(prevButtonText);
            prev.addActionListener(prevButtonAction);
            prev.setPreferredSize(new Dimension(GUISettings.SMALL_PANEL_SIZE-20,(int)(GUISettings.PANEL_SIZE*0.075)));
            prev.setMargin(new Insets(0,0,0,0));

            this.add(next);
            this.add(prev);
        }
    }

    public class UseItemPanel extends JPanel{

        private JButton useItem;
        private ItemSlot itemSlot;

        public UseItemPanel() {
            this.setPreferredSize(new Dimension(GUISettings.SMALL_PANEL_SIZE-20,(int)(GUISettings.PANEL_SIZE*0.25)));
            FlowLayout layout=new FlowLayout(FlowLayout.CENTER);
            layout.setVgap(10);
            this.setLayout(layout);
            this.setBackground(Color.BLACK);

            useItem=new JButton("Use item");
            useItem.setPreferredSize(new Dimension(GUISettings.SMALL_PANEL_SIZE-50,(int)(GUISettings.PANEL_SIZE*0.05)));
            useItem.addActionListener(e->GameCollection.getEquipment().useChosenItem());

            itemSlot=new ItemSlot(null, BAG_SLOT_ICON,0, CharacterEquipment.USE_SLOT);

            this.add(itemSlot);
            this.add(useItem);
        }

        void refresh(){
            itemSlot.setItem(GameCollection.getEquipment().getUseItem());
        }

    }

}
