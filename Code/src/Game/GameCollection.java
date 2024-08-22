package Game;

import Character.PlayerParty;
import Equipment.EquipmentModule;
import Fight.FightModule;
import Loot.LootModule;

import java.util.Random;

public class GameCollection {

    public static final Random random = new Random();
    private static FightModule fightModule;
    private static EquipmentModule equipmentModule;
    private static PlayerParty party;
    private static LootModule lootModule;

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

    public static FightModule getFight() { return fightModule; }

    public static EquipmentModule getEquipment() {
        return equipmentModule;
    }

    public static PlayerParty getParty() { return party; }

    public static LootModule getLootModule() { return lootModule; }
}
