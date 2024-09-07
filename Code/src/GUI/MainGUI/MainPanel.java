package GUI.MainGUI;

import GUI.EquipmentGUI.EquipmentView;
import GUI.FightGUI.FightView;
import GUI.GUISettings;
import GUI.StartGUI.StartView;
import GUI.WalkingGUI.WalkingView;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    private static final int START_PANEL=0;
    private static final int WALKING_PANEL=1;
    private static final int FIGHTING_PANEL=2;
    private static final int EQUIPMENT_PANEL=3;
    private final CardLayout layout;

    private int currentState;

    public MainPanel(StartView startView,WalkingView walkingView,FightView fightView,EquipmentView equipmentView) {
        //Setting panel
        layout=new CardLayout();
        this.setLayout(layout);
        this.setPreferredSize(new Dimension(GUISettings.heightAndWidth,GUISettings.heightAndWidth));
        this.setBackground(Color.BLACK);

        currentState=START_PANEL;
        //Set panels
        this.add("Start",startView);
        this.add("Walking",walkingView);
        this.add("Fight",fightView);
        this.add("Equipment",equipmentView);

        //Refresh
        this.revalidate();
        this.repaint();
    }
}
