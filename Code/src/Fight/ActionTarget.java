package Fight;

public enum ActionTarget {
    ENEMY_CHARACTER ("enemy"),
    ALL_ENEMIES ("all enemy"),
    PLAYER_CHARACTER ("player"),
    PLAYER_PARTY ("party"),
    SELF ("self");

    private final String name;

    private ActionTarget(String name){
        this.name=name;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
