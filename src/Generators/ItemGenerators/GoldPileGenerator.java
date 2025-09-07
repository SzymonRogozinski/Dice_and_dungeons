package Generators.ItemGenerators;

import Equipment.Items.GoldPile;
import Equipment.Items.ItemCostMod;
import Equipment.Items.ItemQuality;
import Generators.Generator;
import Generators.GeneratorConst;

public class GoldPileGenerator extends Generator {

    public static GoldPile generateGoldPile(ItemQuality quality){
        int points;
        switch (quality){
            case COMMON -> points = getPoints(GeneratorConst.MEDIUM_POINTS * GeneratorConst.COMMON_MOD) * ItemCostMod.COMMON_COST_MOD;
            case RARE -> points = getPoints(GeneratorConst.MEDIUM_POINTS * GeneratorConst.RARE_MOD) * ItemCostMod.RARE_COST_MOD;
            case LEGENDARY -> points = getPoints(GeneratorConst.MEDIUM_POINTS * GeneratorConst.LEGENDARY_MOD) * ItemCostMod.Legendary_COST_MOD;
            case null, default -> throw new RuntimeException("Quality not implemented");
        }
        return new GoldPile(points);
    }
}
