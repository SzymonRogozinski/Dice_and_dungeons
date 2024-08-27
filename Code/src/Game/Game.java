package Game;

import Character.PlayerParty;
import Equipment.EquipmentModule;
import Fight.FightModule;
import Loot.LootModule;
import Walking.WalkingModule;

import java.util.Random;

public class Game {

    public static final Random random = new Random();
    private static FightModule fightModule;
    private static EquipmentModule equipmentModule;
    private static PlayerParty party;
    private static LootModule lootModule;
    private static WalkingModule walkingModule;

    public static void setFight(FightModule fightModule) {
        if(Game.fightModule==null)
            Game.fightModule = fightModule;
    }

    public static void setEquipment(EquipmentModule equipmentModule) {
        if(Game.equipmentModule==null)
            Game.equipmentModule = equipmentModule;
    }

    public static void setLoot(LootModule lootModule){
        if(Game.lootModule==null)
            Game.lootModule=lootModule;
    }

    public static void setWalkingManager(WalkingModule walkingModule) {
        if(Game.walkingModule ==null)
            Game.walkingModule = walkingModule;
    }

    public static FightModule getFight() { return fightModule; }

    public static EquipmentModule getEquipment() {
        return equipmentModule;
    }

    public static LootModule getLootModule() { return lootModule; }

    public static WalkingModule getWalkingManager() {
        return walkingModule;
    }
}
