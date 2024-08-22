package Game;

import Loot.LootSettings;

public class GameLevel {

    private final LootSettings lootSettings;
    private final int enemyStrength;

    public GameLevel(LootSettings lootSettings, int enemyStrength) {
        this.lootSettings = lootSettings;
        this.enemyStrength = enemyStrength;
    }

    public LootSettings getLootSettings() {
        return lootSettings;
    }

    public int getEnemyStrength() {
        return enemyStrength;
    }
}
