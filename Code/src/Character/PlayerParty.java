package Character;

import Equipment.Items.Item;
import Equipment.PartyBackpack;
import Fight.GameActions.UsableItemAction;

import java.util.ArrayList;

public class PlayerParty {

    private ArrayList<PlayerCharacter> characters;
    private int maxHealth,currentHealth,maxMana,currentMana,shield;
    private final PartyBackpack backpack;

    public PlayerParty(ArrayList<PlayerCharacter> characters,ArrayList<Item> items) {
        this.characters = characters;
        backpack=new PartyBackpack(items);
        int health=0;
        int mana=0;
        for(PlayerCharacter player:characters){
            health+=player.getEndurance();
            mana+=player.getIntelligence();
        }
        maxHealth=health;
        currentHealth=health;
        maxMana=mana;
        currentMana=mana;
        shield=0;
    }

    public PartyBackpack getBackpack() {
        return backpack;
    }

    public void calculateHealthAndMana(){
        int health=0;
        int mana=0;
        for(PlayerCharacter player:characters){
            health+=player.getEndurance();
            mana+=player.getIntelligence();
        }
        int healthDif=health-maxHealth;
        maxHealth=health;
        currentHealth=Math.max(currentHealth+healthDif,1);
        int manaDif=mana-maxMana;
        maxMana=mana;
        currentMana=Math.max(currentMana+manaDif,1);
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void dealDamage(int damage) {
        shield-=damage;
        if(shield<0){
            currentHealth+=shield;
            shield=0;
            if(currentHealth<0)
                currentHealth=0;
        }
    }

    public void healDamage(int heal){
        currentHealth+=heal;
        if(currentHealth>maxHealth)
            currentHealth=maxHealth;
    }

    public void gainMana(int mana){
        currentMana+=mana;
        if(currentMana>maxMana)
            currentMana=maxMana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public int getCurrentMana() {
        return currentMana;
    }

    public void spendMana(int mana) {
        currentMana-=mana;
        if(currentMana<0)
            throw new RuntimeException("Your mana cannot be negative!");
    }

    public void addShield(int shield){
        this.shield+=shield;
    }

    public int getShield(){return shield;}
    public void onTurnStart(){
        this.shield=0;
    }

    public ArrayList<PlayerCharacter> getCharacters() {
        return characters;
    }
}
