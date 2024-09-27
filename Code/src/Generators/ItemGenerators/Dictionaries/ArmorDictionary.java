package Generators.ItemGenerators.Dictionaries;

import Equipment.CharacterEquipment;
import Game.GameManager;

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
            CharacterEquipment.HEAD_ARMOR,new ImageIcon[]{new ImageIcon("Texture/Items/armors/helm-pl.png")},
            CharacterEquipment.ARM_ARMOR,new ImageIcon[]{new ImageIcon("Texture/Items/armors/gauntlet-pl.png")},
            CharacterEquipment.CHEST_ARMOR,new ImageIcon[]{new ImageIcon("Texture/Items/armors/chest-pl.png")},
            CharacterEquipment.LEG_ARMOR,new ImageIcon[]{new ImageIcon("Texture/Items/armors/leg-pl.png")}
    );

    private static final String[] STATS_NAMES=new String[]{"strength","endurance","intelligence","charisma","cunning","luck"};

    public static String getArmorPartName(int armorPart){
        String[] names = ARMOR_PART_NAMES.get(armorPart);
        return names[GameManager.random.nextInt(names.length)];
    }

    public static String getAdjectiveName(int statIndex){
        return STATS_NAMES[statIndex];
    }

    public static ImageIcon getArmorPartIcon(int armorPart){
        ImageIcon[] icons = ARMOR_PART_ICONS.get(armorPart);
        return icons[GameManager.random.nextInt(icons.length)];
    }
}
