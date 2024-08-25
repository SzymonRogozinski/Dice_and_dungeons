package Walking.Drones;

import Walking.Places.GamePlace;

public class PlayerDrone extends Drone{

    private int keyCollected;

    public PlayerDrone(int posX, int posY, GamePlace icon) {
        super(posX, posY, icon);
        this.keyCollected=0;
    }

    public void collectKey(){
        keyCollected++;
    }

    public boolean useKey(){
        if(keyCollected>0){
            keyCollected--;
            return true;
        }
        return false;
    }
}
