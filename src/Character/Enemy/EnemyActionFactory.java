package Character.Enemy;

import Dice.ActionEnum;
import Dice.DiceAction.*;

public class EnemyActionFactory {
    private final StatLambda lambda;
    private final double mod;
    private final boolean onSelf;
    private final int actionType;

    public EnemyActionFactory(StatLambda lambda, double mod, boolean onSelf, int actionType) {
        this.lambda = lambda;
        this.mod = mod;
        this.onSelf = onSelf;
        this.actionType = actionType;
    }

    public DiceAction makeAction(EnemyCharacter enemy) {
        int value = (int) (enemy.getStatisticMod() * mod * lambda.calcPower(enemy) / ActionEnum.actionCost(actionType));
        value = Math.max(value, 1);
        DiceAction action;
        switch (actionType) {
            case ActionEnum.NULL_ACTION -> action = new NullAction();
            case ActionEnum.DAMAGE_ACTION -> action = new DamageAction(value);
            case ActionEnum.SHIELD_ACTION -> action = new ShieldAction(value, onSelf);
            case ActionEnum.HEAL_ACTION -> action = new HealAction(value, onSelf);
            case ActionEnum.POISON_ACTION -> action = new PoisonAction(value);
            case ActionEnum.BLEEDING_ACTION -> action = new BleedingAction(value);
            case ActionEnum.WEAKNESS_ACTION -> action = new WeaknessAction(value);
            case ActionEnum.COUNTER_ACTION -> action = new CounterAction(value, onSelf);
            case ActionEnum.STUN_ACTION -> action = new StunAction();
            default -> throw new IllegalArgumentException("actionType not found!");
        }
        return action;
    }
}
