package Game;

import Loot.LootSettings;
import Walking.WalkingSettings;

public record GameLevel(LootSettings lootSettings, int enemyStrength, int minHP, WalkingSettings walkingSettings) {
}
