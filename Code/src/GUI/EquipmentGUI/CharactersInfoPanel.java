package GUI.EquipmentGUI;

import GUI.GUISettings;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class CharactersInfoPanel extends JPanel {

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
        changeCharacterPanel=new ChangePanel("Next character","Previous character",e->System.out.println("click"),e->System.out.println("click"));
        useItemPanel=new UseItemPanel();
        changeBackpackPagePanel=new ChangePanel("Next page","Previous page",e->System.out.println("click"),e->System.out.println("click"));

        this.add(headline);
        this.add(charactersInfoPanel);
        this.add(partyInfoPanel);
        this.add(changeCharacterPanel);
        this.add(useItemPanel);
        this.add(changeBackpackPagePanel);

        setEquipmentVisibility(false);
    }

    public void setEquipmentVisibility(boolean isVisible){
        charactersInfoPanel.setVisible(isVisible);
        changeCharacterPanel.setVisible(isVisible);

        useItemPanel.setVisible(!isVisible);
        changeBackpackPagePanel.setVisible(!isVisible);
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
            statisticLabels[0].setText("Bandit");
            statisticLabels[1].setText("Strength: 12");
            statisticLabels[2].setText("Endurance: 12");
            statisticLabels[3].setText("Intelligence: 10");
            statisticLabels[4].setText("Charisma: 10");
            statisticLabels[5].setText("Cunning: 12");
            statisticLabels[6].setText("Luck: 12");

            for(int i=0;i<7;i++){
                this.add(statisticLabels[i]);
            }
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
            //TEST DATA
            statisticLabels[0].setText("Party");
            statisticLabels[1].setText("Health: 100/120");
            statisticLabels[2].setText("Mana: 80/90");

            for(int i=0;i<3;i++){
                this.add(statisticLabels[i]);
            }
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

            itemSlot=new ItemSlot();

            this.add(itemSlot);
            this.add(useItem);
        }
    }

}
