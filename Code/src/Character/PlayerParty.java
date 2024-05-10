package Character;

import java.util.ArrayList;

public class PlayerParty {

    private ArrayList<PlayerCharacter> characters;
    private int maxHealth,currentHealth,maxMana,currentMana,shield;

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
        shield=0;
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
