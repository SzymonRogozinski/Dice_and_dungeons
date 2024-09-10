package Walking.Drones;

import Character.Enemy.EnemyCharacter;
import Walking.Collision.EnemyFightException;
import Walking.Collision.EnterExitException;
import Walking.GameMap;
import Walking.Places.GamePlace;
import Walking.Places.PlayerGamePlace;
import Walking.Places.SpaceGamePlace;

import java.util.*;

public class EnemyDrone extends Drone
{
    private final double chanceToMove=0.5;
    private final static int seeRange=5;
    private final static int[] movement={0,1,1,0,0,-1,-1,0};
    private final Random random;
    private ArrayList<NextMove> moves;
    private final GamePlace pursuitIcon;
    private final ArrayList<EnemyCharacter> enemies;

    public EnemyDrone(int posX, int posY, GamePlace icon, GamePlace pursuitIcon, ArrayList<EnemyCharacter> enemies) {
        super(posX, posY, icon);
        this.pursuitIcon=pursuitIcon;
        this.pursuitIcon.setReference(this);
        super.getIcon().setReference(this);
        random=new Random();
        moves=new ArrayList<>();
        this.enemies=enemies;
    }

    public ArrayList<EnemyCharacter> getEnemies() {
        return enemies;
    }

    public boolean ifMove(){
        return !moves.isEmpty() || random.nextDouble(0, 1) <= chanceToMove;
    }

    public void ifPlayerWasSeen(int playerX, int playerY, GameMap map){
        if(!(Math.sqrt(Math.pow(playerX-getPosX(),2)+Math.pow(playerY-getPosY(),2))<=seeRange))
            return;

        Stack<ArrayList<NextMove>> moveStack=new Stack<>();
        Stack<ArrayList<NextMove>> nextMoveStack=new Stack<>();
        //Starting position, it is not move!
        moveStack.push(new ArrayList<>(List.of(new NextMove(0, 0, getPosX(), getPosY()))));

        boolean[][] checkedPlaces=new boolean[seeRange*2+1][seeRange*2+1];
        checkedPlaces[seeRange][seeRange]=true;
        int xOffSet=getPosX()-seeRange;
        int yOffSet=getPosY()-seeRange;

        for(int iter=0;iter<seeRange;iter++){
            while(!moveStack.isEmpty()){
                ArrayList<NextMove> nextMoveList=moveStack.pop();
                NextMove lastMove=nextMoveList.get(nextMoveList.size()-1);
                for(int i=0;i<movement.length;i+=2){
                    GamePlace place=map.getPlace(lastMove.x+movement[i],lastMove.y+movement[i+1]);
                    //If player was found
                    if(place instanceof PlayerGamePlace){
                        nextMoveList.remove(0);
                        nextMoveList.add(new NextMove(movement[i],movement[i+1],lastMove.x+movement[i],lastMove.y+movement[i+1]));
                        moves=nextMoveList;
                        return;
                    }//If space was not checked
                    else if(place instanceof SpaceGamePlace && !checkedPlaces[lastMove.y+movement[i+1]-yOffSet][lastMove.x+movement[i]-xOffSet]){
                        ArrayList<NextMove> newMoveList= (ArrayList<NextMove>) nextMoveList.clone();
                        newMoveList.add(new NextMove(movement[i],movement[i+1],lastMove.x+movement[i],lastMove.y+movement[i+1]));
                        nextMoveStack.add(newMoveList);

                        checkedPlaces[lastMove.y+movement[i+1]-yOffSet][lastMove.x+movement[i]-xOffSet]=true;
                    }
                }
            }
            moveStack=nextMoveStack;
            nextMoveStack=new Stack<>();
        }
    }

    public void enemyMove(GameMap gameMap) throws EnemyFightException{
        //If some moves was saved
        if(!moves.isEmpty()){
            try {
               if(gameMap.changeCharacterPlace(this, moves.get(0).dx, moves.get(0).dy))
                    moves.remove(0);
                return;
            }catch (EnterExitException ignored) {}
        }
        //If not
        int count=3;
        int r;
        while(count>0) {
            r = random.nextInt(4);
            try {
                if(gameMap.changeCharacterPlace(this, movement[r * 2], movement[r * 2 + 1]))
                    break;
                else
                    count--;
            } catch (EnterExitException ignored) {}
        }
    }

    @Override
    public GamePlace getIcon(){
        return moves.isEmpty()?super.getIcon():pursuitIcon;
    }

    private class NextMove{
        final int dx;
        final int dy;
        final int x;
        final int y;

        public NextMove(int dx, int dy,int x,int y) {
            this.dx = dx;
            this.dy = dy;
            this.x=x;
            this.y=y;
        }
    }
}
