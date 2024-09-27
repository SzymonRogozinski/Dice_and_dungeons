package GUI.MainGUI;

import GUI.EquipmentGUI.EquipmentView;
import GUI.FightGUI.FightView;
import GUI.MenuGUI.MenuView;
import GUI.WalkingGUI.WalkingKeyListener;
import GUI.WalkingGUI.WalkingView;
import Game.GameStates;

import javax.swing.*;

public class MainFrame extends JFrame {

    private final MainPanel mainPanel;

    public MainFrame(MenuView menuView, WalkingView walkingView, FightView fightView, EquipmentView equipmentView) {
        this.setTitle("Dice and Dungeons");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(new WalkingKeyListener());

        mainPanel = new MainPanel(menuView,walkingView,fightView,equipmentView);
        this.add(mainPanel);
    }

    public void refresh(GameStates state){
        mainPanel.changeView(state);
    }
}
