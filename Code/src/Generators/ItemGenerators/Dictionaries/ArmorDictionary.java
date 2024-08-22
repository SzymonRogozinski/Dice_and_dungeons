package Generators.ItemGenerators.Dictionaries;

import Equipment.CharacterEquipment;
import Game.GameCollection;

import javax.swing.*;
import java.util.Map;

public class ArmorDictionary extends ItemDictionary {
    private static final Map<Integer,String[]> ARMOR_PART_NAMES = Map.of(
            CharacterEquipment.HEAD_ARMOR,new String[]{"hood","helmet", "hat"},
            CharacterEquipment.ARM_ARMOR,new String[]{"gauntlet", "glove","gantlet","armlet"},
            CharacterEquipment.CHEST_ARMOR,new String[]{"plate", "mail", "jacket", "cloak","brigandine","vest","cuirass"},
            CharacterEquipment.LEG_ARMOR,new String[]{"leg guards","boots","chausses"}
    );

    private static final Map<Integer, ImageIcon[]> ARMOR_PART_ICONS = Map.of(
            CharacterEquipment.HEAD_ARMOR,new ImageIcon[]{new ImageIcon("Texture/Items/helm-pl.png")},
            CharacterEquipment.ARM_ARMOR,new ImageIcon[]{new ImageIcon("Texture/Items/gauntlet-pl.png")},
            CharacterEquipment.CHEST_ARMOR,new ImageIcon[]{new ImageIcon("Texture/Items/chest-pl.png")},
            CharacterEquipment.LEG_ARMOR,new ImageIcon[]{new ImageIcon("Texture/Items/leg-pl.png")}
    );

    private static final String[] STATS_NAMES=new String[]{"strength","endurance","intelligence","charisma","cunning","luck"};

    public static String getArmorPartName(int armorPart){
        String[] names = ARMOR_PART_NAMES.get(armorPart);
        return names[GameCollection.random.nextInt(names.length)];
    }

    public static String getAdjectiveName(int statIndex){
        return STATS_NAMES[statIndex];
    }

    public static ImageIcon getArmorPartIcon(int armorPart){
        ImageIcon[] icons = ARMOR_PART_ICONS.get(armorPart);
        return icons[GameCollection.random.nextInt(icons.length)];
    }
}
