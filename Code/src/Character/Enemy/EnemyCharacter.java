package Character.Enemy;

import Fight.GameActions.EnemyAction;
import Character.GameCharacter;
import Character.CharacterDieException;
import Character.PlayerCharacter;
import Game.GameBalance;
import Game.Tags;

import javax.swing.*;
import java.util.ArrayList;

public class EnemyCharacter extends GameCharacter {

    private final int maxHealth;
    private int currentHealth,shield;
    private final EnemyAI ai;
    private final EnemyCategory category;

    public EnemyCharacter(int startStrength, int startEndurance, int startIntelligence, int startCharisma, int startCunning, EnemyCategory category, String name, ImageIcon image, EnemyAI ai) {
        super(startStrength, startEndurance, startIntelligence, startCharisma, startCunning, 0,name,image, new Tags[]{});
        this.ai=ai;
        this.category=category;
        if(category==EnemyCategory.Minion)
            maxHealth=startEndurance* GameBalance.ENEMY_MINION_HP_MOD;
        else if(category==EnemyCategory.Strong)
            maxHealth=startEndurance* GameBalance.ENEMY_STRONG_HP_MOD;
        else if (category==EnemyCategory.Boss)
            maxHealth=startEndurance* GameBalance.ENEMY_BOSS_HP_MOD;
        else
            throw new IllegalArgumentException("EnemyCategory "+category+" is not supported!");

        currentHealth=maxHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public EnemyCategory getCategory() {
        return category;
    }

    @Override
    public void dealDamage(int damage) throws CharacterDieException {
        damage= (int) (damage * getDamageReceivingMod());
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
