package Generators.EnemyGenerator;

import Character.Enemy.EnemyAI;
import Character.Enemy.EnemyCategory;
import Character.Enemy.EnemyCharacter;
import Game.GameBalance;
import Game.GameManager;
import Generators.Generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EnemyGenerator extends Generator {

    private static final int ENEMY_GROUP_COUNT = 3;

    public static EnemyCharacter generate(EnemyCategory category, int cost, int minHP) {
        EnemyBase base = EnemyBase.getBase(category, cost);
        try {
            return generate((EnemyBase) base.clone(), cost, minHP);
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Error while cloning enemy base!");
        }
    }

    public static EnemyCharacter generate(EnemyBase base, int cost, int minHP) {
        cost -= base.cost;
        if (base.stats[1] < minHP) {
            int diff = minHP - base.stats[1];
            int hpCost = diff / 10 + diff % 10 == 0 ? 0 : 1;
            base.stats[1] += (hpCost * 10);
            cost -= hpCost;
        }
        while (cost >= GameBalance.ENEMY_MIN_COST) {
            EnemyAdjective adjective = EnemyAdjective.getAdjective(cost);
            adjective.modifyEnemy(base);
            base.adjectives.add(adjective);
            cost -= adjective.cost;
        }
        String name = generateName(base);
        EnemyAI ai = new EnemyAI(base.actions);
        return new EnemyCharacter(base.stats[0], base.stats[1], base.stats[2], base.stats[3], base.stats[4], base.category, name, base.image, ai);
    }

    public static ArrayList<EnemyCharacter> generateMore(int cost, int minHP) {
        ArrayList<EnemyCharacter> enemies = new ArrayList<>();
        EnemyBase base = EnemyBase.getBase(EnemyCategory.Minion, cost);
        for (int i = 0; i < ENEMY_GROUP_COUNT; i++) {
            try {
                enemies.add(generate((EnemyBase) base.clone(), cost, minHP));
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException("Error while cloning enemy base!");
            }
        }

        return enemies;
    }

    public static ArrayList<EnemyCharacter> generateEnemyList(int cost, int minHP) {
        int roll = GameManager.getRandom().nextInt(3);
        if (roll == 2)
            return new ArrayList<>(List.of(EnemyGenerator.generate(EnemyCategory.Strong, cost, minHP)));
        else
            return generateMore(cost, minHP);
    }

    // If base have adjectives, get one with the highest value
    private static String generateName(EnemyBase base) {
        HashMap<String, Integer> map = new HashMap<>();
        String adjName = base.adjectives.isEmpty() ? null : base.adjectives.getFirst().name;
        for (EnemyAdjective adj : base.adjectives) {
            if (map.containsKey(adj.name)) {
                int oldCost = map.get(adj.name);
                map.replace(adj.name, oldCost + adj.cost);
            } else
                map.put(adj.name, adj.cost);
            if (map.get(adjName) < map.get(adj.name))
                adjName = adj.name;
        }
        return adjName != null ? adjName + " " + base.name : base.name;
    }
}
