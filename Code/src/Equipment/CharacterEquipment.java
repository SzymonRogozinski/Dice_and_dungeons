package Equipment;

import Character.PlayerCharacter;
import Equipment.Items.*;

import java.util.ArrayList;

public class CharacterEquipment {
    public final static int HEAD_ARMOR = 0;
    public final static int ARM_ARMOR = 1;
    public final static int CHEST_ARMOR = 2;
    public final static int LEG_ARMOR = 3;

    public final static int ACTION_SLOT = 10;
    public final static int SPELL_SLOT = 11;
    public final static int ARMOR_SLOT = 12;
    public final static int BAG_SLOT = 13;
    public final static int USE_SLOT = 14;

    private final ActionItem[] actionItems;
    private final SpellItem[] spellItems;
    private final ArmorItem[] armorItems;

    private final PlayerCharacter player;

    public CharacterEquipment(PlayerCharacter player) {
        actionItems = new ActionItem[3];
        spellItems = new SpellItem[3];
        armorItems = new ArmorItem[4];
        this.player = player;
    }

    public ActionItem[] getActionItems() {
        return actionItems;
    }

    public SpellItem[] getSpellItems() {
        return spellItems;
    }

    public ArmorItem[] getArmorItems() {
        return armorItems;
    }

    public ArrayList<ActionItem> getNotNullActionItems() {
        ArrayList<ActionItem> actionItemList = new ArrayList<>();
        for (ActionItem item : actionItems)
            if (item != null)
                actionItemList.add(item);
        return actionItemList;
    }

    public ArrayList<SpellItem> getNotNullSpellItems() {
        ArrayList<SpellItem> spellItemList = new ArrayList<>();
        for (SpellItem item : spellItems)
            if (item != null)
                spellItemList.add(item);
        return spellItemList;
    }

    public boolean equip(Item item, int slot, int slotType) {
        if (!(item instanceof EquippableItem eItem) || !eItem.canEquip(player))
            return false;

        if (item instanceof ActionItem aItem && slotType == ACTION_SLOT) {
            if (actionItems[slot] != null) {
                ActionItem removeItem = actionItems[slot];
                removeItem.deEquip(player);
                actionItems[slot] = null;
            }
            actionItems[slot] = aItem;
            aItem.equip(player);
        } else if (item instanceof SpellItem sItem && slotType == SPELL_SLOT) {
            if (spellItems[slot] != null) {
                SpellItem removeItem = spellItems[slot];
                removeItem.deEquip(player);
                spellItems[slot] = null;
            }
            spellItems[slot] = sItem;
            sItem.equip(player);
        } else if (item instanceof ArmorItem arItem && slotType == ARMOR_SLOT && arItem.getBodyPart() == slot) {
            if (armorItems[slot] != null) {
                ArmorItem removeItem = armorItems[slot];
                removeItem.deEquip(player);
                armorItems[slot] = null;
            }
            armorItems[slot] = arItem;
            arItem.equip(player);
        } else
            return false;
        return true;
    }

    public void deEquip(int slot, int slotType) {
        if (slotType == ACTION_SLOT && actionItems[slot] != null) {
            ActionItem aItem = actionItems[slot];
            aItem.deEquip(player);
            actionItems[slot] = null;
        } else if (slotType == SPELL_SLOT && spellItems[slot] != null) {
            SpellItem sItem = spellItems[slot];
            sItem.deEquip(player);
            spellItems[slot] = null;
        } else if (slotType == ARMOR_SLOT && armorItems[slot] != null) {
            ArmorItem arItem = armorItems[slot];
            arItem.deEquip(player);
            armorItems[slot] = null;
        }
    }
}
