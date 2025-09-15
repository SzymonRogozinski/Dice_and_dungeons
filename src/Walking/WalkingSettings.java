package Walking;

import Equipment.Items.ItemQuality;

/**
 * @param seed 0-> No predefine
 */
public record WalkingSettings(int seed, int algGen, int width, int height, int size, int enemies, int treasures, int quests,
                              int vaults, ItemQuality traderQuality, int traderItemCount, String path, boolean bossLevel) {
}
