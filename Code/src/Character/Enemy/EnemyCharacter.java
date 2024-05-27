package Character.Enemy;

import Fight.GameActions.EnemyAction;
import Character.GameCharacter;
import Character.CharacterDieException;
import Character.PlayerCharacter;

import javax.swing.*;
import java.util.ArrayList;

public class EnemyCharacter extends GameCharacter {

    private int maxHealth,currentHealth,shield;
    private final EnemyAI ai;

    public EnemyCharacter(int startStrength, int startEndurance, int startIntelligence, int startCharisma, int startCunning, int startLuck, String name, ImageIcon image, EnemyAI ai) {
        super(startStrength, startEndurance, startIntelligence, startCharisma, startCunning, startLuck,name,image);
        maxHealth=startEndurance*5;
        currentHealth=startEndurance*5;
        this.ai=ai;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    @Override
    public void dealDamage(int damage) throws CharacterDieException {
        damage*=getDamageReceivingMod();
        shield-=damage;
        if(shield<0){
            currentHealth+=shield;
            shield=0;
            if(currentHealth<0)
                currentHealth=0;
        }
        if(currentHealth==0)
            throw new CharacterDieException();
    }

    @Override
    public void healDamage(int heal){
        currentHealth+=heal;
        if(currentHealth>maxHealth)
            currentHealth=maxHealth;
    }

    @Override
    public void gainMana(int mana){
        System.out.println("Enemy cannot gain mana!");
    }

    public EnemyAction action(ArrayList<EnemyCharacter> enemies, ArrayList<PlayerCharacter> characters){
        System.out.println("Enemy attack!");
        System.out.println(currentHealth+"/"+maxHealth);

        //Choose action
        return ai.getAction(enemies, characters);
    }

    public int getTargetId(){
        return ai.getTargetId();
    }

    public void onTurnStart(){
        this.shield=0;
    }

    public int getShield(){return shield;}

    public String getNextAction(){
        return ai.getNextAction(this);
    }
}
