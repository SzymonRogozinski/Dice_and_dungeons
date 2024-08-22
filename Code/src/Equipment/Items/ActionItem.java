package Equipment.Items;

import Character.PlayerCharacter;
import Fight.GameActions.ItemAction;
import Game.Tags;

import javax.swing.*;

public class ActionItem extends Item implements EquipableItem{

    private final ItemAction action;

    public ActionItem(ItemAction action,Tags[] tags, ImageIcon icon,String name,ItemQuality quality) {
        super(tags,icon,name,quality);
        this.action=action;
    }

    public ItemAction getAction() {
        return action;
    }

    @Override
    public void equip(PlayerCharacter character) {}

    @Override
    public void deEquip(PlayerCharacter character) {
    }

    @Override
    public boolean canEquip(PlayerCharacter character) {
        for(Tags tag:tags){
            if(!character.haveTag(tag))
                return false;
        }
        return true;
    }
}
