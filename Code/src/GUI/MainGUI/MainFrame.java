package GUI.MainGUI;

import GUI.EquipmentGUI.EquipmentView;
import GUI.FightGUI.FightView;
import GUI.StartGUI.StartView;
import GUI.WalkingGUI.WalkingView;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame(StartView startView, WalkingView walkingView, FightView fightView, EquipmentView equipmentView) {
        this.setTitle("Dice and Dungeons");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MainPanel mainPanel = new MainPanel(startView,walkingView,fightView,equipmentView);
        this.add(mainPanel);
    }
}
