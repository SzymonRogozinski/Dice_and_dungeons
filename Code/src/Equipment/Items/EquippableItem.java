package Equipment.Items;

import Character.PlayerCharacter;

public interface EquippableItem {
    void equip(PlayerCharacter character);
    void deEquip(PlayerCharacter character);
    default boolean canEquip(PlayerCharacter character){
        return true;
    }
}
