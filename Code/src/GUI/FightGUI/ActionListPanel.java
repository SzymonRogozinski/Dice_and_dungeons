package GUI.FightGUI;

import Character.PlayerCharacter;
import Equipment.Items.ActionItem;
import Equipment.Items.SpellItem;
import Equipment.Items.UsableItem;
import GUI.GUISettings;
import Game.GameManager;
import Game.PlayerInfo;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class ActionListPanel extends JPanel {

    //Sizes and placement of button
    private final static int buttonWidth=GUISettings.SMALL_PANEL_SIZE*2/3;
    private final static int buttonHeight=GUISettings.SMALL_PANEL_SIZE/3;
    private final static int buttonHGap =GUISettings.PANEL_SIZE/5-buttonWidth*2/3;
    private final static int buttonVGap =(GUISettings.SMALL_PANEL_SIZE-buttonHeight)/2;
    private final static int backpackButtonHGap =buttonHGap/2;
    private final static int backpackButtonVGap =buttonVGap/3;
    private CardLayout layout;
    private CardPanel startPanel, fightPanel,magicPanel;
    private BackpackCardPanel itemPanel;

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
        itemPanel=new BackpackCardPanel(border,new ArrayList<>(),"Start");

        this.add("Start",startPanel);
        this.add("Items",itemPanel);
        this.add("Fight",fightPanel);
        this.add("Magic",magicPanel);
    }

    public void loadAction(){
        if(!(GameManager.getFight().getCharacter() instanceof PlayerCharacter character))
            throw new RuntimeException("Illegal state, enemy and player character were mixed!");
        //items
        ArrayList<ActionItem> items = character.getEquipment().getNotNullActionItems();
        ArrayList<JButton> buttons=new ArrayList<>();
        for(ActionItem item:items){
            JButton button=new JButton(item.name);
            button.setSize(buttonWidth,buttonHeight);
            button.addActionListener(e-> {
                GameManager.getFight().choosedAction(item.getAction());
                changePage("Start");
            });
            buttons.add(button);
        }
        fightPanel.loadNewAction(buttons);

        //usable items
        ArrayList<UsableItem> usableItems = PlayerInfo.getParty().getBackpack().getUsableItems();
        itemPanel.reset(usableItems);

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
                if(PlayerInfo.getParty().getCurrentMana()<spell.getAction().getManaCost()){
                    System.out.println("You don't have enough mana!");
                }
                else {
                    PlayerInfo.getParty().spendMana(spell.getAction().getManaCost());
                    GameManager.getFight().choosedAction(spell.getAction());
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
        private FlowLayout layout;
        CardPanel(Border border, ArrayList<JButton> buttons){
            this.buttons=buttons;
            //Set display
            this.setSize(GUISettings.PANEL_SIZE,GUISettings.SMALL_PANEL_SIZE);
            layout=new FlowLayout();
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
            layout=new FlowLayout();
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
            this.repaint();
            this.revalidate();
        }

        FlowLayout getFlowLayout(){
            return layout;
        }
    }

    private class BackpackCardPanel extends CardPanel{

        private int pageNumber;
        private final int pageSize = 8; //Plus goBackButton
        private ArrayList<UsableItem> items;
        private JButton next,prev;

        BackpackCardPanel(Border border, ArrayList<JButton> buttons,String goBackName){
            super(border,buttons,goBackName);
            getFlowLayout().setHgap(backpackButtonHGap);
            getFlowLayout().setVgap(backpackButtonVGap);
            next=new JButton("Next");
            next.setSize(buttonWidth,buttonHeight);
            next.addActionListener(e->next());

            prev=new JButton("Prev");
            prev.setSize(buttonWidth,buttonHeight);
            prev.addActionListener(e->prev());
        }

        void reset(ArrayList<UsableItem> items){
            this.items=items;
            pageNumber=0;
            ArrayList<JButton> buttons = new ArrayList<>();
            //Check if add next button
            int itemCount = items.size()<=pageSize?items.size():pageSize-1;
            //Loading action
            for(int i=0;i<itemCount;i++){
                UsableItem item = items.get(i);
                JButton button=new JButton(item.name);
                button.setSize(buttonWidth,buttonHeight);
                button.addActionListener(e-> {
                    GameManager.getFight().choosedAction(item.getAction());
                    PlayerInfo.getParty().getBackpack().removeFromBackpack(item);
                    changePage("Start");
                });
                buttons.add(button);
            }
            if(items.size()>pageSize)
                buttons.add(next);
            loadNewAction(buttons);
        }

        void next(){
            //check if there is more items on next page
            if(items.size()<=pageSize)
                return;
            int startIndex,itemCount;
            startIndex = pageNumber==0?0:((pageSize-1)+(pageNumber-1)*(pageSize-2));
            if(startIndex+pageSize-1>=items.size())
                return;
            pageNumber++;
            ArrayList<JButton> buttons = new ArrayList<>();
            startIndex+=pageNumber==1?(pageSize-1):(pageSize-2);
            itemCount = (startIndex+pageSize-1)>=items.size()?(items.size()-startIndex):(pageSize-2);

            //Loading action
            for(int i=startIndex;i<startIndex+itemCount;i++){
                UsableItem item = items.get(i);
                JButton button=new JButton(item.name);
                button.setSize(buttonWidth,buttonHeight);
                button.addActionListener(e-> {
                    GameManager.getFight().choosedAction(item.getAction());
                    PlayerInfo.getParty().getBackpack().removeFromBackpack(item);
                    changePage("Start");
                });
                buttons.add(button);
            }
            buttons.add(prev);
            if(items.size()>startIndex+itemCount)
                buttons.add(next);
            loadNewAction(buttons);
        }

        void prev(){
            if(pageNumber==0)
                return;
            pageNumber--;
            int startIndex,itemCount;
            ArrayList<JButton> buttons = new ArrayList<>();
            if(pageNumber==0){
                startIndex = 0;
                itemCount = pageSize-1;
            }else{
                startIndex = ((pageSize-1)+(pageNumber-1)*(pageSize-2));
                itemCount = (startIndex+pageSize-1)>=items.size()?(items.size()-startIndex):(pageSize-2);
            }
            //Loading action
            for(int i=startIndex;i<startIndex+itemCount;i++){
                UsableItem item = items.get(i);
                JButton button=new JButton(item.name);
                button.setSize(buttonWidth,buttonHeight);
                button.addActionListener(e-> {
                    GameManager.getFight().choosedAction(item.getAction());
                    PlayerInfo.getParty().getBackpack().removeFromBackpack(item);
                    changePage("Start");
                });
                buttons.add(button);
            }
            if(pageNumber>0)
                buttons.add(prev);
            if((startIndex+pageSize-1)<items.size())
                buttons.add(next);
            loadNewAction(buttons);
        }
    }

}
