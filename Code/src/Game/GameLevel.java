package Game;

import Loot.LootSettings;
import Walking.WalkingSettings;

public class GameLevel {

    private final LootSettings lootSettings;
    private final int enemyStrength;
    private final WalkingSettings walkingSettings;


    public GameLevel(LootSettings lootSettings, int enemyStrength,WalkingSettings walkingSettings) {
        this.lootSettings = lootSettings;
        this.enemyStrength = enemyStrength;
        this.walkingSettings=walkingSettings;
    }

    public LootSettings getLootSettings() {
        return lootSettings;
    }

    public int getEnemyStrength() {
        return enemyStrength;
    }

    public WalkingSettings getWalkingSettings() {
        return walkingSettings;
    }
}
