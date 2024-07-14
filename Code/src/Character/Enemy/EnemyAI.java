package Character.Enemy;

import Character.PlayerCharacter;
import Fight.ActionTarget;
import Fight.GameActions.EnemyAction;

import java.util.ArrayList;
import java.util.Random;

public class EnemyAI {
    private final ArrayList<EnemyAction> actionList;
    private final Random random;
    private int actionCounter;
    private int targetId;

    public EnemyAI(ArrayList<EnemyAction> actionList) {
        this.actionList=actionList;
        actionCounter=0;
        random=new Random();
    }

    public EnemyAction getAction(ArrayList<EnemyCharacter> enemies, ArrayList<PlayerCharacter> characters){
        setTargetId(enemies,characters);
        int c = actionCounter++;
        if(actionCounter>=actionList.size())
            actionCounter=0;
        return actionList.get(c);
    }

    public String getNextAction(EnemyCharacter enemy){
        EnemyAction action = actionList.get(actionCounter);
        return "Next move: " + action.asString(enemy);
    }

    public void setTargetId(ArrayList<EnemyCharacter> enemies,ArrayList<PlayerCharacter> characters){
        EnemyAction action = actionList.get(actionCounter);
        targetId=-1;
        if(action.getTarget() == ActionTarget.ENEMY_CHARACTER || action.getTarget() ==ActionTarget.ALL_ENEMIES){
            while(targetId==-1 || enemies.get(targetId).getCurrentHealth()<=0)
                targetId=random.nextInt(enemies.size());
        }else{
            targetId=random.nextInt(characters.size());
        }
    }

    public int getTargetId(){return targetId;}

}
