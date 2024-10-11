package Generators.ItemGenerators;

import Dice.DiceAction.*;
import Equipment.Items.ItemQuality;
import Equipment.Items.UsableItem;
import Fight.ActionTarget;
import Fight.GameActions.UsableItemAction;
import Game.GameManager;
import Game.Tags;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class UsableItemGenerator {

    private static final int MIN_ITEMS_NUMBER = 3;
    private static final int MAX_ITEMS_NUMBER = 7;

    private static final UsableItem[] COMMONS = new UsableItem[]{
            new UsableItem(new UsableItemAction(ActionTarget.ENEMY_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new StunAction()})),new Tags[]{Tags.NO_ROLL}),1,new Tags[]{},new ImageIcon("Texture/Items/usable_item/rock.png"),"Rock","Rock",ItemQuality.COMMON),
            new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new HealAction(50,true)})),new Tags[]{Tags.NO_ROLL}),1,new Tags[]{Tags.USABLE_OUT_OF_FIGHT},new ImageIcon("Texture/Items/usable_item/heal_potion.png"),"health potion","health potion",ItemQuality.COMMON),
            new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new ManaAction(50)})),new Tags[]{Tags.NO_ROLL}),1,new Tags[]{Tags.USABLE_OUT_OF_FIGHT},new ImageIcon("Texture/Items/usable_item/mana_potion.png"),"mana potion","mana potion",ItemQuality.COMMON),
            new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new AttackBonusAction(1,true)})),new Tags[]{Tags.NO_ROLL,Tags.FREE_ACTION}),1,new Tags[]{},new ImageIcon("Texture/Items/usable_item/powerup.png"),"power up","power up",ItemQuality.COMMON),
            new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new DefenseBonusAction(1,true)})),new Tags[]{Tags.NO_ROLL,Tags.FREE_ACTION}),1,new Tags[]{},new ImageIcon("Texture/Items/usable_item/defense_potion.png"),"defense potion","defense potion",ItemQuality.COMMON),
            new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new MagicBonusAction(1,true)})),new Tags[]{Tags.NO_ROLL,Tags.FREE_ACTION}),1,new Tags[]{},new ImageIcon("Texture/Items/usable_item/magic_powder.png"),"magic powder","magic powder",ItemQuality.COMMON)
    };

    private static final UsableItem[] RARES = new UsableItem[]{
            new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new HealAction(100,true)})),new Tags[]{Tags.NO_ROLL}),1,new Tags[]{Tags.USABLE_OUT_OF_FIGHT},new ImageIcon("Texture/Items/usable_item/heal_potion.png"),"bigger health potion","health potion",ItemQuality.RARE),
            new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new ManaAction(100)})),new Tags[]{Tags.NO_ROLL}),1,new Tags[]{Tags.USABLE_OUT_OF_FIGHT},new ImageIcon("Texture/Items/usable_item/mana_potion.png"),"bigger mana potion","mana potion",ItemQuality.RARE),
            new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new AttackBonusAction(2,true)})),new Tags[]{Tags.NO_ROLL,Tags.FREE_ACTION}),1,new Tags[]{},new ImageIcon("Texture/Items/usable_item/powerup.png"),"stronger power up","power up",ItemQuality.RARE),
            new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new DefenseBonusAction(2,true)})),new Tags[]{Tags.NO_ROLL,Tags.FREE_ACTION}),1,new Tags[]{},new ImageIcon("Texture/Items/usable_item/defense_potion.png"),"better defense potion","defense potion",ItemQuality.RARE),
            new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new MagicBonusAction(2,true)})),new Tags[]{Tags.NO_ROLL,Tags.FREE_ACTION}),1,new Tags[]{},new ImageIcon("Texture/Items/usable_item/magic_powder.png"),"ancient magic powder","magic powder",ItemQuality.RARE)
    };

    private static final UsableItem[] LEGENDS = new UsableItem[]{
            new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new HealAction(200,true)})),new Tags[]{Tags.NO_ROLL}),1,new Tags[]{Tags.USABLE_OUT_OF_FIGHT},new ImageIcon("Texture/Items/usable_item/heal_potion.png"),"biggest health potion","health potion",ItemQuality.LEGENDARY),
            new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new ManaAction(200)})),new Tags[]{Tags.NO_ROLL}),1,new Tags[]{Tags.USABLE_OUT_OF_FIGHT},new ImageIcon("Texture/Items/usable_item/mana_potion.png"),"biggest mana potion","mana potion",ItemQuality.LEGENDARY),
            new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new AttackBonusAction(3,true)})),new Tags[]{Tags.NO_ROLL,Tags.FREE_ACTION}),1,new Tags[]{},new ImageIcon("Texture/Items/usable_item/powerup.png"),"strongest power up","power up",ItemQuality.LEGENDARY),
            new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new DefenseBonusAction(3,true)})),new Tags[]{Tags.NO_ROLL,Tags.FREE_ACTION}),1,new Tags[]{},new ImageIcon("Texture/Items/usable_item/defense_potion.png"),"the best defense potion","defense potion",ItemQuality.LEGENDARY),
            new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new MagicBonusAction(3,true)})),new Tags[]{Tags.NO_ROLL,Tags.FREE_ACTION}),1,new Tags[]{},new ImageIcon("Texture/Items/usable_item/magic_powder.png"),"eldritch magic powder","magic powder",ItemQuality.LEGENDARY)
    };

    public static UsableItem generate(ItemQuality quality){
        int count = GameManager.getRandom().nextInt(MIN_ITEMS_NUMBER,MAX_ITEMS_NUMBER+1);
        UsableItem uItem;
        switch (quality){
            case COMMON -> uItem = COMMONS[GameManager.getRandom().nextInt(COMMONS.length)];
            case RARE -> uItem = RARES[GameManager.getRandom().nextInt(RARES.length)];
            case LEGENDARY -> uItem = LEGENDS[GameManager.getRandom().nextInt(LEGENDS.length)];
            default -> throw new RuntimeException("Quality not implemented!");
        }
        return multiple(uItem,count);
    }

    private static UsableItem multiple(UsableItem item, int count){
        return new UsableItem(item.getAction(),count,item.tags,item.getIcon(),item.name, item.shortName, item.getQuality());
    }
}
