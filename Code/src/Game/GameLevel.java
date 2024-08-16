package Game;

import Loot.LootSettings;

public class GameLevel {

    private final LootSettings lootSettings;

    public GameLevel(LootSettings lootSettings) {
        this.lootSettings = lootSettings;
    }

    public LootSettings getLootSettings() {
        return lootSettings;
    }
}
