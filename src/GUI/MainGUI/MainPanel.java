package GUI.MainGUI;

import GUI.EquipmentGUI.EquipmentView;
import GUI.FightGUI.FightView;
import GUI.GUISettings;
import GUI.MenuGUI.MenuView;
import GUI.WalkingGUI.WalkingView;
import Game.GameStates;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainPanel extends JPanel {
    private final CardLayout layout;
    private final MenuView menuView;
    private final WalkingView walkingView;
    private final FightView fightView;
    private final EquipmentView equipmentView;

    private GameStates state;

    public MainPanel(MenuView menuView, WalkingView walkingView, FightView fightView, EquipmentView equipmentView) {
        //Setting panel
        layout = new CardLayout();
        state=GameStates.MENU;
        this.setLayout(layout);
        this.setPreferredSize(new Dimension(GUISettings.heightAndWidth, GUISettings.heightAndWidth));
        this.setMinimumSize(new Dimension(GUISettings.heightAndWidth, GUISettings.heightAndWidth));
        this.setBackground(Color.BLACK);

        //Set panels
        this.menuView =menuView;
        this.walkingView=walkingView;
        this.fightView=fightView;
        this.equipmentView=equipmentView;

        this.add("Start", menuView);
        this.add("Walking", walkingView);
        this.add("Fight", fightView);
        this.add("Equipment", equipmentView);

    }

    public void changeView(GameStates state) {
        this.state=state;
        switch (state) {
            case MENU -> layout.show(this, "Start");
            case WALKING -> layout.show(this, "Walking");
            case FIGHTING -> layout.show(this, "Fight");
            case EQUIPMENT -> layout.show(this, "Equipment");
        }
    }

    public void refresh(){
        switch (state){
            case MENU ->  menuView.refresh();
            case WALKING -> walkingView.refresh();
            case FIGHTING -> fightView.refresh();
            case EQUIPMENT -> equipmentView.refresh();
        }

        //Refresh
        this.revalidate();
        this.repaint();
    }

    public void resize(int newSize){
        GUISettings.setProportion(newSize);
        this.setPreferredSize(new Dimension(GUISettings.getResizedValue(GUISettings.heightAndWidth), GUISettings.getResizedValue(GUISettings.heightAndWidth)));

        //Resize components
        menuView.resize();
        walkingView.resize();
        fightView.resize();
        equipmentView.resize();
    }

}
