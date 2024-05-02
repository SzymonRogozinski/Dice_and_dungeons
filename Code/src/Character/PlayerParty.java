package Character;

import java.util.ArrayList;

public class PlayerParty {

    private ArrayList<PlayerCharacter> characters;
    private int maxHealth,currentHealth,maxMana,currentMana;

    public PlayerParty(ArrayList<PlayerCharacter> characters) {
        this.characters = characters;
        int health=0;
        int mana=0;
        for(PlayerCharacter player:characters){
            player.setParty(this);
            health+=player.getEndurance();
            mana+=player.getIntelligence();
        }
        maxHealth=health;
        currentHealth=health;
        maxMana=mana;
        currentMana=mana;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void dealDamage(int damage) {
        currentHealth-=damage;
        if(currentHealth<0)
            currentHealth=0;
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

    public ArrayList<PlayerCharacter> getCharacters() {
        return characters;
    }
}
