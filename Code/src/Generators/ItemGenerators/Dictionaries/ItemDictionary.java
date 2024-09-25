package Generators.ItemGenerators.Dictionaries;

import Dice.ActionEnum;
import Fight.ActionTarget;
import Game.GameManager;
import Game.Tags;
import Generators.ItemGenerators.DiceItemBase;

import java.util.Arrays;
import java.util.Map;

public class ItemDictionary {

    private static final double CHANCE_FOR_FIRST_ACTION_NAME=0.3;

    private static final String[] WIDE_RANGE=new String[]{"almighty", "big", "two-handed", "broad"};

    private static final Map<Tags,String[]> CLASS_TAGS = Map.of(
            Tags.WARRIOR,new String[]{"warrior","mighty","courage"},
            Tags.MAGE,new String[]{"mage","magic","full of energy"},
            Tags.THIEF,new String[]{"rouge", "sly","shifty"}
    );

    private static final Map<Integer,Tags[]> ACTION_TAGS = Map.of(
            ActionEnum.NULL_ACTION,new Tags[]{},
            ActionEnum.DAMAGE_ACTION,new Tags[]{Tags.ATTACK},
            ActionEnum.SHIELD_ACTION,new Tags[]{Tags.DEFENCE},
            ActionEnum.HEAL_ACTION,new Tags[]{},
            ActionEnum.MANA_ACTION,new Tags[]{Tags.MAGIC},
            ActionEnum.POISON_ACTION,new Tags[]{},
            ActionEnum.BLEEDING_ACTION,new Tags[]{},
            ActionEnum.WEAKNESS_ACTION,new Tags[]{},
            ActionEnum.COUNTER_ACTION,new Tags[]{Tags.DEFENCE}
    );

    private static final Map<Integer,String[]> ADJECTIVES = Map.of(
            ActionEnum.DAMAGE_ACTION,new String[]{"%s of sharpness","painfull %s"},
            ActionEnum.SHIELD_ACTION,new String[]{"guardian %s", "%s of defence"},
            ActionEnum.HEAL_ACTION,new String[]{"%s of restorement", "helpfull %s"},
            ActionEnum.MANA_ACTION,new String[]{"%s of enlightenment","%s mystic", "energizing %s"},
            ActionEnum.POISON_ACTION,new String[]{"poison %s", "toxic %s", "venomous %s"},
            ActionEnum.BLEEDING_ACTION,new String[]{"serrate %s","bloody %s"},
            ActionEnum.WEAKNESS_ACTION,new String[]{"cursed %s", "%s of frailty"},
            ActionEnum.COUNTER_ACTION,new String[]{"%s of payback", "counter %s"}
    );

    private static final Map<Integer,String[]> ADJECTIVES_FOR_SPELLS = Map.of(
            ActionEnum.DAMAGE_ACTION,new String[]{"%s of destruction","painfull %s"},
            ActionEnum.SHIELD_ACTION,new String[]{"guardian %s", "rescue %s"},
            ActionEnum.HEAL_ACTION,new String[]{"%s of restorement","%s of regeneration"},
            ActionEnum.POISON_ACTION,new String[]{"poison %s","venomous %s"},
            ActionEnum.BLEEDING_ACTION,new String[]{"serrate %s","bloody %s"},
            ActionEnum.WEAKNESS_ACTION,new String[]{"cursed %s", "%s of frailty"},
            ActionEnum.COUNTER_ACTION,new String[]{"%s of payback", "return %s"}
    );

    public static String getNameFromTag(Tags tag){
        String[] names = CLASS_TAGS.get(tag);
        if(names==null)
            throw new RuntimeException("Tag "+tag+" has no name!");
        return names[GameManager.random.nextInt(names.length)];
    }

    public static Tags[] getTagsFromAction(int action1,int action2){
        Tags[] t1 = ACTION_TAGS.get(action1);
        Tags[] t2 = ACTION_TAGS.get(action2);
        Tags[] result = Arrays.copyOf(t1,t1.length+t2.length);
        System.arraycopy(t2,0,result,t1.length,t2.length);
        return result;
    }

    public static Tags[] getTagsFromActionSpellAction(int action1,int action2){
        Tags[] t1 = ACTION_TAGS.get(action1);
        Tags[] t2 = ACTION_TAGS.get(action2);
        Tags[] result = Arrays.copyOf(t1,t1.length+t2.length+1);
        System.arraycopy(t2,0,result,t1.length,t2.length);
        result[result.length-1]=Tags.MAGIC;
        return result;
    }

    public static String getItemNameFromItemBase(DiceItemBase base,String shortName){
        String name;
        //Base name
        name = shortName;
        //Class-Tag name
        if(!base.tags.isEmpty())
            name = getNameFromTag(base.tags.get(0))+" "+name;
        //ADJECTIVES names
        if(GameManager.random.nextDouble()<=CHANCE_FOR_FIRST_ACTION_NAME) {
            String[] adjNames = ADJECTIVES.get(base.firstAction);
            String adj = adjNames[GameManager.random.nextInt(adjNames.length)];
            name = String.format(adj,name);
        }
        if(base.secondAction!=ActionEnum.NULL_ACTION){
            String[] adjNames = ADJECTIVES.get(base.secondAction);
            String adj = adjNames[GameManager.random.nextInt(adjNames.length)];
            name = String.format(adj,name);
        }
        return name;
    }

    public static String getSpellNameFromItemBase(DiceItemBase base,String shortName){
        String name=shortName;
        //Range name
        if(base.target== ActionTarget.PLAYER_PARTY || base.target==ActionTarget.ALL_ENEMIES)
            name = WIDE_RANGE[GameManager.random.nextInt(WIDE_RANGE.length)]+" "+name;
        //Class-Tag name
        if(!base.tags.isEmpty())
            name = getNameFromTag(base.tags.get(0))+" "+name;
        //ADJECTIVES_FOR_SPELLS names
        if(GameManager.random.nextDouble()<=CHANCE_FOR_FIRST_ACTION_NAME) {
            String[] adjNames = ADJECTIVES_FOR_SPELLS.get(base.firstAction);
            String adj = adjNames[GameManager.random.nextInt(adjNames.length)];
            name = String.format(adj,name);
        }
        if(base.secondAction!=ActionEnum.NULL_ACTION){
            String[] adjNames = ADJECTIVES_FOR_SPELLS.get(base.secondAction);
            String adj = adjNames[GameManager.random.nextInt(adjNames.length)];
            name = String.format(adj,name);
        }
        return name;
    }
}
