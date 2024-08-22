package Generators.EnemyGenerator;

import Character.Enemy.EnemyCategory;
import Fight.GameActions.EnemyAction;
import Game.GameBalance;
import Game.GameCollection;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class EnemyAdjective {

    //Static part
    private static final ArrayList<EnemyAdjective> ADJECTIVES = new ArrayList<>(Arrays.asList(
            new EnemyAdjective(new int[]{10,0,0,0,0},"strong",null,1),
            new EnemyAdjective(new int[]{0,10,0,0,0},"vital",null,1),
            new EnemyAdjective(new int[]{0,0,10,0,0},"wisdom",null,1)
    ));

    public static EnemyAdjective getAdjective(int cost){
        EnemyAdjective adjective = ADJECTIVES.get(GameCollection.random.nextInt(ADJECTIVES.size()));
        return adjective.cost>cost?getAdjective(cost):adjective;
    }

    private final int[] stats;
    public final String name;
    private final ArrayList<EnemyAction> actions;
    public final int cost;

    public EnemyAdjective(int[] stats, String name, ArrayList<EnemyAction> actions, int cost) {
        this.stats = stats;
        this.name = name;
        this.actions = actions;
        this.cost = cost;
    }

    public void modifyEnemy(EnemyBase enemy){
        if(stats!=null){
            for(int i=0;i<5;i++) {
                enemy.stats[i] += stats[i];
                if(enemy.stats[i] > GameBalance.MAX_ENEMY_STAT)
                    enemy.stats[i] = GameBalance.MAX_ENEMY_STAT;
            }
        }
        if(actions!=null)
            enemy.actions.addAll(actions);
    }
}
