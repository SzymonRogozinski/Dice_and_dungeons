package Fight.GameActions;

import Dice.DiceAction.DiceAction;
import Character.Enemy.EnemyActionFactory;
import Character.Enemy.EnemyCharacter;
import Fight.ActionTarget;
import Fight.Tags;

import java.util.ArrayList;

public class EnemyAction extends GameAction{

    private final ArrayList<EnemyActionFactory> actionFactories;

    public EnemyAction(ActionTarget target, Tags[] tags, ArrayList<EnemyActionFactory> actionFactories) {
        super("", null, target, null, tags);
        this.actionFactories =actionFactories;
    }

    public ArrayList<DiceAction> getConstActions(EnemyCharacter enemy){
        ArrayList<DiceAction> result= new ArrayList<>();
        for(EnemyActionFactory factory:actionFactories){
            result.add(factory.makeAction(enemy));
        }
        return result;
    }


    public String asString(EnemyCharacter enemy){
        String action="";
        for(EnemyActionFactory factory:actionFactories){
            action+=factory.makeAction(enemy).actionDescription()+" ";
        }
        return action;
    }
}
