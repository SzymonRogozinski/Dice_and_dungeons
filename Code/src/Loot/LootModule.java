package Loot;

import Equipment.Items.Item;
import Equipment.Items.ItemQuality;
import Equipment.Items.UsableItem;
import Game.GameCollection;
import Generators.ItemGenerators.ArmorGenerator;
import Generators.ItemGenerators.DiceItemGenerator;
import Generators.ItemGenerators.SpellItemGenerator;
import Generators.ItemGenerators.UsableItemGenerator;

import java.util.ArrayList;

public class LootModule {

    private final static double USABLE_PROB = 0.4;
    private final static double ARMOR_PROB = 0.4;
    private final static double DICE_PROB = 0.8;
    private final static int COMMON_COST=1;
    private final static int RARE_COST=2;
    private final static int LEGENDARY_COST=3;

    public void getLoot(LootSettings settings){
        int points = settings.getPoints();
        ArrayList<Item> loot=new ArrayList<>();
        while(points>0){
            int q = settings.getQualityId();
            if(q<=points){
                ItemQuality quality = getQuality(q);
                loot.add(generateLoot(quality));
                points-=q;
            }
        }
        //TODO popup

        //Place in backpack
        for(Item item:loot) {
            GameCollection.getParty().getBackpack().putToBackpack(item);
//            System.out.println("Name: "+item.name);
//            System.out.println("Quality: "+item.getQuality());
//            if(item instanceof UsableItem usableItem)
//                System.out.println("Uses: "+usableItem.getNumberOfItems());
//            System.out.println();
        }
    }

    private ItemQuality getQuality(int quality){
        switch (quality){
            case COMMON_COST -> { return ItemQuality.COMMON;}
            case RARE_COST -> { return ItemQuality.RARE;}
            case LEGENDARY_COST -> { return ItemQuality.LEGENDARY;}
            default -> throw new RuntimeException("Got unexpected quality id!");
        }
    }

    private Item generateLoot(ItemQuality quality){
        double roll = GameCollection.random.nextDouble();
        if(roll<=USABLE_PROB)
            return UsableItemGenerator.generate(quality);
        else{
            roll = GameCollection.random.nextDouble();
            if(ARMOR_PROB>=roll)
                return ArmorGenerator.generateArmor(quality);
            else if(DICE_PROB>=roll)
                return DiceItemGenerator.generateItem(quality);
            else
                return SpellItemGenerator.generateItem(quality);
        }
    }
}
