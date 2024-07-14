package Game;

import Equipment.EquipmentModule;
import Fight.FightModule;

public class GameCollection {
    private static FightModule fightModule;
    private static EquipmentModule equipmentModule;

    public static void setFight(FightModule fightModule) {
        if(GameCollection.fightModule==null)
            GameCollection.fightModule = fightModule;
    }

    public static void setEquipment(EquipmentModule equipmentModule) {
        if(GameCollection.equipmentModule==null)
            GameCollection.equipmentModule = equipmentModule;
    }

    public static FightModule getFight() {
        return fightModule;
    }

    public static EquipmentModule getEquipment() {
        return equipmentModule;
    }
}
