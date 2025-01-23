package Walking;

import Character.Enemy.EnemyCharacter;
import Generators.EnemyGenerator.EnemyGenerator;
import Walking.Drones.Drone;
import Walking.Drones.EnemyDrone;
import Walking.Places.*;

import java.util.*;

import dg.generator.dungeon.Map;
import dg.generator.dungeon.Place;

public class Enemies implements Iterable<EnemyDrone> {
    private final ArrayList<EnemyDrone> enemies;
    private int counter;

    public Enemies(Map map, String path, int enemyCost, int minHP) {
        enemies = new ArrayList<>();
        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                if (map.getTerrain(x, y) == Place.ENEMY) {
                    ArrayList<EnemyCharacter> enemyCharacters = EnemyGenerator.generateEnemyList(enemyCost, minHP);
                    EnemyDrone enemy = new EnemyDrone(x, y, new EnemyGamePlace("enemy", path), new EnemyGamePlace("enemy_pursuit", path), enemyCharacters);
                    enemies.add(enemy);
                }
            }
        }
        counter = 0;
    }

    public void removeEnemy(Drone enemy) {
        enemies.remove(enemy);
    }

    public EnemyDrone getNextEnemy() {
        if (counter >= enemies.size())
            counter = 0;
        return enemies.get(counter++);
    }

    public int countEnemy() {
        return enemies.size();
    }

    @Override
    public Iterator<EnemyDrone> iterator() {
        return enemies.iterator();
    }
}
