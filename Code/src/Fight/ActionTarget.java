package Fight;

public enum ActionTarget {
    ENEMY_CHARACTER ("enemy"),
    ALL_ENEMIES ("all enemy"),
    PLAYER_CHARACTER ("player"),
    PLAYER_PARTY ("party");

    private final String name;

    ActionTarget(String name){
        this.name=name;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
