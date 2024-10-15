package Walking.Drones;

import Character.Enemy.EnemyCharacter;
import Game.GameManager;
import Walking.Collision.EnemyFightException;
import Walking.Collision.EnterExitException;
import Walking.GameMap;
import Walking.Places.GamePlace;
import Walking.Places.PlayerGamePlace;
import Walking.Places.SpaceGamePlace;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class EnemyDrone extends Drone {
    private final static double chanceToMove = 0.5;
    private final static int seeRange = 6;
    private final static int[] movement = {0, 1, 1, 0, 0, -1, -1, 0};
    private final GamePlace pursuitIcon;
    private final ArrayList<EnemyCharacter> enemies;
    private ArrayList<NextMove> moves;

    public EnemyDrone(int posX, int posY, GamePlace icon, GamePlace pursuitIcon, ArrayList<EnemyCharacter> enemies) {
        super(posX, posY, icon);
        this.pursuitIcon = pursuitIcon;
        this.pursuitIcon.setReference(this);
        super.getIcon().setReference(this);
        moves = new ArrayList<>();
        this.enemies = enemies;
    }

    public ArrayList<EnemyCharacter> getEnemies() {
        return enemies;
    }

    public boolean ifMove() {
        return !moves.isEmpty() || GameManager.getRandom().nextDouble(0, 1) <= chanceToMove;
    }

    public boolean playerSeen(int playerX, int playerY) {
        return Math.sqrt(Math.pow(playerX - getPosX(), 2) + Math.pow(playerY - getPosY(), 2)) <= seeRange;
    }

    public void ifPlayerWasSeen(GameMap map) {

        Stack<ArrayList<NextMove>> moveStack = new Stack<>();
        Stack<ArrayList<NextMove>> nextMoveStack = new Stack<>();
        //Starting position, it is not move!
        moveStack.push(new ArrayList<>(List.of(new NextMove(0, 0, getPosX(), getPosY()))));

        boolean[][] checkedPlaces = new boolean[seeRange * 2 + 1][seeRange * 2 + 1];
        checkedPlaces[seeRange][seeRange] = true;
        int xOffSet = getPosX() - seeRange;
        int yOffSet = getPosY() - seeRange;

        for (int iter = 0; iter < seeRange; iter++) {
            while (!moveStack.isEmpty()) {
                ArrayList<NextMove> nextMoveList = moveStack.pop();
                NextMove lastMove = nextMoveList.getLast();
                for (int i = 0; i < movement.length; i += 2) {
                    GamePlace place = map.getPlace(lastMove.x + movement[i], lastMove.y + movement[i + 1]);
                    //If player was found
                    if (place instanceof PlayerGamePlace) {
                        nextMoveList.removeFirst();
                        nextMoveList.add(new NextMove(movement[i], movement[i + 1], lastMove.x + movement[i], lastMove.y + movement[i + 1]));
                        moves = nextMoveList;
                        return;
                    }//If space was not checked
                    else if (place instanceof SpaceGamePlace && !checkedPlaces[lastMove.y + movement[i + 1] - yOffSet][lastMove.x + movement[i] - xOffSet]) {
                        ArrayList<NextMove> newMoveList = new ArrayList<>(nextMoveList);
                        newMoveList.add(new NextMove(movement[i], movement[i + 1], lastMove.x + movement[i], lastMove.y + movement[i + 1]));
                        nextMoveStack.add(newMoveList);

                        checkedPlaces[lastMove.y + movement[i + 1] - yOffSet][lastMove.x + movement[i] - xOffSet] = true;
                    }
                }
            }
            moveStack = nextMoveStack;
            nextMoveStack = new Stack<>();
        }
    }

    public void enemyMove(GameMap gameMap) throws EnemyFightException {
        //If some moves was saved
        if (!moves.isEmpty()) {
            try {
                if (gameMap.changeCharacterPlace(this, moves.getFirst().dx, moves.getFirst().dy))
                    moves.removeFirst();
                return;
            } catch (EnterExitException ignored) {
            }
        }
        //If not
        int count = 3;
        int r;
        while (count > 0) {
            r = GameManager.getRandom().nextInt(4);
            try {
                if (gameMap.changeCharacterPlace(this, movement[r * 2], movement[r * 2 + 1]))
                    break;
                else
                    count--;
            } catch (EnterExitException ignored) {
            }
        }
    }

    @Override
    public GamePlace getIcon() {
        return moves.isEmpty() ? super.getIcon() : pursuitIcon;
    }

    private record NextMove(int dx, int dy, int x, int y) {
    }
}
