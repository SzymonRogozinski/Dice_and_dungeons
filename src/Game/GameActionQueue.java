package Game;

public class GameActionQueue {
    public static synchronized void action(GameAction a)  {
        a.doAction();
    }
}
