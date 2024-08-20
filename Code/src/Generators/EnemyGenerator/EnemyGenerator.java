package Generators.EnemyGenerator;

import Character.Enemy.EnemyAI;
import Character.Enemy.EnemyCategory;
import Character.Enemy.EnemyCharacter;
import Game.GameBalance;
import Generators.Generator;

import java.util.ArrayList;
import java.util.HashMap;

public class EnemyGenerator extends Generator {

    private static final int ENEMY_GROUP_COUNT = 3;

    public static EnemyCharacter generate(EnemyCategory category, int cost) {
        EnemyBase base = EnemyBase.getBase(category, cost);
        return generate(base, cost);
    }

    public static EnemyCharacter generate(EnemyBase base, int cost) {
        cost-= base.cost;
        while (cost>= GameBalance.ENEMY_MIN_COST) {
            EnemyAdjective adjective = EnemyAdjective.getAdjective(cost);
            adjective.modifyEnemy(base);
            base.adjectives.add(adjective);
            cost-=adjective.cost;
        }
        String name =generateName(base);
        EnemyAI ai = new EnemyAI(base.actions);
        return new EnemyCharacter(base.stats[0],base.stats[1],base.stats[2],base.stats[3],base.stats[4],base.category,name,base.image,ai);
    }

    public static ArrayList<EnemyCharacter> generateMore(int cost) {
        ArrayList<EnemyCharacter> enemies = new ArrayList<>();
        EnemyBase base = EnemyBase.getBase(EnemyCategory.Minion, cost);
        for (int i = 0; i < ENEMY_GROUP_COUNT; i++)
            enemies.add(generate((EnemyBase) base.clone(), cost));
        return enemies;
    }

    // If base have adjectives, get one with the highest value
    private static String generateName(EnemyBase base){
        HashMap<String,Integer> map = new HashMap<>();
        String adjName=base.adjectives.isEmpty()?null:base.adjectives.get(0).name;
        for(EnemyAdjective adj:base.adjectives){
            if(map.containsKey(adj.name)){
                int oldCost = map.get(adj.name);
                map.replace(adj.name,oldCost+adj.cost);
            }else
                map.put(adj.name,adj.cost);
            if(map.get(adjName)<map.get(adj.name))
                adjName = adj.name;
        }
        return adjName!=null? adjName+" "+base.name:base.name;
    }
}
