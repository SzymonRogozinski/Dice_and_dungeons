package Generators;

import Dice.DiceAction.*;
import Equipment.Items.ItemQuality;
import Equipment.Items.UsableItem;
import Fight.ActionTarget;
import Fight.GameActions.UsableItemAction;
import Game.GameCollection;
import Game.Tags;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class UsableItemGenerator {

    private static final int MIN_ITEMS_NUMBER = 3;
    private static final int MAX_ITEMS_NUMBER = 7;

    private static final UsableItem[] COMMONS = new UsableItem[]{
            new UsableItem(new UsableItemAction(ActionTarget.ENEMY_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new StunAction()})),new Tags[]{Tags.NO_ROLL}),1,new Tags[]{},new ImageIcon("Texture/Items/rock.png"),"Rock",ItemQuality.COMMON),
            new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new HealAction(50,true)})),new Tags[]{Tags.NO_ROLL}),1,new Tags[]{Tags.USABLE_OUT_OF_FIGHT},new ImageIcon("Texture/Items/heal_potion.png"),"Health potion",ItemQuality.COMMON),
            new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new ManaAction(50)})),new Tags[]{Tags.NO_ROLL}),1,new Tags[]{Tags.USABLE_OUT_OF_FIGHT},new ImageIcon("Texture/Items/mana_potion.png"),"Mana potion",ItemQuality.COMMON),
            new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new AttackBonusAction(1,true)})),new Tags[]{Tags.NO_ROLL,Tags.FREE_ACTION}),1,new Tags[]{},new ImageIcon("Texture/Items/powerup.png"),"Power up",ItemQuality.COMMON),
            new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new DefenseBonusAction(1,true)})),new Tags[]{Tags.NO_ROLL,Tags.FREE_ACTION}),1,new Tags[]{},new ImageIcon("Texture/Items/magic_powder.png"),"Magic powder",ItemQuality.COMMON),
            new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new MagicBonusAction(1,true)})),new Tags[]{Tags.NO_ROLL,Tags.FREE_ACTION}),1,new Tags[]{},new ImageIcon("Texture/Items/defense_potion.png"),"Defense potion",ItemQuality.COMMON)
    };

    private static final UsableItem[] RARES = new UsableItem[]{
            new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new HealAction(150,true)})),new Tags[]{Tags.NO_ROLL}),1,new Tags[]{Tags.USABLE_OUT_OF_FIGHT},new ImageIcon("Texture/Items/heal_potion.png"),"Bigger health potion",ItemQuality.RARE),
            new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new ManaAction(150)})),new Tags[]{Tags.NO_ROLL}),1,new Tags[]{Tags.USABLE_OUT_OF_FIGHT},new ImageIcon("Texture/Items/mana_potion.png"),"Bigger mana potion",ItemQuality.RARE),
            new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new AttackBonusAction(2,true)})),new Tags[]{Tags.NO_ROLL,Tags.FREE_ACTION}),1,new Tags[]{},new ImageIcon("Texture/Items/powerup.png"),"Stronger power up",ItemQuality.RARE),
            new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new DefenseBonusAction(2,true)})),new Tags[]{Tags.NO_ROLL,Tags.FREE_ACTION}),1,new Tags[]{},new ImageIcon("Texture/Items/magic_powder.png"),"Ancient magic powder",ItemQuality.RARE),
            new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new MagicBonusAction(2,true)})),new Tags[]{Tags.NO_ROLL,Tags.FREE_ACTION}),1,new Tags[]{},new ImageIcon("Texture/Items/defense_potion.png"),"Better defense potion",ItemQuality.RARE)
    };

    private static final UsableItem[] LEGENDS = new UsableItem[]{
            new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new HealAction(300,true)})),new Tags[]{Tags.NO_ROLL}),1,new Tags[]{Tags.USABLE_OUT_OF_FIGHT},new ImageIcon("Texture/Items/heal_potion.png"),"Biggest health potion",ItemQuality.LEGENDARY),
            new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new ManaAction(300)})),new Tags[]{Tags.NO_ROLL}),1,new Tags[]{Tags.USABLE_OUT_OF_FIGHT},new ImageIcon("Texture/Items/mana_potion.png"),"Biggest mana potion",ItemQuality.LEGENDARY),
            new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new AttackBonusAction(3,true)})),new Tags[]{Tags.NO_ROLL,Tags.FREE_ACTION}),1,new Tags[]{},new ImageIcon("Texture/Items/powerup.png"),"Strongest power up",ItemQuality.LEGENDARY),
            new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new DefenseBonusAction(3,true)})),new Tags[]{Tags.NO_ROLL,Tags.FREE_ACTION}),1,new Tags[]{},new ImageIcon("Texture/Items/magic_powder.png"),"Eldritch magic powder",ItemQuality.LEGENDARY),
            new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new MagicBonusAction(3,true)})),new Tags[]{Tags.NO_ROLL,Tags.FREE_ACTION}),1,new Tags[]{},new ImageIcon("Texture/Items/defense_potion.png"),"The best defense potion",ItemQuality.LEGENDARY)
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
