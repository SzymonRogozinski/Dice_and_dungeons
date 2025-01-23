package Walking.Collision;

import Walking.Drones.Drone;

public class EnemyFightException extends CollisionException {

    private final Drone reference;

    public EnemyFightException(Drone reference) {
        super();
        this.reference = reference;
    }

    public Drone getReference() {
        return reference;
    }
}
