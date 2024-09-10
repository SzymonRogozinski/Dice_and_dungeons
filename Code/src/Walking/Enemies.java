package Walking;

import Character.Enemy.EnemyCharacter;
import Generators.EnemyGenerator.EnemyGenerator;
import Walking.Drones.Drone;
import Walking.Drones.EnemyDrone;
import Walking.Places.*;

import java.io.*;
import java.util.*;

import dg.generator.dungeon.Map;
import dg.generator.dungeon.Place;

public class Enemies implements Iterable<EnemyDrone> {
    private ArrayList<EnemyDrone> enemies;
    private int counter;

    public Enemies(File inputFile) {
        try {
            BufferedReader file=new BufferedReader(new FileReader(inputFile));
            String entrypoint;
            //Find entry point for enemies data
            do{
                entrypoint=file.readLine();
            }while(!entrypoint.contains("Enemies:"));
            String iconPath = file.readLine();
            Scanner scanner = new Scanner(file.readLine());
            int len = scanner.nextInt();
            int x,y;
            enemies=new ArrayList<>();

            for(int i=0;i<len;i++) {
                scanner = new Scanner(file.readLine());
                x = scanner.nextInt();
                y = scanner.nextInt();
                //TODO ???
                //enemies.add(new EnemyDrone(x, y, new EnemyGamePlace("enemy", iconPath),new EnemyGamePlace("enemy_pursuit", iconPath)));
            }
        }catch (FileNotFoundException e){
            System.err.println("File not found!");
            System.exit(1);
        }catch(NoSuchElementException | IOException | NullPointerException e){
            System.err.println("File illegal format!");
            System.exit(2);
        }
        counter=0;
    }

    public Enemies(Map map,String path,int enemyCost){
        enemies=new ArrayList<>();
        for(int y=0;y<map.getHeight();y++){
            for(int x=0;x<map.getWidth();x++) {
                if(map.getTerrain(x, y)== Place.ENEMY){
                    ArrayList<EnemyCharacter> enemyCharacters = EnemyGenerator.generateEnemyList(enemyCost);
                    EnemyDrone enemy=new EnemyDrone(x, y, new EnemyGamePlace("enemy", path),new EnemyGamePlace("enemy_pursuit", path),enemyCharacters );
                    enemies.add(enemy);
                }
            }
        }
        counter=0;
    }

    public void removeEnemy(Drone enemy){
        enemies.remove(enemy);
    }

    public EnemyDrone getNextEnemy(){
        if(counter>= enemies.size())
            counter=0;
        return enemies.get(counter++);
    }

    public int countEnemy(){
        return enemies.size();
    }

    @Override
    public  Iterator<EnemyDrone> iterator(){
        return enemies.iterator();
    }
}
