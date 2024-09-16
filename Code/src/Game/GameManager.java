package Game;

import Character.PlayerParty;
import Equipment.EquipmentModule;
import Fight.FightModule;
import GUI.MainGUI.MainGUIState;
import GUI.MenuGUI.MenuModule;
import Loot.LootModule;
import Walking.WalkingModule;

import java.util.ArrayList;
import java.util.Random;

public class GameManager {

    public static final Random random = new Random();
    private static FightModule fightModule;
    private static EquipmentModule equipmentModule;
    private static PlayerParty party;
    private static LootModule lootModule;
    private static WalkingModule walkingModule;
    private static MenuModule menuModule;
    private static boolean bossFight;

    //Game state
    private static GameStates state = GameStates.START;
    private static MainGUIState GUIState;
    private static int levelPointer=0;

    public static void setFight(FightModule fightModule) {
        if(GameManager.fightModule==null)
            GameManager.fightModule = fightModule;
    }

    public static void setEquipment(EquipmentModule equipmentModule) {
        if(GameManager.equipmentModule==null)
            GameManager.equipmentModule = equipmentModule;
    }

    public static void setLoot(LootModule lootModule){
        if(GameManager.lootModule==null)
            GameManager.lootModule=lootModule;
    }

    public static void setWalkingManager(WalkingModule walkingModule) {
        if(GameManager.walkingModule ==null)
            GameManager.walkingModule = walkingModule;
    }

    public static void setMenuModule(MenuModule menuModule) {
        if(GameManager.menuModule==null)
            GameManager.menuModule = menuModule;
    }

    public static void setGUIState(MainGUIState GUIState) {
        if(GameManager.GUIState==null)
            GameManager.GUIState = GUIState;
    }

    public static void setNextLevel() {
        GameManager.levelPointer++;
    }

    public static void startBossBattle(){
        bossFight=true;
    }

    public static void changeState(GameStates newState){
        if(state==newState)
            return;
        //Load data
        //Old state
        if (state==GameStates.START)
            PlayerInfo.setParty(new PlayerParty(menuModule.getParty(),new ArrayList<>()));
        else if (state == GameStates.WALKING)
            walkingModule.stopWalking();

        //New state
        if (newState==GameStates.WALKING && !bossFight){
            walkingModule.startWalking();
            walkingModule.getState().refresh();
        }
        //Set state
        state=newState;
        GUIState.refresh(newState);
    }

    public static void gameOver(){
        menuModule.gameOver();
        changeState(GameStates.START);
    }

    public static void gameWin(){
        menuModule.playerWin();
        changeState(GameStates.START);
    }

    public static FightModule getFight() { return fightModule; }

    public static EquipmentModule getEquipment() {
        return equipmentModule;
    }

    public static LootModule getLootModule() { return lootModule; }

    public static WalkingModule getWalkingManager() {
        return walkingModule;
    }

    public static MenuModule getMenuModule() {
        return menuModule;
    }

    public static int getLevelPointer() {
        return levelPointer;
    }

    public static boolean isBossFight() {
        return bossFight;
    }
}
