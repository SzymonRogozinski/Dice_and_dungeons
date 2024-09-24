package Walking;

import GUI.WalkingGUI.WalkingGUIState;
import Game.GameBalance;
import Game.GameConst;
import Game.GameManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class WalkingModule {
    private WalkingLevel walking;
    private final WalkingGUIState state;

    public WalkingModule(WalkingGUIState state){
        this.state=state;
        walking = new WalkingLevel(GameManager.getCurrentLevel());
    }

    public WalkingLevel getWalking(){
        return walking;
    }

    public WalkingGUIState getState() {
        return state;
    }

    public void setNextMap() throws Exception {
        if(GameManager.getLevelPointer()+1 >= GameConst.LEVELS.size())
            throw new Exception("Cannot load new map!");
        try {
            walking.killModule();
            GameManager.setNextLevel();
            walking = new WalkingLevel(GameManager.getCurrentLevel());
            walking.walkingStart();
        }catch (Exception ignore){}
    }

    public void startWalking(){
        if(walking.walkingRunning()){
            walking.walkingContinue();
        }else{
            walking.walkingStart();
        }
    }

    public void stopWalking(){
        walking.walkingStop();
    }
}
