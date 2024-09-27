package Walking;

public class WalkingSettings{
    // 0-> No predefine
    public final int seed,algGen,width,height,size,enemies,treasures,vaults;
    public final String path;
    public final boolean bossLevel;

    public WalkingSettings(int seed,int algGen,int width,int height,int size,int enemies,int treasures,int vaults, String path,boolean bossLevel){
        this.seed=seed;
        this.algGen=algGen;
        this.width=width;
        this.height=height;
        this.size=size;
        this.enemies=enemies;
        this.treasures=treasures;
        this.vaults=vaults;
        this.path=path;
        this.bossLevel=bossLevel;
    }
}
