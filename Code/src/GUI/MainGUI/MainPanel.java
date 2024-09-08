package GUI.MainGUI;

import GUI.EquipmentGUI.EquipmentView;
import GUI.FightGUI.FightView;
import GUI.GUISettings;
import GUI.MenuGUI.MenuView;
import GUI.WalkingGUI.WalkingView;
import Game.GameStates;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private final CardLayout layout;

    public MainPanel(MenuView startView, WalkingView walkingView, FightView fightView, EquipmentView equipmentView) {
        //Setting panel
        layout=new CardLayout();
        this.setLayout(layout);
        this.setPreferredSize(new Dimension(GUISettings.heightAndWidth,GUISettings.heightAndWidth));
        this.setBackground(Color.BLACK);

        //Set panels
        this.add("Start",startView);
        this.add("Walking",walkingView);
        this.add("Fight",fightView);
        this.add("Equipment",equipmentView);

        //Refresh
        this.revalidate();
        this.repaint();
    }

    public void changeView(GameStates state){
        switch(state){
            case START -> layout.show(this,"Start");
            case WALKING -> layout.show(this,"Walking");
            case FIGHTING -> layout.show(this,"Fight");
            case EQUIPMENT -> layout.show(this,"Equipment");
        }
        //Refresh
        this.revalidate();
        this.repaint();
    }
}
