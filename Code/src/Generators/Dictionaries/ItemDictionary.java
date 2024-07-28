package Generators.Dictionaries;

import Game.GameCollection;
import Game.Tags;

import java.util.Map;

public class ItemDictionary {

    private static final Map<Tags,String[]> CLASS_TAGS = Map.of(
            Tags.WARRIOR,new String[]{"warrior","mighty","courage"},
            Tags.MAGE,new String[]{"mage","magic","full of energy"},
            Tags.THIEF,new String[]{"rouge", "sly","shifty"}
    );

    public static String getNameFromTag(Tags tag){
        String[] names = CLASS_TAGS.get(tag);
        if(names==null)
            throw new RuntimeException("Tag "+tag+" has no name!");
        return names[GameCollection.random.nextInt(names.length)];
    }
}
