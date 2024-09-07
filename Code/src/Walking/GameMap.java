package Walking;

import Game.PlayerInfo;
import Walking.Drones.Drone;
import Walking.Drones.PlayerDrone;
import Walking.Collision.*;
import Walking.Places.*;

import java.io.*;
import java.util.*;

import dg.generator.dungeon.Map;

public class GameMap {
    private int height,width;
    private GamePlace[][] currentGamePlaces;
    private GamePlace[][] originalGamePlaces;
    private int startX, startY;
    private String PATH;

    public GameMap(File inputFile){
        try{
            //Load data
            BufferedReader file=new BufferedReader(new FileReader(inputFile));
            String entrypoint;
            //Find entry point for map data
            do{
                entrypoint=file.readLine();
            }while(!entrypoint.contains("Map:"));

            this.PATH =file.readLine();
            Scanner scanner=new Scanner(file.readLine());

            this.width = scanner.nextInt();
            this.height = scanner.nextInt();
            this.currentGamePlaces = new GamePlace[height][width];
            this.originalGamePlaces = new GamePlace[height][width];

            char[] line;
            GamePlace p;
            for(int i=0;i<height;i++){
                line=file.readLine().toCharArray();
                for(int j=0;j< line.length;j++) {
                    switch (line[j]) {
                        case ' ' -> p = new SpaceGamePlace(PATH);
                        case 'T' -> p = new TreasureGamePlace(PATH);
                        case 'K' -> p=new KeyGamePlace(PATH);
                        case 'D' -> p=new DoorGamePlace(PATH);
                        default -> {
                            if(Character.isLetter(line[j])){
                                p=new WallGamePlace(line[j], PATH);
                            }else{
                                throw new InputMismatchException("Map file is corrupted!");
                            }
                        }
                    }
                    currentGamePlaces[i][j]=p;
                    originalGamePlaces[i][j]=p;
                }
            }

            //Read entries
            //Find entry point for entries data
            do{
                entrypoint=file.readLine();
            }while(!entrypoint.contains("Entries:"));

            int x,y;
            EntryGamePlace entry;
            //Start entry
            scanner=new Scanner(file.readLine());
            x = scanner.nextInt();
            y = scanner.nextInt();
            entry=new EntryGamePlace(PATH,true);
            currentGamePlaces[y][x]=entry;
            originalGamePlaces[y][x]=entry;
            //Save coordinates
            startX =x;
            startY =y;
            //End entry
            scanner=new Scanner(file.readLine());
            x = scanner.nextInt();
            y = scanner.nextInt();
            entry=new EntryGamePlace(PATH,false);
            currentGamePlaces[y][x]=entry;
            originalGamePlaces[y][x]=entry;
        }catch (FileNotFoundException e){
            System.err.println("File not found!");
            System.exit(1);
        }catch(NoSuchElementException | IOException | NullPointerException e){
            System.out.println("Illegal file format");
            System.exit(2);
        }
    }

    public GameMap(Map map,String path){
        this.PATH=path;
        this.width = map.getWidth();
        this.height = map.getHeight();
        this.currentGamePlaces = new GamePlace[height][width];
        this.originalGamePlaces = new GamePlace[height][width];

        GamePlace place;
        //Load terrain
        for(int y=0;y<map.getHeight();y++){
            for(int x=0;x<map.getWidth();x++) {
                switch (map.getTerrain(x, y)) {
                    case null, FLOOR, VOID, ENTRIES, ENEMY -> place = new SpaceGamePlace(PATH);
                    case DOOR -> place = new DoorGamePlace(PATH);
                    case TREASURE -> place = new TreasureGamePlace(PATH);
                    case KEY -> place = new KeyGamePlace(PATH);
                    case WALL -> place = new WallGamePlace('W', PATH);
                    default -> throw new RuntimeException("Something goes wrong while writing map!");
                }
                currentGamePlaces[y][x]=place;
                originalGamePlaces[y][x]=place;
            }
        }
        //Entry
        place=new EntryGamePlace(PATH,true);
        int x,y;
        x=map.getEntries()[0].x;
        y=map.getEntries()[0].y;
        currentGamePlaces[y][x]=place;
        originalGamePlaces[y][x]=place;
        startX=x;
        startY=y;
        //Exit
        place=new EntryGamePlace(PATH,false);
        x=map.getEntries()[1].x;
        y=map.getEntries()[1].y;
        currentGamePlaces[y][x]=place;
        originalGamePlaces[y][x]=place;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public String getPATH(){ return  PATH;}

    public GamePlace getPlace (int x, int y){
        return currentGamePlaces[y][x];
    }

    public int getStartX(){
        return startX;
    }

    public int getStartY(){
        return startY;
    }

    public void addCharacterPlace(GamePlace place, int x, int y){
        try {
            if (currentGamePlaces[y][x] instanceof SpaceGamePlace || (currentGamePlaces[y][x] instanceof EntryGamePlace && place instanceof PlayerGamePlace))
                currentGamePlaces[y][x] = place;
        }catch(ArrayIndexOutOfBoundsException e){
            System.err.println("Character has illegal coordinates, cannot be placed!");
            System.exit(3);
        }
    }
    //true means, that character was moved
    public boolean changeCharacterPlace(Drone gc, int dx, int dy) throws EnemyKilledException, EnterExitException {
        boolean collisionDetected=false;
        //If collision
        try {
            collisionDetected= currentGamePlaces[gc.getPosY()+dy][gc.getPosX()+dx].getCollision(gc);
        }catch(EnemyKilledException | EnterExitException e){
            throw e;
        }catch(KeyCollectedException e){
            PlayerInfo.collectKey();
            originalGamePlaces[gc.getPosY() + dy][gc.getPosX() + dx] = new SpaceGamePlace(PATH);
        }catch(DoorOpenException e){
            originalGamePlaces[gc.getPosY() + dy][gc.getPosX() + dx] = new SpaceGamePlace(PATH);
        } catch(CollisionException e) {
            //Should not happens!
            throw new RuntimeException(e);
        }finally {
            if(!collisionDetected) {
                currentGamePlaces[gc.getPosY()][gc.getPosX()] = originalGamePlaces[gc.getPosY()][gc.getPosX()];
                currentGamePlaces[gc.getPosY() + dy][gc.getPosX() + dx] = gc.getIcon();
                gc.setPos(dx, dy);
            }
        }
        return !collisionDetected;
    }
}


