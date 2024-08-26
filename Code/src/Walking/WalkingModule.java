package Walking;

import Walking.Drones.Drone;
import Walking.Drones.EnemyDrone;
import Walking.Drones.PlayerDrone;
import Walking.Collision.EnemyKilledException;
import Walking.Collision.EnterExitException;
import Walking.Places.PlayerGamePlace;
import dg.generator.dungeon.Map;

import java.io.File;

public class WalkingModule {
    private final GameMap gameMap;
    private final Enemies enemies;
    private final EnemyThread enemyThread;
    private final PlayerDrone player;
    private final WalkingManager manager;
    public final FogOfWar fogOfWar;

    public WalkingModule(String filename) {
        File input=new File(filename);
        //Loading enemies
        this.enemies=new Enemies(input);
        //Loading map
        this.gameMap =new GameMap(input);
        setEnemy();
        enemyThread=new EnemyThread();
        //Add player
        player=new PlayerDrone(gameMap.getStartX(), gameMap.getStartY(),new PlayerGamePlace(gameMap.getPATH()));
        gameMap.addCharacterPlace(player.getIcon(), player.getPosX(), player.getPosY());
        //Add fog
        fogOfWar=new FogOfWar(player,gameMap);
        manager=null;
    }

    public WalkingModule(WalkingManager manager,int seed, int genAlg, int width, int height, int size, int enemies, int treasures, int vaults, String path) throws Exception {
        MapCreator creator;
        if(seed==0)
            creator=new MapCreator();
        else
            creator=new MapCreator(seed);
        if(!creator.createMap(genAlg,width,height,size,enemies,treasures,vaults))
            throw new Exception("Dungeon map was not generated!");
        Map map=creator.getMap();
        //Loading enemies
        this.enemies=new Enemies(map,path);
        //Loading map
        this.gameMap =new GameMap(map,path);
        setEnemy();
        enemyThread=new EnemyThread();
        //Add player
        player=new PlayerDrone(gameMap.getStartX(), gameMap.getStartY(),new PlayerGamePlace(gameMap.getPATH()));
        gameMap.addCharacterPlace(player.getIcon(), player.getPosX(), player.getPosY());
        //Add fog
        fogOfWar=new FogOfWar(player,gameMap);
        this.manager=manager;
    }

    public GameMap getMap() {
        return gameMap;
    }

    public void walkingStart(){
        enemyThread.start();
    }

    private void setEnemy(){
        for (Drone enemy:enemies)
            gameMap.addCharacterPlace(enemy.getIcon(),enemy.getPosX(),enemy.getPosY());
    }

    public synchronized void playerMove(int dx,int dy) {
        try {
            gameMap.changeCharacterPlace(player, dx, dy);
        }catch (EnemyKilledException e){
            enemies.removeEnemy(e.getReference());
        }catch (EnterExitException e){
            try {
                manager.setNextMap();
            }catch (Exception ex){
                throw new RuntimeException(ex.getMessage());
            }
        }finally {
            fogOfWar.refreshFog();
            manager.getState().refresh();
        }
    }

    public synchronized void killModule(){
        try {
            enemyThread.endThread();
            enemyThread.join();
        }catch (InterruptedException ignored){}
    }

    private synchronized void enemiesMove(){
        EnemyDrone enemy=enemies.getNextEnemy();
        if (enemy.ifMove()) {
            enemy.ifPlayerWasSeen(player.getPosX(), player.getPosY(), gameMap);
            enemy.enemyMove(gameMap);
        }
        manager.getState().refresh();
    }

    private class EnemyThread extends Thread{

        private final static int oneRoundTime=500;
        private boolean endThread;

        @Override
        public void run(){
            while(enemies.countEnemy()>0 && !endThread) {
                enemiesMove();
                try {
                    Thread.sleep(oneRoundTime/enemies.countEnemy());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Thread end");
        }

        synchronized void endThread(){
            endThread=true;
        }
    }
}
