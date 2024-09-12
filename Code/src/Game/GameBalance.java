package Game;

import Loot.LootSettings;
import Walking.WalkingSettings;

import java.util.ArrayList;
import java.util.List;

public class GameBalance {
    public static final int REROLLS_DIVIDE=10;
    public static final int MIN_REROLLS=1;
    public static final int MAX_DICE=12;
    public static final int DICES_DIVIDE=6;
    public static final int MIN_DICES=3;
    public static final int MIN_STAT_VALUE=12;
    public static final int MAX_DICE_VALUE=20;
    public static final int DICE_BONUS_ADD_TO_STAT_MOD=5;
    public static final int DICE_BONUS_DIVIDE=12;
    public static final int MIN_DICE_BONUS_VALUE=2;
    //TODO return
    public static final int ENEMY_MINION_HP_MOD = 1;    //2
    public static final int ENEMY_STRONG_HP_MOD = 1;    //5
    public static final int ENEMY_BOSS_HP_MOD = 10;
    public static final int MAX_ENEMY_STAT=100;
    public static final int ENEMY_MIN_COST=1;

    //Levels
    public static final GameLevel DUNGEON = new GameLevel(new LootSettings(5,new double[]{0.7,0.3}),3,
            new WalkingSettings(1234,2,100,100,1000,25,20,2,"Texture/MapTextures/dungeon/"));
    public static final GameLevel MINE = new GameLevel(new LootSettings(8,new double[]{0.4,0.5,0.1}),6,
            new WalkingSettings(1234,1,100,100,1000,25,20,2,"Texture/MapTextures/mine/"));
    public static final GameLevel CAVE = new GameLevel(new LootSettings(8,new double[]{0.0,0.6,0.4}),9,
            new WalkingSettings(1234,0,100,100,1000,25,20,2,"Texture/MapTextures/cave/"));

    public static final ArrayList<GameLevel> LEVELS = new ArrayList<>(List.of(DUNGEON,MINE, CAVE));
}
