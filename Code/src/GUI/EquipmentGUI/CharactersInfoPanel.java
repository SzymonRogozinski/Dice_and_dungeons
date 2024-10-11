package GUI.EquipmentGUI;

import Character.PlayerCharacter;
import Equipment.CharacterEquipment;
import GUI.GUISettings;
import Game.GameManager;
import Game.PlayerInfo;

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
        changeCharacterPanel=new ChangePanel("Next character","Previous character",e-> GameManager.getEquipment().changeCharacter(true), e-> GameManager.getEquipment().changeCharacter(false));
        useItemPanel=new UseItemPanel();
        changeBackpackPagePanel=new ChangePanel("Next page","Previous page",e-> GameManager.getEquipment().changeBackpackPage(true), e-> GameManager.getEquipment().changeBackpackPage(false));

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
            PlayerCharacter player= GameManager.getEquipment().getCurrentCharacter();
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

        private JProgressBar healthBar, manaBar;

        public PartyInfoPanel() {
            this.setPreferredSize(new Dimension(GUISettings.SMALL_PANEL_SIZE-20,GUISettings.PANEL_SIZE/4));
            FlowLayout layout=new FlowLayout(FlowLayout.CENTER);
            layout.setVgap(1);
            this.setLayout(layout);
            this.setBackground(Color.BLACK);

            JLabel health=new JLabel("Party health");
            health.setForeground(Color.WHITE);
            this.add(health);

            healthBar=new JProgressBar();
            healthBar.setForeground(Color.RED);
            healthBar.setStringPainted(true);
            healthBar.setPreferredSize(new Dimension(GUISettings.SMALL_PANEL_SIZE-6,GUISettings.SMALL_PANEL_SIZE/8));
            this.add(healthBar);

            JLabel mana=new JLabel("Party mana");
            mana.setForeground(Color.WHITE);
            this.add(mana);

            manaBar=new JProgressBar();
            manaBar.setForeground(Color.BLUE);
            manaBar.setStringPainted(true);
            manaBar.setPreferredSize(new Dimension(GUISettings.SMALL_PANEL_SIZE-6,GUISettings.SMALL_PANEL_SIZE/8));
            this.add(manaBar);

        }

        public void refresh(){
            healthBar.setMaximum(PlayerInfo.getParty().getMaxHealth());
            manaBar.setMaximum(PlayerInfo.getParty().getMaxMana());
            healthBar.setValue(PlayerInfo.getParty().getCurrentHealth());
            String healthString = PlayerInfo.getParty().getCurrentHealth()+"/"+ PlayerInfo.getParty().getMaxHealth();
            if(PlayerInfo.getParty().getShield()>0)
                healthString+=" +"+ PlayerInfo.getParty().getShield();
            healthBar.setString(healthString);
            manaBar.setValue(PlayerInfo.getParty().getCurrentMana());
            manaBar.setString(PlayerInfo.getParty().getCurrentMana()+"/"+ PlayerInfo.getParty().getMaxMana());
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
            useItem.addActionListener(e-> GameManager.getEquipment().useChosenItem());

            itemSlot=new ItemSlot(null, BAG_SLOT_ICON,0, CharacterEquipment.USE_SLOT);

            this.add(itemSlot);
            this.add(useItem);
        }

        void refresh(){
            itemSlot.setItem(GameManager.getEquipment().getUseItem());
        }

    }

}
