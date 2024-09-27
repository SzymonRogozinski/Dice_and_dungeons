package Dice;

import java.util.Map;

public class ActionEnum {
    public static final int NULL_ACTION=0;
    public static final int DAMAGE_ACTION=1;
    public static final int SHIELD_ACTION=2;
    public static final int HEAL_ACTION=3;
    public static final int MANA_ACTION=4;
    public static final int POISON_ACTION=5;
    public static final int BLEEDING_ACTION=6;
    public static final int WEAKNESS_ACTION=7;
    public static final int COUNTER_ACTION=8;
    public static final int STUN_ACTION=9;

    public static final Map<Integer,Integer> ACTION_COST=Map.of(
            ActionEnum.NULL_ACTION,0,
            ActionEnum.DAMAGE_ACTION,1,
            ActionEnum.SHIELD_ACTION,1,
            ActionEnum.HEAL_ACTION,2,
            ActionEnum.MANA_ACTION,2,
            ActionEnum.POISON_ACTION,3,
            ActionEnum.BLEEDING_ACTION,5,
            ActionEnum.WEAKNESS_ACTION,4,
            ActionEnum.COUNTER_ACTION,2
    );

    public static int actionCost(int action){
        return ACTION_COST.get(action);
    }
}
