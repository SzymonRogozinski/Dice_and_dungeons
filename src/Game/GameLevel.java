package Game;

import Loot.LootSettings;
import Walking.WalkingSettings;

public record GameLevel(LootSettings lootSettings, LootSettings questLoot, int enemyStrength, int minHP, WalkingSettings walkingSettings) {
}
