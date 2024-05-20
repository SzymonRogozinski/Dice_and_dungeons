package Character.Enemy;

import Dice.DiceAction.*;

public class EnemyActionFactory {

    public static final int NULL_ACTION=0;
    public static final int DAMAGE_ACTION=1;
    public static final int SHIELD_ACTION=2;
    public static final int HEAL_ACTION=3;
    public static final int POISON_ACTION=4;
    public static final int BLEEDING_ACTION=5;
    public static final int WEAKNESS_ACTION=6;
    public static final int COUNTER_ACTION=7;
    public static final int STUN_ACTION=8;

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

    public DiceAction makeAction(EnemyCharacter enemy){
        int value = (int)(enemy.getStatisticMod()*mod*lambda.myMethod(enemy));
        DiceAction action;
        switch (actionType){
            case NULL_ACTION -> action=new NullAction();
            case DAMAGE_ACTION -> action = new DamageAction(value);
            case SHIELD_ACTION -> action = new ShieldAction(value,onSelf);
            case HEAL_ACTION -> action = new HealAction(value,onSelf);
            case POISON_ACTION -> action = new PoisonAction(value);
            case BLEEDING_ACTION -> action = new BleedingAction(value);
            case WEAKNESS_ACTION -> action = new WeaknessAction(value);
            case COUNTER_ACTION -> action = new CounterAction(value,onSelf);
            case STUN_ACTION -> action = new StunAction();
            default -> throw new IllegalArgumentException("actionType not found!");
        }
        return action;
    }
}
