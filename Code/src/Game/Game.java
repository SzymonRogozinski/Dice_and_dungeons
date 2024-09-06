package Game;

import Character.PlayerParty;
import Dice.DiceFactory;
import Equipment.EquipmentModule;
import Equipment.Items.ActionItem;
import Equipment.Items.ItemQuality;
import Equipment.Items.SpellItem;
import Fight.ActionTarget;
import Fight.FightModule;
import Fight.GameActions.ItemAction;
import Fight.GameActions.SpellAction;
import GUI.StartGUI.MenuModule;
import Loot.LootModule;
import Walking.WalkingModule;
import Character.PlayerCharacter;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    private static final ActionItem sword = new ActionItem(new ItemAction(DiceFactory.buildDice(new int[][]{{0},{0},{0},{1,4},{1,4},{1,6}}), ActionTarget.ENEMY_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getStrength()),new Tags[]{Tags.ATTACK}), new Tags[]{},new ImageIcon("Texture/Items/sword.png"),"Sword", ItemQuality.COMMON);
    private static final ActionItem shield = new ActionItem(new ItemAction(DiceFactory.buildDice(new int[][]{{0},{2,3,0},{2,3,0},{2,3,0},{2,3,0},{2,3,0}}), ActionTarget.PLAYER_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getCharisma()),new Tags[]{Tags.DEFENCE}), new Tags[]{},new ImageIcon("Texture/Items/shield.png"),"Shield",ItemQuality.COMMON);
    private static final ActionItem mace = new ActionItem(new ItemAction(DiceFactory.buildDice(new int[][]{{0},{0},{0},{1,4},{1,4},{1,6}}), ActionTarget.ENEMY_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getStrength()),new Tags[]{Tags.ATTACK}), new Tags[]{},new ImageIcon("Texture/Items/mace.png"),"Mace",ItemQuality.COMMON);
    private static final ActionItem trap = new ActionItem(new ItemAction(DiceFactory.buildDice(new int[][]{{0},{0},{0},{6,1},{6,1},{6,1}}), ActionTarget.ENEMY_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getCunning()), new Tags[]{}),new Tags[]{},new ImageIcon("Texture/Items/trap.png"),"Trap",ItemQuality.COMMON);
    private static final SpellItem heal = new SpellItem(new SpellAction(DiceFactory.buildDice(new int[][]{{0},{3,3,1},{3,3,1},{3,3,1},{3,3,1},{3,3,1}}), ActionTarget.PLAYER_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getIntelligence()),4,new Tags[]{Tags.MAGIC}), new Tags[]{},null,"Heal",ItemQuality.COMMON);

    public static final ArrayList<PlayerCharacter> START_CHARACTER = new ArrayList<>(List.of(
            new PlayerCharacter(24,12,12,12,12,12,"Warrior",new ImageIcon("Texture/CharacterTexture/player.png"),new Tags[]{Tags.WARRIOR},
                    new ArrayList<>(List.of(sword, shield)),
                    new ArrayList<>(List.of(heal))),
            new PlayerCharacter(12,18,12,12,12,12,"Bandit",new ImageIcon("Texture/CharacterTexture/player.png"),new Tags[]{Tags.THIEF},
                    new ArrayList<>(List.of(mace, trap)),
                    new ArrayList<>(List.of(heal))),
            new PlayerCharacter(24,12,12,12,12,12,"Warrior",new ImageIcon("Texture/CharacterTexture/player.png"),new Tags[]{Tags.WARRIOR},
                    new ArrayList<>(List.of(sword, shield)),
                    new ArrayList<>(List.of(heal))),
            new PlayerCharacter(24,12,12,12,12,12,"Warrior",new ImageIcon("Texture/CharacterTexture/player.png"),new Tags[]{Tags.WARRIOR},
                    new ArrayList<>(List.of(sword, shield)),
                    new ArrayList<>(List.of(heal))),
            new PlayerCharacter(24,12,12,12,12,12,"Warrior",new ImageIcon("Texture/CharacterTexture/player.png"),new Tags[]{Tags.WARRIOR},
                    new ArrayList<>(List.of(sword, shield)),
                    new ArrayList<>(List.of(heal)))
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
