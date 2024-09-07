package Walking;

import GUI.WalkingGUI.WalkingGUIState;
import Game.GameBalance;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class WalkingModule {
    private WalkingLevel walking;
    private final ArrayList<WalkingSettings> settings;
    private int levelPointer;
    private final WalkingGUIState state;

    public WalkingModule(WalkingGUIState state){
        this.state=state;
        settings=new ArrayList<>();
        levelPointer =1;
        walking = new WalkingLevel(this,GameBalance.LEVELS.get(0).getWalkingSettings());
    }

    public WalkingLevel getWalking(){
        return walking;
    }

    public WalkingGUIState getState() {
        return state;
    }

    public void setNextMap() throws Exception {
        if(levelPointer >=settings.size())
            throw new Exception("Cannot load new map!");
        try {
            walking.killModule();
            WalkingSettings startSetting = GameBalance.LEVELS.get(levelPointer).getWalkingSettings();
            walking = new WalkingLevel(this, startSetting);
            levelPointer++;
            walking.walkingStart();
        }catch (Exception ignore){}
    }
}
