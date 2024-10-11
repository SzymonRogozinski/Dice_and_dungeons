package Character.Enemy;

import Character.PlayerCharacter;
import Fight.ActionTarget;
import Fight.GameActions.EnemyAction;
import Game.GameManager;

import java.util.ArrayList;

public class EnemyAI {
    private final ArrayList<EnemyAction> actionList;
    private int actionCounter;
    private int targetId;

    public EnemyAI(ArrayList<EnemyAction> actionList) {
        this.actionList=actionList;
        actionCounter=0;
    }

    public EnemyAction getAction(ArrayList<EnemyCharacter> enemies, ArrayList<PlayerCharacter> characters){
        setTargetId(enemies,characters);
        int counter = actionCounter++;
        if(actionCounter>=actionList.size())
            actionCounter=0;
        return actionList.get(counter);
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
                targetId= GameManager.random.nextInt(enemies.size());
        }else{
            targetId=GameManager.random.nextInt(characters.size());
        }
    }

    public int getTargetId(){return targetId;}

}
