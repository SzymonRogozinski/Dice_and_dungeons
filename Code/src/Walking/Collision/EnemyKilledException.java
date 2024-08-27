package Walking.Collision;

import Walking.Drones.Drone;

public class EnemyKilledException extends CollisionException{

    private final Drone reference;
    public EnemyKilledException(Drone reference){
        super();
        this.reference=reference;
    }

    public Drone getReference(){
        return reference;
    }
}
