package Game;

import Equipment.EquipmentModule;
import Fight.FightModule;
import GUI.EquipmentGUI.EquipmentGUIState;
import GUI.EquipmentGUI.EquipmentView;
import GUI.FightGUI.FightGUIState;
import GUI.FightGUI.FightView;
import GUI.MainGUI.MainFrame;
import GUI.MainGUI.MainGUIState;
import GUI.MenuGUI.MenuModule;
import GUI.MenuGUI.MenuState;
import GUI.MenuGUI.MenuView;
import GUI.WalkingGUI.WalkingGUIState;
import GUI.WalkingGUI.WalkingView;
import Loot.LootModule;
import Walking.WalkingModule;

public class Game {

    public Game() {
        //Set views and panels
        MenuView menuView = new MenuView();
        WalkingView walkingView = new WalkingView();
        FightView fightView = new FightView();
        EquipmentView equipmentView = new EquipmentView();

        MainFrame mainFrame = new MainFrame(menuView,walkingView,fightView,equipmentView);

        //Set modules and states
        GameManager.setMenuModule(new MenuModule(new MenuState(menuView)));
        GameManager.setWalkingManager(new WalkingModule(new WalkingGUIState(walkingView)));
        GameManager.setFight(new FightModule(new FightGUIState(fightView)));
        GameManager.setEquipment(new EquipmentModule(new EquipmentGUIState(equipmentView)));
        GameManager.setLoot(new LootModule());
        GameManager.setGUIState(new MainGUIState(mainFrame));

        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.setFocusable(true);

    }
}
