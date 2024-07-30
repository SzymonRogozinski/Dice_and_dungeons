package Generators.Dictionaries;

import Dice.ActionEnum;
import Game.GameCollection;
import Game.Tags;
import Generators.DiceItemBase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class ItemDictionary {

    private static final double CHANCE_FOR_FIRST_ACTION_NAME=0.3;

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
            ActionEnum.DAMAGE_ACTION,new String[]{"%s of sharpness"},
            ActionEnum.SHIELD_ACTION,new String[]{"guardian %s"},
            ActionEnum.HEAL_ACTION,new String[]{"%s of restorement"},
            ActionEnum.MANA_ACTION,new String[]{"%s of enlightenment"},
            ActionEnum.POISON_ACTION,new String[]{"poison %s"},
            ActionEnum.BLEEDING_ACTION,new String[]{"serrate %s"},
            ActionEnum.WEAKNESS_ACTION,new String[]{"cursed %s"},
            ActionEnum.COUNTER_ACTION,new String[]{"%s of payback"}
    );

    public static String getNameFromTag(Tags tag){
        String[] names = CLASS_TAGS.get(tag);
        if(names==null)
            throw new RuntimeException("Tag "+tag+" has no name!");
        return names[GameCollection.random.nextInt(names.length)];
    }

    public static Tags[] getTagsFromAction(int action1,int action2){
        ArrayList<Tags> tags=new ArrayList<>();
        Tags[] t1 = ACTION_TAGS.get(action1);
        Tags[] t2 = ACTION_TAGS.get(action2);
        tags.addAll(Arrays.asList(t1));
        tags.addAll(Arrays.asList(t2));
        return (Tags[]) tags.toArray();
    }

    public static String getNameFromItemBase(DiceItemBase base){
        String name="";
        //Base name
        name = base.names[GameCollection.random.nextInt(base.names.length)];
        //Class-Tag name
        name = getNameFromTag(base.tags.get(0))+" "+name;

        //ADJECTIVES names
        if(GameCollection.random.nextDouble()<=CHANCE_FOR_FIRST_ACTION_NAME) {
            String[] adjNames = ADJECTIVES.get(base.firstAction);
            String adj = adjNames[GameCollection.random.nextInt(adjNames.length)];
            name = String.format(adj,name);
        }
        if(base.secondAction!=ActionEnum.NULL_ACTION){
            String[] adjNames = ADJECTIVES.get(base.secondAction);
            String adj = adjNames[GameCollection.random.nextInt(adjNames.length)];
            name = String.format(adj,name);
        }
        return name;
    }
}
