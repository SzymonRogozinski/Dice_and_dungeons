package Character;

import Equipment.CharacterEquipment;
import Game.GameBalance;
import Game.GameCollection;
import Game.Tags;

import javax.swing.*;

public class PlayerCharacter extends GameCharacter{
    private final CharacterEquipment equipment;

    public PlayerCharacter(int startStrength, int startEndurance, int startIntelligence, int startCharisma, int startCunning, int startLuck, String name, ImageIcon image, Tags[] tags) {
        super(startStrength, startEndurance, startIntelligence, startCharisma, startCunning, startLuck,name,image,tags);
        equipment=new CharacterEquipment(this);
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
        GameCollection.getParty().calculateHealthAndMana();
    }

    @Override
    public void dealDamage(int damage) throws CharacterDieException {
        damage*=getDamageReceivingMod();
        GameCollection.getParty().dealDamage(damage);
        if(GameCollection.getParty().getCurrentHealth()==0)
            throw new CharacterDieException();
    }

    @Override
    public void healDamage(int heal){
        GameCollection.getParty().healDamage(heal);
    }

    @Override
    public void gainMana(int mana){
        GameCollection.getParty().gainMana(mana);
    }

    @Override
    public void addShield(int shield){
        GameCollection.getParty().addShield(shield);
    }
}
