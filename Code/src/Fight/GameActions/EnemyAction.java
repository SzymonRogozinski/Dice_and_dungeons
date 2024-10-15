package Fight.GameActions;

import Character.Enemy.EnemyActionFactory;
import Character.Enemy.EnemyCharacter;
import Dice.DiceAction.DiceAction;
import Fight.ActionTarget;
import Game.Tags;

import java.util.ArrayList;

public class EnemyAction extends GameAction {

    private final ArrayList<EnemyActionFactory> actionFactories;

    public EnemyAction(ActionTarget target, Tags[] tags, ArrayList<EnemyActionFactory> actionFactories) {
        super(null, target, null, tags);
        this.actionFactories = actionFactories;
    }

    public ArrayList<DiceAction> getConstActions(EnemyCharacter enemy) {
        ArrayList<DiceAction> result = new ArrayList<>();
        for (EnemyActionFactory factory : actionFactories)
            result.add(factory.makeAction(enemy));
        return result;
    }


    public String asString(EnemyCharacter enemy) {
        StringBuilder action = new StringBuilder();
        for (EnemyActionFactory factory : actionFactories)
            action.append(factory.makeAction(enemy).actionDescription()).append(" ");
        return action.toString();
    }
}
