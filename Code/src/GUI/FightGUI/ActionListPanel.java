package GUI.FightGUI;

import Character.PlayerCharacter;
import Equipment.Items.ActionItem;
import Equipment.Items.SpellItem;
import Equipment.Items.UsableItem;
import Fight.FightModule;
import Fight.GameActions.ItemAction;
import Fight.GameActions.SpellAction;
import Fight.GameActions.UsableItemAction;
import GUI.GUISettings;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ActionListPanel extends JPanel {

    //Sizes and placement of button
    private final static int buttonWidth=GUISettings.SMALL_PANEL_SIZE*2/3;
    private final static int buttonHeight=GUISettings.SMALL_PANEL_SIZE/3;
    private final static int buttonHGap =GUISettings.PANEL_SIZE/5-buttonWidth*2/3;
    private final static int buttonVGap =(GUISettings.SMALL_PANEL_SIZE-buttonHeight)/2;
    private FightModule fight;
    private CardLayout layout;
    private CardPanel startPanel, fightPanel,magicPanel,itemPanel;

    public ActionListPanel(Border border){
        //Set display
        this.setSize(GUISettings.PANEL_SIZE,GUISettings.SMALL_PANEL_SIZE);
        this.layout=new CardLayout();
        this.setLayout(layout);
        this.setBorder(border);
        this.setBackground(Color.BLACK);

        ArrayList<JButton> actionButtons1=new ArrayList<>();
        //Setting buttons
        String[] names={"Attack","Items","Spells"};
        for(int i=0;i<3;i++){
            JButton action=new JButton(names[i]);
            action.setSize(buttonWidth,buttonHeight);
            actionButtons1.add(action);
        }

        actionButtons1.get(0).addActionListener(e->changePage("Fight"));
        actionButtons1.get(1).addActionListener(e->changePage("Items"));
        actionButtons1.get(2).addActionListener(e->changePage("Magic"));

        startPanel=new CardPanel(border,actionButtons1);
        fightPanel=new CardPanel(border,new ArrayList<>(),"Start");
        magicPanel=new CardPanel(border,new ArrayList<>(),"Start");
        itemPanel=new CardPanel(border,new ArrayList<>(),"Start");

        this.add("Start",startPanel);
        this.add("Items",itemPanel);
        this.add("Fight",fightPanel);
        this.add("Magic",magicPanel);
    }

    public void setFight(FightModule fight){
        this.fight=fight;
    }

    public void loadAction(){
        if(!(fight.getCharacter() instanceof PlayerCharacter))
            throw new RuntimeException("Illegal state, enemy and player character were mixed!");
        PlayerCharacter character=(PlayerCharacter) fight.getCharacter();
        //items
        ArrayList<ActionItem> items = character.getEquipment().getNotNullActionItems();
        ArrayList<JButton> buttons=new ArrayList<>();
        for(ActionItem item:items){
            JButton button=new JButton(item.name);
            button.setSize(buttonWidth,buttonHeight);
            button.addActionListener(e-> {
                fight.choosedAction(item.getAction());
                changePage("Start");
            });
            buttons.add(button);
        }
        fightPanel.loadNewAction(buttons);

        //usable items
        //ArrayList<UsableItemAction> usableItems = character.getUsableItems();
        ArrayList<UsableItem> usableItems = character.getParty().getBackpack().getUsableItems();
        buttons=new ArrayList<>();

        for(UsableItem item:usableItems){
            JButton button=new JButton(item.name);
            button.setSize(buttonWidth,buttonHeight);
            button.addActionListener(e-> {
                fight.choosedAction(item.getAction());
                character.getParty().getBackpack().removeFromBackpack(item);
                changePage("Start");
            });
            buttons.add(button);
        }
        itemPanel.loadNewAction(buttons);

        //spells
        //ArrayList<SpellAction> spellActions = character.getSpells();
        ArrayList<SpellItem> spells = character.getEquipment().getNotNullSpellItems();
        buttons=new ArrayList<>();
        //Mana
        FlowLayout flowLayout=new FlowLayout();
        flowLayout.setHgap(0);
        flowLayout.setVgap(0);
        for(SpellItem spell:spells){
            //TextAreas
            JLabel t = new JLabel(spell.name);
            t.setFont(GUISettings.BUTTON_FONT);
            t.setBackground(new Color(0,0,0,0));

            JLabel tt = new JLabel(" "+spell.getAction().getManaCost());
            tt.setFont(GUISettings.BUTTON_FONT);
            tt.setBackground(new Color(0,0,0,0));
            tt.setForeground(Color.BLUE);

            //Button setup
            JButton button=new JButton();
            button.setLayout(flowLayout);
            button.add(t);
            button.add(tt);

            button.setSize(buttonWidth,buttonHeight);
            button.addActionListener(e-> {
                if(fight.getParty().getCurrentMana()<spell.getAction().getManaCost()){
                    System.out.println("You don't have enough mana!");
                }
                else {
                    fight.getParty().spendMana(spell.getAction().getManaCost());
                    fight.choosedAction(spell.getAction());
                    changePage("Start");
                }
            });
            buttons.add(button);
        }
        magicPanel.loadNewAction(buttons);
    }

    private void changePage(String pageName){
        layout.show(this,pageName);
    }

    private class CardPanel extends JPanel{
        final ArrayList<JButton> buttons;
        private JButton goBackButton;
        CardPanel(Border border, ArrayList<JButton> buttons){
            this.buttons=buttons;
            //Set display
            this.setSize(GUISettings.PANEL_SIZE,GUISettings.SMALL_PANEL_SIZE);
            FlowLayout layout=new FlowLayout();
            layout.setHgap(buttonHGap);
            layout.setVgap(buttonVGap);
            this.setLayout(layout);
            this.setBorder(border);
            this.setBackground(Color.BLACK);

            for(JButton button:buttons){
                this.add(button);
            }
        }

        CardPanel(Border border, ArrayList<JButton> buttons,String goBackName){
            this.buttons=buttons;
            //Set display
            this.setSize(GUISettings.PANEL_SIZE,GUISettings.SMALL_PANEL_SIZE);
            FlowLayout layout=new FlowLayout();
            layout.setHgap(buttonHGap);
            layout.setVgap(buttonVGap);
            this.setLayout(layout);
            this.setBorder(border);
            this.setBackground(Color.BLACK);

            for(JButton button:buttons){
                this.add(button);
            }

            JButton goBackButton=new JButton("Go back");
            goBackButton.setSize(buttonWidth,buttonHeight);
            goBackButton.addActionListener(e->changePage(goBackName));
            this.goBackButton = goBackButton;
            this.add(goBackButton);
        }

        void loadNewAction(ArrayList<JButton> buttons){
            this.removeAll();

            for(JButton button:buttons){
                this.add(button);
            }
            this.add(goBackButton);
        }
    }

}
