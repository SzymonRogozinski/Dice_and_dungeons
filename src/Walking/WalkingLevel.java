package Walking;

import Character.Enemy.EnemyCategory;
import Character.Enemy.EnemyCharacter;
import Game.GameLevel;
import Game.GameManager;
import Game.GameStates;
import Generators.EnemyGenerator.EnemyGenerator;
import Walking.Drones.Drone;
import Walking.Drones.EnemyDrone;
import Walking.Drones.PlayerDrone;
import Walking.Collision.EnemyFightException;
import Walking.Collision.EnterExitException;
import Walking.Places.PlayerGamePlace;
import dg.generator.dungeon.Map;

import java.util.ArrayList;
import java.util.List;

public class WalkingLevel {
    public final FogOfWar fogOfWar;
    private final GameMap gameMap;
    private final Enemies enemies;
    private final PlayerDrone player;
    private final EnemyCharacter boss;

    public WalkingLevel(GameLevel levelSetting) {
        MapCreator creator;
        WalkingSettings settings = levelSetting.walkingSettings();
        if (settings.seed() == 0)
            creator = new MapCreator();
        else
            creator = new MapCreator(settings.seed());
        if (!creator.createMap(settings.algGen(), settings.width(), settings.height(), settings.size(), settings.enemies(), settings.treasures(), settings.vaults()))
            throw new RuntimeException("Dungeon map was not generated!");
        Map map = creator.getMap();
        //Loading enemies
        this.enemies = new Enemies(map, settings.path(), levelSetting.enemyStrength(), levelSetting.minHP());
        //Loading map
        this.gameMap = new GameMap(map, settings.path(), settings.bossLevel());
        boss = settings.bossLevel() ? EnemyGenerator.generate(EnemyCategory.Boss, levelSetting.enemyStrength(), levelSetting.minHP()) : null;
        setEnemy();

        GameManager.getThreadManager().makeNewEnemyThread();

        //Add player
        player = new PlayerDrone(gameMap.getStartX(), gameMap.getStartY(), new PlayerGamePlace(gameMap.getPath()));
        gameMap.addCharacterPlace(player.getIcon(), player.getPosX(), player.getPosY());
        //Add fog
        fogOfWar = new FogOfWar(player, gameMap);
    }

    public GameMap getMap() {
        return gameMap;
    }

    public Enemies getEnemies() {
        return enemies;
    }

    private void setEnemy() {
        for (Drone enemy : enemies)
            gameMap.addCharacterPlace(enemy.getIcon(), enemy.getPosX(), enemy.getPosY());
    }

    public int getEnemyCount() {
        return enemies.countEnemy();
    }

    public void playerMove(int dx, int dy) {
        if (GameManager.getThreadManager().isEnemyThreadStopped())
            return;
        try {
            gameMap.changeCharacterPlace(player, dx, dy);
        } catch (EnemyFightException e) {
            enemies.removeEnemy(e.getReference());
            EnemyDrone enemy = (EnemyDrone) e.getReference();
            GameManager.getFight().startFight(enemy.getEnemies());
            GameManager.changeState(GameStates.FIGHTING);
        } catch (EnterExitException e) {
            try {
                GameManager.getWalkingManager().setNextMap();
            } catch (Exception ex) {
                if (boss == null)
                    throw new RuntimeException("Boss do not exist!");
                GameManager.getFight().startFight(new ArrayList<>(List.of(boss)));
                GameManager.startBossBattle();
                GameManager.changeState(GameStates.FIGHTING);
            }
        } finally {
            fogOfWar.refreshFog();
        }
    }

    public synchronized void enemiesMove() {
        EnemyDrone enemy = enemies.getNextEnemy();
        boolean playerSeen = enemy.playerSeen(player.getPosX(), player.getPosY());
        if (playerSeen || enemy.ifMove()) {
            if (playerSeen)
                enemy.ifPlayerWasSeen(gameMap);
            try {
                enemy.enemyMove(gameMap);
            } catch (EnemyFightException e) {
                enemies.removeEnemy(enemy);
                GameManager.getFight().startFight(enemy.getEnemies());
                GameManager.changeState(GameStates.FIGHTING);
            }
        }
    }

}
