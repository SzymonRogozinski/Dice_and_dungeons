package Walking;

import dg.generator.Generator;
import dg.generator.dungeon.Map;

import java.util.Random;

public class MapCreator {

    private Map map;
    private final Random random;

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
}
