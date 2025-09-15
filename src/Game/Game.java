package Game;

import Dialog.DialogModule;
import Equipment.EquipmentModule;
import Fight.FightModule;
import GUI.DialogGUI.DialogView;
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
import GUI.TradeGUI.TradeGUIState;
import GUI.TradeGUI.TradeView;
import GUI.WalkingGUI.WalkingView;
import Game.Threads.ThreadManager;
import Loot.LootModule;
import Quest.QuestModule;
import Trade.TradeModule;
import Walking.WalkingModule;

public class Game {

    public Game() {
        //Set views and panels
        MenuView menuView = new MenuView();
        WalkingView walkingView = new WalkingView();
        FightView fightView = new FightView();
        EquipmentView equipmentView = new EquipmentView();
        QuestView questView = new QuestView();
        DialogView dialogView = new DialogView();
        TradeView tradeView = new TradeView();

        MainFrame mainFrame = new MainFrame(menuView, walkingView, fightView, equipmentView,questView, dialogView, tradeView);

        //Threads
        GameManager.setThreadManager(new ThreadManager());

        //Set modules and states
        GameManager.setDialogModule(new DialogModule());
        GameManager.setQuestModule(new QuestModule());
        GameManager.setMenuModule(new MenuModule(new MenuState(menuView)));
        GameManager.setWalkingManager(new WalkingModule());
        GameManager.setFight(new FightModule(new FightGUIState(fightView)));
        GameManager.setEquipment(new EquipmentModule(new EquipmentGUIState(equipmentView)));
        GameManager.setLoot(new LootModule());
        GameManager.setGUIState(new MainGUIState(mainFrame));
        GameManager.setMainFrame(mainFrame);
        GameManager.setTradeModule(new TradeModule(new TradeGUIState(tradeView)));

        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.setFocusable(true);
        mainFrame.setMinimumSize();

        GameManager.getThreadManager().start();
    }
}
