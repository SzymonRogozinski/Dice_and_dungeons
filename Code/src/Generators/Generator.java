package Generators;

import Game.GameManager;
import Game.Tags;

public class Generator {

    private static final Tags[] classTags = new Tags[]{Tags.WARRIOR, Tags.THIEF, Tags.MAGE};

    protected static Tags getRandomTag() {
        return classTags[GameManager.getRandom().nextInt(classTags.length)];
    }

    protected static int getPoints(int medium) {
        int variance = (int) (medium * GeneratorConst.VARIANCE);
        return GameManager.getRandom().nextInt(medium - variance, medium + variance + 1);

    }
}
