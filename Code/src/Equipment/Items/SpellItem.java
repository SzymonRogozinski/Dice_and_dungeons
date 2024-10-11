package Equipment.Items;

import Character.PlayerCharacter;
import Fight.GameActions.SpellAction;
import Game.Tags;

import javax.swing.*;

public class SpellItem extends Item  implements EquippableItem {

    private final SpellAction action;

    public SpellItem(SpellAction action,Tags[] tags, ImageIcon icon,String name,String shortName,ItemQuality quality) {
        super(tags,icon,name,shortName,quality);
        this.action=action;
    }

    public SpellAction getAction() {
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
