package Game;

import Character.PlayerParty;
import Equipment.EquipmentModule;
import Fight.FightModule;
import GUI.StartGUI.MenuModule;
import Loot.LootModule;
import Walking.WalkingModule;
import Character.PlayerCharacter;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    public static final ArrayList<PlayerCharacter> START_CHARACTER = new ArrayList<>(List.of(
            new PlayerCharacter(24,12,12,12,12,12,"Warrior",new ImageIcon("Texture/CharacterTexture/player.png"),new Tags[]{Tags.WARRIOR}),
            new PlayerCharacter(12,18,12,12,12,12,"Bandit",new ImageIcon("Texture/CharacterTexture/player.png"),new Tags[]{Tags.THIEF}),
            new PlayerCharacter(24,12,12,12,12,12,"Warrior",new ImageIcon("Texture/CharacterTexture/player.png"),new Tags[]{Tags.WARRIOR}),
            new PlayerCharacter(24,12,12,12,12,12,"Warrior",new ImageIcon("Texture/CharacterTexture/player.png"),new Tags[]{Tags.WARRIOR}),
            new PlayerCharacter(24,12,12,12,12,12,"Warrior",new ImageIcon("Texture/CharacterTexture/player.png"),new Tags[]{Tags.WARRIOR})
            ));

    public static final Random random = new Random();
    private static FightModule fightModule;
    private static EquipmentModule equipmentModule;
    private static PlayerParty party;
    private static LootModule lootModule;
    private static WalkingModule walkingModule;
    private static MenuModule menuModule;

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

    public static void setMenuModule(MenuModule menuModule) {
        if(Game.menuModule==null)
            Game.menuModule = menuModule;
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
