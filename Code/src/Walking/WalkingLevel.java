package Walking;

import Character.Enemy.EnemyCharacter;
import Game.GameLevel;
import Game.GameManager;
import Game.GameStates;
import Walking.Drones.Drone;
import Walking.Drones.EnemyDrone;
import Walking.Drones.PlayerDrone;
import Walking.Collision.EnemyFightException;
import Walking.Collision.EnterExitException;
import Walking.Places.PlayerGamePlace;
import dg.generator.dungeon.Map;

public class WalkingLevel {
    private final GameMap gameMap;
    private final Enemies enemies;
    private final EnemyThread enemyThread;
    private final PlayerDrone player;
    private final WalkingModule manager;
    public final FogOfWar fogOfWar;
    private boolean isStopped;

    public WalkingLevel(WalkingModule manager, GameLevel levelSetting) {
        MapCreator creator;
        WalkingSettings settings = levelSetting.getWalkingSettings();
        if(settings.seed==0)
            creator=new MapCreator();
        else
            creator=new MapCreator(settings.seed);
        if(!creator.createMap(settings.algGen,settings.width,settings.height,settings.size,settings.enemies,settings.treasures,settings.vaults))
            throw new RuntimeException("Dungeon map was not generated!");
        Map map=creator.getMap();
        //Loading enemies
        this.enemies=new Enemies(map,settings.path, levelSetting.getEnemyStrength());
        //Loading map
        this.gameMap =new GameMap(map,settings.path);
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
        isStopped=false;
    }

    public boolean walkingRunning(){
        return enemyThread.isAlive();
    }

    public void walkingStop(){
        isStopped=true;
        enemyThread.pauseThread();
    }

    public void walkingContinue(){
        isStopped=false;
        enemyThread.resumeThread();
    }

    private void setEnemy(){
        for (Drone enemy:enemies)
            gameMap.addCharacterPlace(enemy.getIcon(),enemy.getPosX(),enemy.getPosY());
    }

    public int getEnemyCount(){
        return enemies.countEnemy();
    }

    public synchronized void playerMove(int dx,int dy) {
        if(isStopped)
            return;
        try {
            gameMap.changeCharacterPlace(player, dx, dy);
        }catch (EnemyFightException e){
            enemies.removeEnemy(e.getReference());
            EnemyDrone enemy = (EnemyDrone) e.getReference();
            GameManager.getFight().startFight(enemy.getEnemies());
            GameManager.changeState(GameStates.FIGHTING);
        }catch (EnterExitException e){
            try {
                manager.setNextMap();
            }catch (Exception ex){
                //TODO BOSS!
                System.out.println("Boss will someday appear!");
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
            try {
                enemy.enemyMove(gameMap);
            }catch (EnemyFightException e){
                enemies.removeEnemy(enemy);
                GameManager.getFight().startFight(enemy.getEnemies());
                GameManager.changeState(GameStates.FIGHTING);
            }
        }
        manager.getState().refresh();
    }

    private class EnemyThread extends Thread{

        private final static int oneRoundTime=500;
        private boolean endThread,stopThread;
        private Object lock = new Object();

        @Override
        public void run(){
            while(enemies.countEnemy()>0 && !endThread) {
                synchronized (lock) {
                    if (stopThread) {
                        stopThread = false;
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException("Thread interrupted!?");
                        }
                    }
                }
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
        synchronized void pauseThread(){
            stopThread=true;
        }

        void resumeThread(){
            synchronized (lock) {
                lock.notifyAll();
            }
        }
    }
}
