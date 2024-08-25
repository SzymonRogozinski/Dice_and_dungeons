package Game;

import Character.PlayerParty;
import Equipment.EquipmentModule;
import Fight.FightModule;
import Loot.LootModule;
import Walking.WalkingManager;

import java.util.Random;

public class GameCollection {

    public static final Random random = new Random();
    private static FightModule fightModule;
    private static EquipmentModule equipmentModule;
    private static PlayerParty party;
    private static LootModule lootModule;
    private static WalkingManager walkingManager;

    public static void setFight(FightModule fightModule) {
        if(GameCollection.fightModule==null)
            GameCollection.fightModule = fightModule;
    }

    public static void setEquipment(EquipmentModule equipmentModule) {
        if(GameCollection.equipmentModule==null)
            GameCollection.equipmentModule = equipmentModule;
    }

    public static void setParty(PlayerParty party) {
        if(GameCollection.party==null)
            GameCollection.party = party;
    }

    public static void setLoot(LootModule lootModule){
        if(GameCollection.lootModule==null)
            GameCollection.lootModule=lootModule;
    }

    public static void setWalkingManager(WalkingManager walkingManager) {
        if(GameCollection.walkingManager==null)
            GameCollection.walkingManager = walkingManager;
    }

    public static FightModule getFight() { return fightModule; }

    public static EquipmentModule getEquipment() {
        return equipmentModule;
    }

    public static PlayerParty getParty() { return party; }

    public static LootModule getLootModule() { return lootModule; }

    public static WalkingManager getWalkingManager() {
        return walkingManager;
    }
}
