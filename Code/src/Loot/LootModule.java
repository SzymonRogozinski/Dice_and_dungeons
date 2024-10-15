package Loot;

import Equipment.Items.Item;
import Equipment.Items.ItemQuality;
import Game.GameManager;
import Game.PlayerInfo;
import Generators.ItemGenerators.ArmorGenerator;
import Generators.ItemGenerators.DiceItemGenerator;
import Generators.ItemGenerators.SpellItemGenerator;
import Generators.ItemGenerators.UsableItemGenerator;

import java.util.ArrayList;

public class LootModule {

    private final static double USABLE_PROB = 0.4;
    private final static double ARMOR_PROB = 0.4;
    private final static double DICE_PROB = 0.8;
    private final static int COMMON_COST = 1;
    private final static int RARE_COST = 2;
    private final static int LEGENDARY_COST = 3;

    //Logger
    private static final int TICKS_COUNT = 40;
    private String lootLogText = "";
    private int logCounter = -1;

    public void getLoot(LootSettings settings, boolean wasFight) {
        int points = settings.getPoints();
        ArrayList<Item> loot = new ArrayList<>();
        while (points > 0) {
            int q = settings.getQualityId();
            if (q <= points) {
                ItemQuality quality = getQuality(q);
                loot.add(generateLoot(quality));
                points -= q;
            }
        }
        setLootLogText(loot, wasFight);
        //Place in backpack
        for (Item item : loot)
            PlayerInfo.getParty().getBackpack().putToBackpack(item);
    }

    public String getLootLogText() {
        logCounter--;
        if (logCounter == 0)
            lootLogText = "";
        return lootLogText;
    }


    private void setLootLogText(ArrayList<Item> loot, boolean wasFight) {
        logCounter = TICKS_COUNT * GameManager.getWalkingManager().getWalking().getEnemyCount();
        StringBuilder builder = new StringBuilder(wasFight ? "Enemies drop: " : "You found in chest: ");
        for (Item item : loot)
            builder.append(item.name).append(", ");
        builder.setLength(builder.length() - 2);
        builder.append('.');
        lootLogText = builder.toString();
    }

    private ItemQuality getQuality(int quality) {
        switch (quality) {
            case COMMON_COST -> {
                return ItemQuality.COMMON;
            }
            case RARE_COST -> {
                return ItemQuality.RARE;
            }
            case LEGENDARY_COST -> {
                return ItemQuality.LEGENDARY;
            }
            default -> throw new RuntimeException("Got unexpected quality id!");
        }
    }

    private Item generateLoot(ItemQuality quality) {
        double roll = GameManager.getRandom().nextDouble();
        if (roll <= USABLE_PROB)
            return UsableItemGenerator.generate(quality);
        else {
            roll = GameManager.getRandom().nextDouble();
            if (ARMOR_PROB >= roll)
                return ArmorGenerator.generateArmor(quality);
            else if (DICE_PROB >= roll)
                return DiceItemGenerator.generateItem(quality);
            else
                return SpellItemGenerator.generateItem(quality);
        }
    }
}
