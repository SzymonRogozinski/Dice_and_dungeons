package Equipment.Items;

import Game.Tags;

public class GoldPile extends Item{

    public GoldPile(int gold) {
        super(new Tags[]{}, null, STR."Gold \{gold}", "Gold", ItemQuality.COMMON, gold);
    }
}
