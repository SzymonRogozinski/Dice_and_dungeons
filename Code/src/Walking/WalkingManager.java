package Walking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class WalkingManager {
    private WalkingModule walking;
    private final ArrayList<WalkingSettings> settings;
    private int pointer;

    public WalkingManager(String filename) throws Exception {
        settings=new ArrayList<>();
        pointer=1;
        BufferedReader reader=new BufferedReader(new FileReader(filename));
        int levels=Integer.parseInt(reader.readLine());
        for(int i=0; i<levels;i++){
            settings.add(new WalkingSettings(reader.readLine()));
        }
        WalkingSettings startSetting=settings.get(0);
        walking = new WalkingModule(this,startSetting.seed, startSetting.algGen,startSetting.width,startSetting.height,startSetting.size, startSetting.enemies, startSetting.treasures,startSetting.vaults,startSetting.path);
    }

    public WalkingModule getWalking(){
        return walking;
    }

    public void setNextMap() throws Exception {
        if(pointer>=settings.size())
            throw new Exception("Cannot load new map!");
        try {
            walking.killModule();
            WalkingSettings startSetting = settings.get(pointer);
            walking = new WalkingModule(this, startSetting.seed, startSetting.algGen, startSetting.width, startSetting.height, startSetting.size, startSetting.enemies, startSetting.treasures, startSetting.vaults, startSetting.path);
            pointer++;
            walking.walkingStart();
        }catch (Exception ignore){}
    }

    private class WalkingSettings{
        // 0-> No predefine
        final int seed,algGen,width,height,size,enemies,treasures,vaults;
        final String path;
        WalkingSettings(String fileLine){
            String[] settings=fileLine.split(",");
            this.seed=Integer.parseInt(settings[0]);
            this.algGen=Integer.parseInt(settings[1]);
            this.width=Integer.parseInt(settings[2]);
            this.height=Integer.parseInt(settings[3]);
            this.size=Integer.parseInt(settings[4]);
            this.enemies=Integer.parseInt(settings[5]);
            this.treasures=Integer.parseInt(settings[6]);
            this.vaults=Integer.parseInt(settings[7]);
            this.path=settings[8];
        }
    }
}
