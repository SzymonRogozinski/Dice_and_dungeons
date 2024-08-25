package Walking;

import dg.generator.Generator;
import dg.generator.dungeon.Coordinate;
import dg.generator.dungeon.Map;
import dg.generator.dungeon.Place;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;

public class MapCreator {

    private Map map;
    private Random random;

    public MapCreator(){
        random=new Random();
    }

    public MapCreator(int seed){
        random=new Random(seed);
    }

    public Map getMap(){
        return map;
    }

    public boolean createMap(int selectedAlg,int width,int height,int size,int enemies,int treasures,int vaults){
        try{
            Generator.setRandom(random);
            map=Generator.generateDungeon(selectedAlg,width,height,size,enemies,treasures,vaults);
        }catch (Exception e){
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public void writeMap(String filename,String texturePath) throws Exception {
        BufferedWriter writer=new BufferedWriter(new FileWriter(filename));
        ArrayList<Coordinate> enemies=new ArrayList<>();
        StringBuilder mapStringify= new StringBuilder();
        for(int y=0;y<map.getHeight();y++){
            for(int x=0;x<map.getWidth();x++){
                Place p=map.getTerrain(x,y);
                switch(p){
                    case null -> mapStringify.append(" ");
                    case FLOOR, VOID, ENTRIES -> mapStringify.append(" ");
                    case ENEMY -> {
                        enemies.add(new Coordinate(x,y));
                        mapStringify.append(" ");
                    }
                    case DOOR -> mapStringify.append("D");
                    case TREASURE -> mapStringify.append("T");
                    case KEY -> mapStringify.append("K");
                    case WALL -> mapStringify.append("W");
                    default -> throw new Exception("Something goes wrong while writing map!");
                }
            }
            mapStringify.append("\n");
        }
        //Enemies
        writer.write("Enemies:\n");
        writer.write(texturePath+"/\n");
        writer.write(enemies.size()+"\n");
        for(Coordinate enemy: enemies)
            writer.write(enemy.x+" "+ enemy.y+"\n");

        //Map
        writer.write("Map:\n");
        writer.write(texturePath+"/\n");
        writer.write(map.getWidth()+" "+map.getHeight()+"\n");
        writer.write(mapStringify.toString());

        //Entries
        writer.write("Entries:\n");
        writer.write(map.getEntries()[0].x+" "+map.getEntries()[0].y+"\n");
        writer.write(map.getEntries()[1].x+" "+map.getEntries()[1].y);

        writer.close();
    }
}
