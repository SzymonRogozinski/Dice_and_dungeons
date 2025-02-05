package Equipment.Items;

import Character.PlayerCharacter;
import Fight.GameActions.ItemAction;
import Game.Tags;

import javax.swing.*;

public class ActionItem extends Item implements EquippableItem {

    private final ItemAction action;
    private final String scaleAttribute;

    public ActionItem(ItemAction action, Tags[] tags, ImageIcon icon, String name, String shortName, ItemQuality quality, String scaleAttribute) {
        super(tags, icon, name, shortName, quality);
        this.action = action;
        this.scaleAttribute = scaleAttribute;
    }

    public ItemAction getAction() {
        return action;
    }

    public String getScaleAttribute() {
        return scaleAttribute;
    }

    @Override
    public void equip(PlayerCharacter character) {
    }

    @Override
    public void deEquip(PlayerCharacter character) {
    }

    @Override
    public boolean canEquip(PlayerCharacter character) {
        for (Tags tag : tags) {
            if (!character.haveTag(tag))
                return false;
        }
        return true;
    }
}
