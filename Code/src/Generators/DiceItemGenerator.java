package Generators;

import Equipment.Items.ActionItem;
import Equipment.Items.ItemQuality;
import Fight.GameActions.ItemAction;
import Game.GameCollection;
import Game.Tags;

import javax.swing.*;

public class DiceItemGenerator extends Generator{

    public static ActionItem generateItem(ItemQuality quality){
        if (quality==null)
            throw new IllegalArgumentException("Quality of item cannot be null!");

        int points;
        Tags tag=null;
        String name="";

        switch (quality){
            case COMMON -> points = getPoints(GeneratorConst.MEDIUM_POINTS*GeneratorConst.COMMON_MOD);
            case RARE -> {
                points = getPoints(GeneratorConst.MEDIUM_POINTS*GeneratorConst.RARE_MOD);
                //Add tag
                if (GameCollection.random.nextBoolean()) {
                    tag = getRandomTag();
                    points += GeneratorConst.TAG_BONUS*GeneratorConst.RARE_MOD;
                    name = ArmorDictionary.getNameFromTag(tag)+" "+name;
                }
            }
            case LEGENDARY -> {
                points = getPoints(GeneratorConst.MEDIUM_POINTS*GeneratorConst.LEGENDARY_MOD)+GeneratorConst.TAG_BONUS*GeneratorConst.LEGENDARY_MOD;
                tag = getRandomTag();
                name = ArmorDictionary.getNameFromTag(tag)+" "+name;
            }
            default -> {
                throw new RuntimeException("Quality not implemented");
            }
        }


        ItemAction action=null;
        ImageIcon icon = null; //TODO
        Tags [] tags = tag==null ? new Tags[]{}:new Tags[]{tag};
        return new ActionItem(action,tags,icon,name,quality);
    }
}
