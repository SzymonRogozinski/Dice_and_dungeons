package Character;

import Fight.GameActions.ActionItem;
import Fight.GameActions.SpellAction;

import javax.swing.*;
import java.util.ArrayList;

public class PlayerCharacter extends GameCharacter{

    private static final int MAX_ACTION_ITEM=3;
    private final ArrayList<ActionItem> actionItems;
    private final ArrayList<SpellAction> spells;
    private PlayerParty party;

    public PlayerCharacter(int startStrength, int startEndurance, int startIntelligence, int startCharisma, int startCunning, int startLuck, String name, ImageIcon image,ArrayList<ActionItem> actionItems,ArrayList<SpellAction> spells) {
        super(startStrength, startEndurance, startIntelligence, startCharisma, startCunning, startLuck,name,image);
        this.actionItems = actionItems;
        this.spells=spells;
    }

    public void setParty(PlayerParty party){
        this.party=party;
    }

    public int getDiceNumber(int attribute){
        attribute*=getStatisticMod();
        int diceNumber=attribute/6+1;
        return Math.min(Math.max(diceNumber, 1),12);
    }

    public int getCharacterRerolls(){
        return getLuck()/10;
    }

    public ArrayList<ActionItem> getActionItems() {
        return actionItems;
    }

    public ArrayList<SpellAction> getSpells() {
        return spells;
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
