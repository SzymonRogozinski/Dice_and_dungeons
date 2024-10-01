package Game;

import Loot.LootSettings;
import Walking.WalkingSettings;

public class GameLevel {

    private final LootSettings lootSettings;
    private final int enemyStrength,minHP;
    private final WalkingSettings walkingSettings;


    public GameLevel(LootSettings lootSettings, int enemyStrength, int minHP,WalkingSettings walkingSettings) {
        this.lootSettings = lootSettings;
        this.enemyStrength = enemyStrength;
        this.minHP=minHP;
        this.walkingSettings=walkingSettings;
    }

    public LootSettings getLootSettings() {
        return lootSettings;
    }

    public int getEnemyStrength() {
        return enemyStrength;
    }

    public int getMinHP() {
        return minHP;
    }

    public WalkingSettings getWalkingSettings() {
        return walkingSettings;
    }
}
