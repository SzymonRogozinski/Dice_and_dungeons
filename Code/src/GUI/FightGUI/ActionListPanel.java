package GUI.FightGUI;

import Fight.ActionItem;
import GUI.GUISettings;
import main.FightModule;
import Character.PlayerCharacter;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class ActionListPanel extends JPanel {

    //Sizes and placement of button
    private final static int buttonWidth=GUISettings.SMALL_PANEL_SIZE*2/3;
    private final static int buttonHeight=GUISettings.SMALL_PANEL_SIZE/3;
    private final static int buttonHGap =GUISettings.PANEL_SIZE/4-buttonWidth/2;
    private final static int buttonVGap =(GUISettings.SMALL_PANEL_SIZE-buttonHeight)/2;
    private FightModule fight;
    private CardLayout layout;
    private CardPanel startPanel;
    private CardPanel fightPanel;

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

        ArrayList<JButton> actionButtons2=new ArrayList<>();

        startPanel=new CardPanel(border,actionButtons1);
        fightPanel=new CardPanel(border,actionButtons2,"Start");

        this.add("Start",startPanel);
        this.add("Fight",fightPanel);
    }

    public void setFight(FightModule fight){
        this.fight=fight;
    }

    public void loadAction(){
        if(!(fight.getCharacter() instanceof PlayerCharacter))
            throw new RuntimeException("Illegal state, enemy and player character were mixed!");
        PlayerCharacter character=(PlayerCharacter) fight.getCharacter();
        ArrayList<ActionItem> items = character.getActionItems();
        ArrayList<JButton> buttons=new ArrayList<>();
        for(ActionItem item:items){
            JButton button=new JButton(item.getName());
            button.setSize(buttonWidth,buttonHeight);
            button.addActionListener(e-> {
                fight.choosedAction(item.getDice(), character.getDiceNumber(character.getStrength()), character.getCharacterRerolls());
                changePage("Start");
            });
            buttons.add(button);
        }
        fightPanel.loadNewAction(buttons);
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
