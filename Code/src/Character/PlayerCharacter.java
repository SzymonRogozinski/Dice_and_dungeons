package Character;

import Equipment.CharacterEquipment;
import Equipment.Items.ActionItem;
import Equipment.Items.SpellItem;
import Game.GameBalance;
import Game.PlayerInfo;
import Game.Tags;

import javax.swing.*;
import java.util.ArrayList;

public class PlayerCharacter extends GameCharacter{
    private final CharacterEquipment equipment;

    public PlayerCharacter(int startStrength, int startEndurance, int startIntelligence, int startCharisma, int startCunning, int startLuck, String name, ImageIcon image, Tags[] tags) {
        super(startStrength, startEndurance, startIntelligence, startCharisma, startCunning, startLuck,name,image,tags);
        equipment=new CharacterEquipment(this);
    }

    public PlayerCharacter(int startStrength, int startEndurance, int startIntelligence, int startCharisma, int startCunning, int startLuck, String name, ImageIcon image, Tags[] tags, ArrayList<ActionItem> items, ArrayList<SpellItem> spells) {
        super(startStrength, startEndurance, startIntelligence, startCharisma, startCunning, startLuck,name,image,tags);
        equipment=new CharacterEquipment(this);
        for(int i=0;i< items.size();i++)
            equipment.equip(items.get(i),i,CharacterEquipment.ACTION_SLOT);

        for(int i=0;i< spells.size();i++)
            equipment.equip(spells.get(i),i,CharacterEquipment.SPELL_SLOT);
    }

    public CharacterEquipment getEquipment() {
        return equipment;
    }

    public int getDiceNumber(int attribute){
        attribute*=getStatisticMod();
        int diceNumber=(attribute-GameBalance.MIN_STAT_VALUE)/GameBalance.DICES_DIVIDE + GameBalance.MIN_DICES;
        return Math.min(Math.max(diceNumber, 1),GameBalance.MAX_DICE);
    }

    public int getCharacterRerolls(){
        return getLuck()/GameBalance.REROLLS_DIVIDE + GameBalance.MIN_REROLLS;
    }

    public void recalculateStats(){
        PlayerInfo.getParty().calculateHealthAndMana();
    }

    @Override
    public void dealDamage(int damage) throws CharacterDieException {
        damage*=getDamageReceivingMod();
        PlayerInfo.getParty().dealDamage(damage);
        if(PlayerInfo.getParty().getCurrentHealth()==0)
            throw new CharacterDieException();
    }

    @Override
    public void healDamage(int heal){
        PlayerInfo.getParty().healDamage(heal);
    }

    @Override
    public void gainMana(int mana){
        PlayerInfo.getParty().gainMana(mana);
    }

    @Override
    public void addShield(int shield){
        PlayerInfo.getParty().addShield(shield);
    }
}
