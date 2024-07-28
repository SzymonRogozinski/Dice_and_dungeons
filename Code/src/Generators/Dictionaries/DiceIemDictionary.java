package Generators.Dictionaries;

import Dice.ActionEnum;

public class DiceIemDictionary extends ItemDictionary {

    public static final int DEFENCE_ACTIONS_ID = 0;
    public static final int SUPPORT_ACTIONS_ID = 1;
    public static final int CURSE_ENEMY_ACTIONS_ID = 2;
    private static final int[] DEFENCE_ACTION=new int[]{ActionEnum.SHIELD_ACTION,ActionEnum.COUNTER_ACTION};
    private static final int[] SUPPORT_ACTION=new int[]{ActionEnum.HEAL_ACTION,ActionEnum.MANA_ACTION};
    private static final int[] CURSE_ENEMY_ACTION=new int[]{ActionEnum.BLEEDING_ACTION,ActionEnum.POISON_ACTION,ActionEnum.WEAKNESS_ACTION};


}
