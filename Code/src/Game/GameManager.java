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

    //Game state
    private static GameStates state = GameStates.START;
    private static MainGUIState GUIState;

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

    public static void changeState(GameStates newState){
        //TODO Do something
        //Load data
        if (state==GameStates.START){
            PlayerInfo.setParty(new PlayerParty(menuModule.getParty(),new ArrayList<>()));
        }

        state=newState;
        GUIState.refresh(newState);

        if (state==GameStates.WALKING){
            walkingModule.getWalking().walkingStart();
        }
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

}
