package Equipment.Items;

import Character.PlayerCharacter;
import Game.Tags;

import javax.swing.*;

public class ArmorItem extends Item implements EquipableItem{

    private final int strength,endurance,intelligence,charisma,cunning,luck,bodyPart;

    public ArmorItem(int strength, int endurance, int intelligence, int charisma, int cunning, int luck,int bodyPart,Tags[] tags, ImageIcon icon,String name,ItemQuality quality) {
        super(tags,icon,name,quality);
        this.strength=strength;
        this.endurance=endurance;
        this.intelligence=intelligence;
        this.charisma=charisma;
        this.cunning=cunning;
        this.luck=luck;
        this.bodyPart=bodyPart;
    }

    @Override
    public void equip(PlayerCharacter character) {
        character.modifyStats(strength,endurance,intelligence,charisma,cunning,luck);
        character.recalculateStats();
    }

    @Override
    public void deEquip(PlayerCharacter character) {
        character.modifyStats(-strength,-endurance,-intelligence,-charisma,-cunning,-luck);
        character.recalculateStats();
    }

    @Override
    public boolean canEquip(PlayerCharacter character){
        for(Tags tag:tags){
            if(!character.haveTag(tag))
                return false;
        }
        return true;
    }

    public int[] getStats(){
        return new int[]{strength,endurance,intelligence,charisma,cunning,luck};
    }

    public int getBodyPart() {
        return bodyPart;
    }
}
