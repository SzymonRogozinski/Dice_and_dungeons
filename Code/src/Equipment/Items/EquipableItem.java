package Equipment.Items;

import Character.PlayerCharacter;

public interface EquipableItem {
    void equip(PlayerCharacter character);
    void deEquip(PlayerCharacter character);
}
