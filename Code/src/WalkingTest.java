import Walking.MapCreator;
import dg.generator.Generator;

public class WalkingTest {

    public static void main(String[] args) {
        MapCreator mc=new MapCreator();
        boolean success=mc.createMap(Generator.CAVE_ALGORITHM,100,100,1000,30,20,2);
        System.out.println("Success? "+success);
        try {
            mc.writeMap("test_save.txt", "test_saving");
        }catch(Exception ignore){}
    }
}
