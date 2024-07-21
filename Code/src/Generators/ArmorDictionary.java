package Generators;

import Equipment.CharacterEquipment;
import Game.GameCollection;
import Game.Tags;

import java.util.Map;

public class ArmorDictionary {

    private static final Map<Tags,String[]> CLASS_TAGS = Map.of(
            Tags.WARRIOR,new String[]{"warrior","mighty","courage"},
            Tags.MAGE,new String[]{"mage","magic","full of energy"},
            Tags.THIEF,new String[]{"rouge", "sly","shifty"}
    );
    private static final Map<Integer,String[]> ARMOR_PART_NAMES = Map.of(
            CharacterEquipment.HEAD_ARMOR,new String[]{"hood","helmet", "hat"},
            CharacterEquipment.ARM_ARMOR,new String[]{"gauntlet", "glove","gantlet","armlet"},
            CharacterEquipment.CHEST_ARMOR,new String[]{"plate", "mail", "jacket", "cloak","brigandine","vest","cuirass"},
            CharacterEquipment.LEG_ARMOR,new String[]{"leg guards","boots","chausses"}
    );

    private static final String[] STATS_NAMES=new String[]{"strength","endurance","intelligence","charisma","cunning","luck"};


    public static String getArmorPartName(int armorPart){
        String[] names = ARMOR_PART_NAMES.get(armorPart);
        return names[GameCollection.random.nextInt(names.length)];
    }

    public static String getNameFromTag(Tags tag){
        String[] names = CLASS_TAGS.get(tag);
        if(names==null)
            throw new RuntimeException("Tag "+tag+" has no name!");
        return names[GameCollection.random.nextInt(names.length)];
    }

    public static String getAdjectiveName(int statIndex){
        return STATS_NAMES[statIndex];
    }
}
