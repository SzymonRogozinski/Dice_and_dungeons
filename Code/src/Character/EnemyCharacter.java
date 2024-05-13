package Character;

import Dice.DiceAction.DamageAction;
import Dice.DiceAction.DiceAction;
import Dice.DiceAction.WeaknessAction;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class EnemyCharacter extends GameCharacter{

    private int maxHealth,currentHealth,shield;

    public EnemyCharacter(int startStrength, int startEndurance, int startIntelligence, int startCharisma, int startCunning, int startLuck, String name, ImageIcon image) {
        super(startStrength, startEndurance, startIntelligence, startCharisma, startCunning, startLuck,name,image);
        maxHealth=startEndurance*5;
        currentHealth=startEndurance*5;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    @Override
    public void dealDamage(int damage) throws CharacterDieException{
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

    public ArrayList<DiceAction> action(){
        //TODO
        getStatisticMod();
        System.out.println("Enemy attack!");
        System.out.println(currentHealth+"/"+maxHealth);

        //Choose action
        DiceAction action=new DamageAction(3);
        WeaknessAction action2 = new WeaknessAction(2);
        return new ArrayList<>(List.of(new DiceAction[]{action,action2}));
    }

    public void onTurnStart(){
        this.shield=0;
    }

    public int getShield(){return shield;}
}
