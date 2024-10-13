package Walking;

import GUI.WalkingGUI.WalkingGUIState;
import Game.GameConst;
import Game.GameManager;

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
        if(walking.walkingRunning())
            walking.walkingContinue();
        else
            walking.walkingStart();
    }

    public void stopWalking(){
        walking.walkingStop();
    }
}
