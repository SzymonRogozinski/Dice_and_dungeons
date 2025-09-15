package Game;

import Character.PlayerParty;
import Dialog.DialogModule;
import Equipment.EquipmentModule;
import Fight.FightModule;
import GUI.DialogGUI.DialogView;
import GUI.MainGUI.MainFrame;
import GUI.MainGUI.MainGUIState;
import GUI.MenuGUI.MenuModule;
import Game.Threads.ThreadManager;
import Loot.LootModule;
import Quest.QuestModule;
import Trade.TradeModule;
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
    private static QuestModule questModule;
    private static DialogModule dialogModule;
    private static TradeModule tradeModule;
    private static ThreadManager threadManager;
    private static MainFrame mainFrame;
    private static boolean bossFight;

    //Game state
    private static GameStates state = GameStates.MENU;
    private static MainGUIState GUIState;
    private static int levelPointer = 0;
    private static GameLevel level = null;

    public static void refresh(){
        if(PlayerInfo.getParty()!=null)
            GameManager.getWalkingManager().getWalking().makePlayerMove();
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

    public static QuestModule getQuestModule() {
        return questModule;
    }

    public static void setQuestModule(QuestModule questModule) {
        if (GameManager.questModule == null)
            GameManager.questModule = questModule;
    }

    public static DialogModule getDialogModule() {
        return dialogModule;
    }

    public static void setDialogModule(DialogModule dialogModule) {
        if(GameManager.dialogModule == null)
            GameManager.dialogModule = dialogModule;
    }

    public static TradeModule getTradeModule() {
        return tradeModule;
    }

    public static void setTradeModule(TradeModule tradeModule) {
        if(GameManager.tradeModule == null)
            GameManager.tradeModule = tradeModule;
    }

    public static int getLevelPointer() {
        return levelPointer;
    }

    public static GameLevel getCurrentLevel() {
        return level==null ? GameConst.LEVELS.get(levelPointer): level;
    }

    public static void setLevel(GameLevel level) {
        GameManager.level = level;
    }

    public static boolean isBossFight() {
        return bossFight;
    }

    public static Random getRandom() {
        return random;
    }

    public static void setMainFrame(MainFrame mainFrame) {
        GameManager.mainFrame = mainFrame;
    }

    public static MainFrame getMainFrame() {
        return mainFrame;
    }
}
