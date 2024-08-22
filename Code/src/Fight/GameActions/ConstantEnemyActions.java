package Fight.GameActions;

import Character.Enemy.EnemyActionFactory;
import Fight.ActionTarget;
import Game.Tags;

import java.util.ArrayList;
import java.util.List;

public class ConstantEnemyActions {
    public static EnemyAction attack = new EnemyAction(ActionTarget.PLAYER_CHARACTER,new Tags[]{Tags.ATTACK}, new ArrayList<>(List.of(new EnemyActionFactory(e->e.getStrength(),0.2,false,1))));
    public static EnemyAction strongAttack = new EnemyAction(ActionTarget.PLAYER_CHARACTER,new Tags[]{Tags.ATTACK}, new ArrayList<>(List.of(new EnemyActionFactory(e->e.getStrength(),0.4,false,1))));

}
