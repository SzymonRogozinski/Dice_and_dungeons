package Walking;

/**
 * @param seed 0-> No predefine
 */
public record WalkingSettings(int seed, int algGen, int width, int height, int size, int enemies, int treasures,
                              int vaults, String path, boolean bossLevel) { }
