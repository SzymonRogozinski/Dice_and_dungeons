package Character;

import Dice.DiceAction.DamageAction;
import Dice.DiceAction.DiceAction;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class EnemyCharacter extends GameCharacter{

    private int maxHealth,currentHealth;

    public EnemyCharacter(int startStrength, int startEndurance, int startIntelligence, int startCharisma, int startCunning, int startLuck, String name, ImageIcon image) {
        super(startStrength, startEndurance, startIntelligence, startCharisma, startCunning, startLuck,name,image);
        maxHealth=startEndurance;
        currentHealth=startEndurance;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    @Override
    public void dealDamage(int damage) {
        currentHealth-=damage;
        if(currentHealth<0)
            currentHealth=0;
    }

    public ArrayList<DiceAction> action(){
        System.out.println("Enemy attack!");
        System.out.println(currentHealth+"/"+maxHealth);

        //Choose action
        DiceAction action=new DamageAction(3);
        return new ArrayList<>(List.of(new DiceAction[]{action}));
    }
}
