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
import GUI.QuestGUI.QuestView;
import GUI.WalkingGUI.WalkingView;
import Game.Threads.ThreadManager;
import Loot.LootModule;
import Quest.QuestModule;
import Walking.WalkingModule;

public class Game {

    public Game() {
        //Set views and panels
        MenuView menuView = new MenuView();
        WalkingView walkingView = new WalkingView();
        FightView fightView = new FightView();
        EquipmentView equipmentView = new EquipmentView();
        QuestView questView = new QuestView();

        MainFrame mainFrame = new MainFrame(menuView, walkingView, fightView, equipmentView,questView);

        //Threads
        GameManager.setThreadManager(new ThreadManager());

        //Set modules and states
        GameManager.setQuestModule(new QuestModule());
        GameManager.setMenuModule(new MenuModule(new MenuState(menuView)));
        GameManager.setWalkingManager(new WalkingModule());
        GameManager.setFight(new FightModule(new FightGUIState(fightView)));
        GameManager.setEquipment(new EquipmentModule(new EquipmentGUIState(equipmentView)));
        GameManager.setLoot(new LootModule());
        GameManager.setGUIState(new MainGUIState(mainFrame));
        GameManager.setMainFrame(mainFrame);

        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.setFocusable(true);
        mainFrame.setMinimumSize();

        GameManager.getThreadManager().start();
    }
}
