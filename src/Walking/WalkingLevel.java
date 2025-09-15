package Walking;

import Character.Enemy.EnemyCategory;
import Character.Enemy.EnemyCharacter;
import Dialog.NPCLines;
import Game.GameLevel;
import Game.GameManager;
import Game.GameStates;
import Generators.EnemyGenerator.EnemyGenerator;
import Generators.StoryGenerator.NPCGenerator;
import Generators.StoryGenerator.QuestGenerator;
import Loot.LootModule;
import Quest.*;
import Walking.Collision.NPCDialogException;
import Walking.Drones.Drone;
import Walking.Drones.EnemyDrone;
import Walking.Drones.PlayerDrone;
import Walking.Collision.EnemyFightException;
import Walking.Collision.EnterExitException;
import Walking.Places.*;
import dg.generator.dungeon.Coordinate;
import dg.generator.dungeon.Map;
import dg.generator.dungeon.Place;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class WalkingLevel {
    public final FogOfWar fogOfWar;
    private final GameMap gameMap;
    private final Enemies enemies;
    private final PlayerDrone player;
    private final EnemyCharacter boss;

    //Player state
    private int playerDx=0;
    private int playerDy=0;
    private int playerCountdown=0;
    private final static int MOVE_COUNTDOWN=10;
    private boolean oneStep=false;

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

        //Add NPC
        if(gameMap.getNpcPlace()!=null){
            //Generate quests
            ArrayList<Quest> quests = new ArrayList<>();
            for(int i=0; i<settings.quests(); i++)
                quests.add(QuestGenerator.generateQuest(levelSetting.questLoot()));
            setQuestPlaces(quests);

            gameMap.getNpcPlace().setLines(NPCGenerator.generateNPC(quests, settings.traderQuality(), settings.traderItemCount()));
        }
        //Add player
        player = new PlayerDrone(gameMap.getStartX(), gameMap.getStartY(), new PlayerGamePlace(gameMap.getPath()));
        gameMap.addCharacterPlace(player.getIcon(), player.getPosX(), player.getPosY());
        //Add fog
        fogOfWar = new FogOfWar(player, gameMap);
    }

    public WalkingLevel(JSONObject mapJson){

        int[] start = new int[2];
        start[0] = mapJson.getJSONArray("Player").getInt(0);
        start[1] = mapJson.getJSONArray("Player").getInt(1);

        int[] end = new int[2];
        end[0] = mapJson.getJSONArray("Exit").getInt(0);
        end[1] = mapJson.getJSONArray("Exit").getInt(1);

        gameMap = new GameMap(mapJson.getString("Terrain"),mapJson.getString("imagesPath"),mapJson.getBoolean("isBoss"), start, end);

        //Cast array to Coordinate
        ArrayList<Coordinate> enemiesCoordinates = new ArrayList<>();
        JSONArray jsonEnemiesCoordinates = mapJson.getJSONArray("Enemies");
        for(int i=0;i<jsonEnemiesCoordinates.length();i++)
            enemiesCoordinates.add(new Coordinate(jsonEnemiesCoordinates.getJSONArray(i).getInt(0),jsonEnemiesCoordinates.getJSONArray(i).getInt(1)));

        enemies=new Enemies(enemiesCoordinates,mapJson.getString("imagesPath"),
                mapJson.getJSONObject("EnemiesStats").getInt("enemyCost"),
                mapJson.getJSONObject("EnemiesStats").getInt("minHP"));
        setEnemy();

        //Load Quests
        ArrayList<Quest> quests = new ArrayList<>();
        for (int i = 0; i < mapJson.getJSONArray("Quests").length(); i++) {
            quests.add(new Quest(mapJson.getJSONArray("Quests").getJSONObject(i), LootModule.generateLoot(GameManager.getCurrentLevel().questLoot())));
            switch (quests.getLast().getQuestType()){
                case QuestType.CHEST_OPEN -> {
                    QuestPlace questPlace = new QuestPlace(new TreasureGamePlace(GameManager.getWalkingManager().getWalking().getMap().getPath()),quests.getLast());
                    gameMap.setTerrain(
                            questPlace,
                            mapJson.getJSONArray("Quests").getJSONObject(i).getJSONArray("chestLocation").getInt(0),
                            mapJson.getJSONArray("Quests").getJSONObject(i).getJSONArray("chestLocation").getInt(1)
                    );
                }
                case QuestType.ENEMY_DEFEAT -> enemies.addQuestToEnemy(0,quests.getLast());
                case null, default -> throw new RuntimeException("Error while loading quest. QuestType cannot be null.");
            }
        }

        //Add NPC
        NPCPlace npcPlace = new NPCPlace(gameMap.getPath());
        npcPlace.setLines(new NPCLines(mapJson.getJSONObject("NPC"),quests));
        gameMap.setTerrain(
                npcPlace,
                mapJson.getJSONObject("NPC").getJSONArray("Location").getInt(0),
                mapJson.getJSONObject("NPC").getJSONArray("Location").getInt(1)
        );

        //Add safe room
        for (int i = 0; i < mapJson.getJSONArray("SafeRoom").length(); i++) {
            gameMap.setTerrain(
                    new SafeRoomDoor(gameMap.getPath()),
                    mapJson.getJSONArray("SafeRoom").getJSONArray(i).getInt(0),
                    mapJson.getJSONArray("SafeRoom").getJSONArray(i).getInt(1)
            );
        }

        //Add player
        player = new PlayerDrone(gameMap.getStartX(), gameMap.getStartY(), new PlayerGamePlace(gameMap.getPath()));
        gameMap.addCharacterPlace(player.getIcon(), player.getPosX(), player.getPosY());
        //Add fog
        fogOfWar = new FogOfWar(player, gameMap);

        boss = mapJson.getBoolean("isBoss") ? EnemyGenerator.generate(EnemyCategory.Boss, mapJson.getJSONObject("EnemiesStats").getInt("enemyCost"), mapJson.getJSONObject("EnemiesStats").getInt("minHP")) : null;
    }

    public PlayerDrone getPlayer() {
        return player;
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
        playerDx=dx;
        playerDy=dy;
    }

    public void playerMoveByOne(int dx, int dy) {
        playerDx=dx;
        playerDy=dy;
        oneStep=true;
    }

    public synchronized void makePlayerMove() {
        if (GameManager.getThreadManager().isEnemyThreadStopped())
            return;
        try {
            //Move
            if(playerCountdown==0 && !(playerDx==0 && playerDy==0)) {
                gameMap.changeCharacterPlace(player, playerDx, playerDy);
                playerCountdown=MOVE_COUNTDOWN;
                if(oneStep){
                    oneStep=false;
                    playerDx=0;
                    playerDy=0;
                }
            }else if(playerCountdown>0)
                playerCountdown--;
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
        } catch (NPCDialogException e){
            GameManager.getDialogModule().setPointedNPC(e.getLines());
            GameManager.changeState(GameStates.DIALOG);
        }finally {
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

    private void setQuestPlaces(ArrayList<Quest> quests){
        //It was assumed that number of treasures and enemies was bigger than number of quest
        ArrayList<Coordinate> treasures = new ArrayList<>();
        for (int y = 0; y < gameMap.getHeight(); y++) {
            for (int x = 0; x < gameMap.getWidth(); x++) {
                if (gameMap.getPlace(x, y) instanceof TreasureGamePlace)
                    treasures.add(new Coordinate(x,y));
            }
        }

        for(Quest quest:quests){
            switch (quest.getQuestType()){
                case CHEST_OPEN -> {
                    Coordinate c = treasures.get(GameManager.getRandom().nextInt(treasures.size()));
                    treasures.remove(c);
                    gameMap.setTerrain(new QuestPlace(gameMap.getPlace(c.x,c.y),quest), c.x,c.y);
                }
                case ENEMY_DEFEAT -> {
                    while (true) {
                        try {
                            enemies.addQuestToEnemy(GameManager.getRandom().nextInt(enemies.countEnemy()), quest);
                            break;
                        } catch (IllegalArgumentException ignore){}
                    }
                }
                case null, default -> throw new RuntimeException("Cannot set quest that type do not exist");
            }
        }
    }
}
