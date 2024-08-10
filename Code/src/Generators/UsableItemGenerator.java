package Generators;

import Dice.DiceAction.*;
import Equipment.Items.ItemQuality;
import Equipment.Items.UsableItem;
import Fight.ActionTarget;
import Fight.GameActions.UsableItemAction;
import Game.GameCollection;
import Game.Tags;

import java.util.ArrayList;
import java.util.List;

public class UsableItemGenerator {

    private static final int MIN_ITEMS_NUMBER = 3;
    private static final int MAX_ITEMS_NUMBER = 7;

    private static final UsableItem[] COMMONS = new UsableItem[]{
            new UsableItem(new UsableItemAction(ActionTarget.ENEMY_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new StunAction()})),new Tags[]{Tags.NO_ROLL}),1,new Tags[]{},null,"Rock",ItemQuality.COMMON),
            new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new HealAction(50,true)})),new Tags[]{Tags.NO_ROLL}),1,new Tags[]{Tags.USABLE_OUT_OF_FIGHT},null,"Health potion",ItemQuality.COMMON),
            new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new ManaAction(50)})),new Tags[]{Tags.NO_ROLL}),1,new Tags[]{Tags.USABLE_OUT_OF_FIGHT},null,"Mana potion",ItemQuality.COMMON),
            new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new AttackBonusAction(1,true)})),new Tags[]{Tags.NO_ROLL,Tags.FREE_ACTION}),1,new Tags[]{},null,"Power up",ItemQuality.COMMON),
            new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new DefenseBonusAction(1,true)})),new Tags[]{Tags.NO_ROLL,Tags.FREE_ACTION}),1,new Tags[]{},null,"Magic powder",ItemQuality.COMMON),
            new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new MagicBonusAction(1,true)})),new Tags[]{Tags.NO_ROLL,Tags.FREE_ACTION}),1,new Tags[]{},null,"Defense potion",ItemQuality.COMMON)
    };

    private static final UsableItem[] RARES = new UsableItem[]{
            new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new HealAction(150,true)})),new Tags[]{Tags.NO_ROLL}),1,new Tags[]{Tags.USABLE_OUT_OF_FIGHT},null,"Bigger health potion",ItemQuality.RARE),
            new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new ManaAction(150)})),new Tags[]{Tags.NO_ROLL}),1,new Tags[]{Tags.USABLE_OUT_OF_FIGHT},null,"Bigger mana potion",ItemQuality.RARE),
            new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new AttackBonusAction(2,true)})),new Tags[]{Tags.NO_ROLL,Tags.FREE_ACTION}),1,new Tags[]{},null,"Stronger power up",ItemQuality.RARE),
            new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new DefenseBonusAction(2,true)})),new Tags[]{Tags.NO_ROLL,Tags.FREE_ACTION}),1,new Tags[]{},null,"Ancient magic powder",ItemQuality.RARE),
            new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new MagicBonusAction(2,true)})),new Tags[]{Tags.NO_ROLL,Tags.FREE_ACTION}),1,new Tags[]{},null,"Better defense potion",ItemQuality.RARE)
    };

    private static final UsableItem[] LEGENDS = new UsableItem[]{
            new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new HealAction(300,true)})),new Tags[]{Tags.NO_ROLL}),1,new Tags[]{Tags.USABLE_OUT_OF_FIGHT},null,"Biggest health potion",ItemQuality.LEGENDARY),
            new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new ManaAction(300)})),new Tags[]{Tags.NO_ROLL}),1,new Tags[]{Tags.USABLE_OUT_OF_FIGHT},null,"Biggest mana potion",ItemQuality.LEGENDARY),
            new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new AttackBonusAction(3,true)})),new Tags[]{Tags.NO_ROLL,Tags.FREE_ACTION}),1,new Tags[]{},null,"Strongest power up",ItemQuality.LEGENDARY),
            new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new DefenseBonusAction(3,true)})),new Tags[]{Tags.NO_ROLL,Tags.FREE_ACTION}),1,new Tags[]{},null,"Eldritch magic powder",ItemQuality.LEGENDARY),
            new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new MagicBonusAction(3,true)})),new Tags[]{Tags.NO_ROLL,Tags.FREE_ACTION}),1,new Tags[]{},null,"The best defense potion",ItemQuality.LEGENDARY)
    };

    public static UsableItem generate(ItemQuality quality){
        int count = GameCollection.random.nextInt(MIN_ITEMS_NUMBER,MAX_ITEMS_NUMBER+1);
        UsableItem uItem;
        switch (quality){
            case COMMON -> uItem = COMMONS[GameCollection.random.nextInt(COMMONS.length)];
            case RARE -> uItem = RARES[GameCollection.random.nextInt(RARES.length)];
            case LEGENDARY -> uItem = LEGENDS[GameCollection.random.nextInt(LEGENDS.length)];
            default -> throw new RuntimeException("Quality not implemented!");
        }
        return multiple(uItem,count);
    }

    private static UsableItem multiple(UsableItem item, int count){
        return new UsableItem(item.getAction(),count,item.tags,item.getIcon(),item.name,item.getQuality());
    }
}
