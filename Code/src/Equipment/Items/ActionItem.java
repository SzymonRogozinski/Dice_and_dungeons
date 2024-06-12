package Equipment.Items;

import Character.PlayerCharacter;
import Fight.GameActions.ItemAction;
import Game.Tags;

import javax.swing.*;

public class ActionItem extends Item implements EquipableItem{

    private final ItemAction action;

    public ActionItem(ItemAction action,Tags[] tags, ImageIcon icon,String name) {
        super(tags,icon,name);
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
}
