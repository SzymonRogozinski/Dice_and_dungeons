package Game;

import Dice.DiceFactory;
import Equipment.Items.ActionItem;
import Equipment.Items.ItemQuality;
import Equipment.Items.SpellItem;
import Fight.ActionTarget;
import Fight.GameActions.ItemAction;
import Fight.GameActions.SpellAction;
import Character.PlayerCharacter;
import Loot.LootSettings;
import Walking.WalkingSettings;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GameConst {
    private static final ActionItem sword = new ActionItem(new ItemAction(DiceFactory.buildDice(new int[][]{{0},{0},{1,4},{1,4},{1,6},{1,6}}), ActionTarget.ENEMY_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getStrength()),new Tags[]{Tags.ATTACK}), new Tags[]{},new ImageIcon("Texture/Items/attack_item/sword.png"),"sword","sword", ItemQuality.COMMON);
    private static final ActionItem bow = new ActionItem(new ItemAction(DiceFactory.buildDice(new int[][]{{0},{1,2},{1,3},{1,3},{1,6},{1,6}}), ActionTarget.ENEMY_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getStrength()),new Tags[]{Tags.ATTACK}), new Tags[]{},new ImageIcon("Texture/Items/attack_item/bow.png"),"bow","bow", ItemQuality.COMMON);
    private static final ActionItem shield = new ActionItem(new ItemAction(DiceFactory.buildDice(new int[][]{{0},{2,4,0},{2,4,0},{2,4,0},{2,4,0},{2,4,0}}), ActionTarget.PLAYER_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getEndurance()),new Tags[]{Tags.DEFENCE}), new Tags[]{},new ImageIcon("Texture/Items/attack_item/shield.png"),"shield","shield",ItemQuality.COMMON);
    private static final ActionItem trap = new ActionItem(new ItemAction(DiceFactory.buildDice(new int[][]{{0},{0},{0},{6,1},{6,1},{6,2}}), ActionTarget.ENEMY_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getCunning()), new Tags[]{}),new Tags[]{},new ImageIcon("Texture/Items/attack_item/trap.png"),"trap","trap",ItemQuality.COMMON);
    private static final ActionItem bolas = new ActionItem(new ItemAction(DiceFactory.buildDice(new int[][]{{0},{0},{7,1},{7,1},{7,1},{7,2}}), ActionTarget.ENEMY_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getCunning()), new Tags[]{}),new Tags[]{},new ImageIcon("Texture/Items/attack_item/bolas.png"),"bolas","bolas",ItemQuality.COMMON);
    private static final ActionItem magic_sphere = new ActionItem(new ItemAction(DiceFactory.buildDice(new int[][]{{0},{4,4},{4,4},{4,4},{4,4},{4,4}}), ActionTarget.PLAYER_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getIntelligence()), new Tags[]{}),new Tags[]{},new ImageIcon("Texture/Items/attack_item/magic_sphere.png"),"magic sphere","magic sphere",ItemQuality.COMMON);
    private static final SpellItem ghost_sword = new SpellItem(new SpellAction(DiceFactory.buildDice(new int[][]{{0},{8,2,1},{8,2,1},{8,2,1},{8,4,1},{8,4,1}}), ActionTarget.PLAYER_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getIntelligence()),17,new Tags[]{Tags.MAGIC}), new Tags[]{},new ImageIcon("Texture/Items/spells/ghost_sword.png"),"ghost sword","ghost sword",ItemQuality.COMMON);
    private static final SpellItem thunder_ball = new SpellItem(new SpellAction(DiceFactory.buildDice(new int[][]{{0},{0},{0},{1,5},{1,11},{1,11}}), ActionTarget.ENEMY_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getIntelligence()),16,new Tags[]{Tags.MAGIC}), new Tags[]{},new ImageIcon("Texture/Items/spells/thunder_ball.png"),"thunder ball","thunder ball",ItemQuality.COMMON);
    private static final SpellItem toxic_flower = new SpellItem(new SpellAction(DiceFactory.buildDice(new int[][]{{0},{0},{0},{5,3},{5,3},{5,3}}), ActionTarget.ENEMY_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getIntelligence()),16,new Tags[]{Tags.MAGIC}), new Tags[]{},new ImageIcon("Texture/Items/spells/toxic_flower.png"),"toxic flower","toxic flower",ItemQuality.COMMON);


    //Levels
    public static final GameLevel DUNGEON = new GameLevel(new LootSettings(3,new double[]{0.7,0.3}),4,10,
            new WalkingSettings(0,2,100,100,1000,25,20,2,"Texture/MapTextures/dungeon/",false));
    public static final GameLevel MINE = new GameLevel(new LootSettings(5,new double[]{0.4,0.5,0.1}),8,20,
            new WalkingSettings(0,1,100,100,1000,25,20,2,"Texture/MapTextures/mine/",false));
    public static final GameLevel CAVE = new GameLevel(new LootSettings(7,new double[]{0.1,0.5,0.4}),12,30,
            new WalkingSettings(0,0,100,100,1000,25,20,2,"Texture/MapTextures/cave/",true));

    public static final ArrayList<GameLevel> LEVELS = new ArrayList<>(List.of(DUNGEON,MINE, CAVE));

    public static final ArrayList<PlayerCharacter> START_CHARACTER = new ArrayList<>(List.of(
            new PlayerCharacter(32,23,23,23,14,14,"Warrior",new ImageIcon("Texture/CharacterTexture/player.png"),new Tags[]{Tags.WARRIOR},
                    new ArrayList<>(List.of(sword, shield)),
                    new ArrayList<>(List.of(ghost_sword))),
            new PlayerCharacter(23,14,14,14,32,32,"Bandit",new ImageIcon("Texture/CharacterTexture/player.png"),new Tags[]{Tags.THIEF},
                    new ArrayList<>(List.of(bow, trap,bolas)),
                    new ArrayList<>()),
            new PlayerCharacter(14,14,32,23,23,23,"Warlock",new ImageIcon("Texture/CharacterTexture/player.png"),new Tags[]{Tags.MAGE},
                    new ArrayList<>(List.of(sword,magic_sphere)),
                    new ArrayList<>(List.of(thunder_ball,toxic_flower)))
    ));
}
