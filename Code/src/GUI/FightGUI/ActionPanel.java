package GUI.FightGUI;

import GUI.GUISettings;
import main.FightModule;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class ActionPanel extends JPanel {

    //Sizes and placement of button
    private final static int buttonWidth=GUISettings.SMALL_PANEL_SIZE*2/3;
    private final static int buttonHeight=GUISettings.SMALL_PANEL_SIZE/3;
    private final static int buttonVGap=GUISettings.PANEL_SIZE/4-buttonHeight/2;

    private FightModule fight;
    private CardLayout layout;

    public ActionPanel(Border border){
        //Set display
        this.setSize(GUISettings.SMALL_PANEL_SIZE,GUISettings.PANEL_SIZE);
        this.layout=new CardLayout();
        this.setLayout(layout);
        this.setBorder(border);
        this.setBackground(Color.BLACK);

        ArrayList<JButton> actionButtons1=new ArrayList<>();
        //Setting buttons
        for(int i=1;i<4;i++){
            JButton action=new JButton("Action "+i);
            action.setSize(buttonWidth,buttonHeight);
            actionButtons1.add(action);
        }
        actionButtons1.get(0).addActionListener(e->changePage("Fight"));

        ArrayList<JButton> actionButtons2=new ArrayList<>();
        JButton action1=new JButton("Action 4");
        action1.setSize(buttonWidth,buttonHeight);
        action1.addActionListener(e->{
            fight.chooseTarget();
            changePage("Start");
        });
        actionButtons2.add(action1);

        JButton action3=new JButton("Go back");
        action3.setSize(buttonWidth,buttonHeight);
        action3.addActionListener(e->changePage("Start"));
        actionButtons2.add(action3);

        this.add("Start",new CardPanel(border,actionButtons1));
        this.add("Fight",new CardPanel(border,actionButtons2));
    }

    public void setFight(FightModule fight){
        this.fight=fight;
    }

    private void changePage(String pageName){
        layout.show(this,pageName);
    }

    private class CardPanel extends JPanel{
        CardPanel(Border border, ArrayList<JButton> buttons){
            //Set display
            this.setSize(GUISettings.SMALL_PANEL_SIZE,GUISettings.PANEL_SIZE);
            FlowLayout layout=new FlowLayout();
            layout.setVgap(buttonVGap);
            this.setLayout(layout);
            this.setBorder(border);
            this.setBackground(Color.BLACK);

            for(JButton button:buttons){
                this.add(button);
            }
        }
    }

}
