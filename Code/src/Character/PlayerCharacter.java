package Character;

import Equipment.CharacterEquipment;
import Fight.GameActions.ItemAction;
import Fight.GameActions.SpellAction;
import Fight.GameActions.UsableItemAction;

import javax.swing.*;
import java.util.ArrayList;

public class PlayerCharacter extends GameCharacter{
    private PlayerParty party;
    private final CharacterEquipment equipment;

    public PlayerCharacter(int startStrength, int startEndurance, int startIntelligence, int startCharisma, int startCunning, int startLuck, String name, ImageIcon image) {
        super(startStrength, startEndurance, startIntelligence, startCharisma, startCunning, startLuck,name,image);
        equipment=new CharacterEquipment(this);
    }

    public void setParty(PlayerParty party){
        this.party=party;
    }

    public PlayerParty getParty() {
        return party;
    }

    public CharacterEquipment getEquipment() {
        return equipment;
    }

    public int getDiceNumber(int attribute){
        attribute*=getStatisticMod();
        int diceNumber=attribute/6+1;
        return Math.min(Math.max(diceNumber, 1),12);
    }

    public int getCharacterRerolls(){
        return getLuck()/10+1;
    }

    public void recalculateStats(){
        party.calculateHealthAndMana();
    }

    @Override
    public void dealDamage(int damage) throws CharacterDieException {
        damage*=getDamageReceivingMod();
        party.dealDamage(damage);
        if(party.getCurrentHealth()==0)
            throw new CharacterDieException();
    }

    @Override
    public void healDamage(int heal){
        party.healDamage(heal);
    }

    @Override
    public void gainMana(int mana){
        party.gainMana(mana);
    }

    @Override
    public void addShield(int shield){
        party.addShield(shield);
    }
}
