package Game;

import Character.PlayerParty;
import Equipment.EquipmentModule;
import Fight.FightModule;
import GUI.MainGUI.MainGUIState;
import GUI.MenuGUI.MenuModule;
import Game.Threads.ThreadManager;
import Loot.LootModule;
import Walking.WalkingModule;

import java.util.ArrayList;
import java.util.Random;

public class GameManager {

    private static final Random random = new Random();
    private static FightModule fightModule;
    private static EquipmentModule equipmentModule;
    private static LootModule lootModule;
    private static WalkingModule walkingModule;
    private static MenuModule menuModule;
    private static ThreadManager threadManager;
    private static boolean bossFight;

    //Game state
    private static GameStates state = GameStates.MENU;
    private static MainGUIState GUIState;
    private static int levelPointer = 0;

    public static void refresh(){
        GUIState.refresh();
    }

    public static void setThreadManager(ThreadManager threadManager) {
        if(GameManager.threadManager==null)
            GameManager.threadManager = threadManager;
    }

    public static ThreadManager getThreadManager() {
        return threadManager;
    }

    public static void setLoot(LootModule lootModule) {
        if (GameManager.lootModule == null)
            GameManager.lootModule = lootModule;
    }

    public static void setGUIState(MainGUIState GUIState) {
        if (GameManager.GUIState == null)
            GameManager.GUIState = GUIState;
    }

    public static void setNextLevel() {
        GameManager.levelPointer++;
    }

    public static void startBossBattle() {
        bossFight = true;
    }

    public static void changeState(GameStates newState) {
        if (state == newState)
            return;
        //Load data
        //Old state
        if (state == GameStates.MENU)
            PlayerInfo.setParty(new PlayerParty(menuModule.getParty(), new ArrayList<>()));
        else if (state == GameStates.WALKING)
            walkingModule.stopWalking();

        //New state
        if (newState == GameStates.WALKING && !bossFight) {
            walkingModule.startWalking();
        }
        //Set state
        state = newState;
        GUIState.changeState(newState);
    }

    public static void gameOver() {
        menuModule.gameOver();
        changeState(GameStates.MENU);
    }

    public static void gameWin() {
        menuModule.playerWin();
        changeState(GameStates.MENU);
    }

    public static FightModule getFight() {
        return fightModule;
    }

    public static void setFight(FightModule fightModule) {
        if (GameManager.fightModule == null)
            GameManager.fightModule = fightModule;
    }

    public static EquipmentModule getEquipment() {
        return equipmentModule;
    }

    public static void setEquipment(EquipmentModule equipmentModule) {
        if (GameManager.equipmentModule == null)
            GameManager.equipmentModule = equipmentModule;
    }

    public static LootModule getLootModule() {
        return lootModule;
    }

    public static WalkingModule getWalkingManager() {
        return walkingModule;
    }

    public static void setWalkingManager(WalkingModule walkingModule) {
        if (GameManager.walkingModule == null)
            GameManager.walkingModule = walkingModule;
    }

    public static MenuModule getMenuModule() {
        return menuModule;
    }

    public static void setMenuModule(MenuModule menuModule) {
        if (GameManager.menuModule == null)
            GameManager.menuModule = menuModule;
    }

    public static int getLevelPointer() {
        return levelPointer;
    }

    public static GameLevel getCurrentLevel() {
        return GameConst.LEVELS.get(levelPointer);
    }

    public static boolean isBossFight() {
        return bossFight;
    }

    public static Random getRandom() {
        return random;
    }
}
