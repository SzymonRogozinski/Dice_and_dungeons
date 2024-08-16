package Game;

import Loot.LootSettings;

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

    //Levels
    public static final GameLevel DUNGEON = new GameLevel(new LootSettings(5,new double[]{0.7,0.3}));
    public static final GameLevel MINE = new GameLevel(new LootSettings(8,new double[]{0.4,0.5,0.1}));
}
