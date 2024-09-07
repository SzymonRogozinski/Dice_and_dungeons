package Walking;

public class WalkingSettings{
    // 0-> No predefine
    public final int seed,algGen,width,height,size,enemies,treasures,vaults;
    public final String path;
    public WalkingSettings(String fileLine){
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

    public WalkingSettings(int seed,int algGen,int width,int height,int size,int enemies,int treasures,int vaults, String path){
        this.seed=seed;
        this.algGen=algGen;
        this.width=width;
        this.height=height;
        this.size=size;
        this.enemies=enemies;
        this.treasures=treasures;
        this.vaults=vaults;
        this.path=path;
    }
}
