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

    public int getMaxMana() {
        return maxMana;
    }

    public int getCurrentMana() {
        return currentMana;
    }

    public ArrayList<PlayerCharacter> getCharacters() {
        return characters;
    }
}
