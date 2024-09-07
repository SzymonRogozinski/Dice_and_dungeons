package Game;

import Equipment.EquipmentModule;
import Fight.FightModule;
import GUI.EquipmentGUI.EquipmentGUIState;
import GUI.EquipmentGUI.EquipmentView;
import GUI.FightGUI.FightGUIState;
import GUI.FightGUI.FightView;
import GUI.MainGUI.MainFrame;
import GUI.MainGUI.MainPanel;
import GUI.StartGUI.MenuModule;
import GUI.StartGUI.MenuState;
import GUI.StartGUI.StartView;
import GUI.WalkingGUI.WalkingGUIState;
import GUI.WalkingGUI.WalkingView;
import Walking.WalkingModule;

public class Game {

    public Game() {
        //Set views and panels
        StartView startView = new StartView();
        WalkingView walkingView = new WalkingView();
        FightView fightView = new FightView();
        EquipmentView equipmentView = new EquipmentView();

        MainFrame mainFrame = new MainFrame(startView,walkingView,fightView,equipmentView);

        //Set modules and states
        GameManager.setMenuModule(new MenuModule(new MenuState(startView)));
        GameManager.setWalkingManager(new WalkingModule(new WalkingGUIState(walkingView)));
        GameManager.setFight(new FightModule(new FightGUIState(fightView)));
        GameManager.setEquipment(new EquipmentModule(new EquipmentGUIState(equipmentView)));
    }
}
