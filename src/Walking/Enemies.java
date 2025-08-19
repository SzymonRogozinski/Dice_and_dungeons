package Walking;

import Character.Enemy.EnemyCharacter;
import Generators.EnemyGenerator.EnemyGenerator;
import Quest.Quest;
import Walking.Drones.Drone;
import Walking.Drones.EnemyDrone;
import Walking.Places.*;

import java.util.*;

import dg.generator.dungeon.Coordinate;
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

    public Enemies(ArrayList<Coordinate> enemiesCoordinates,String path, int enemyCost, int minHP) {
        enemies = new ArrayList<>();
        for(Coordinate c:enemiesCoordinates)
            enemies.add(new EnemyDrone(c.x, c.y, new EnemyGamePlace("enemy", path), new EnemyGamePlace("enemy_pursuit", path),
                    EnemyGenerator.generateEnemyList(enemyCost, minHP)));
        this.counter = 0;
    }

    public void addQuestToEnemy(int enemyId, Quest quest){
        EnemyDrone enemy = enemies.get(enemyId);
        EnemyDrone questEnemy = new EnemyDrone(enemy.getPosX(), enemy.getPosY(),
                new QuestPlace(enemy.getBaseIcon(),quest),
                new QuestPlace(enemy.getPursuitIcon(),quest),
                enemy.getEnemies()
        );
        enemies.add(questEnemy);
        enemies.remove(enemy);
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
