package Generators.StoryGenerator;

import Dialog.DialogLine;
import Dialog.DialogQuestLine;
import Dialog.NPCLines;
import Equipment.Items.Item;
import Equipment.Items.ItemQuality;
import Equipment.Items.UsableItem;
import Game.GameManager;
import Generators.ItemGenerators.ArmorGenerator;
import Generators.ItemGenerators.DiceItemGenerator;
import Generators.ItemGenerators.SpellItemGenerator;
import Generators.ItemGenerators.UsableItemGenerator;
import Quest.Quest;
import Trade.Trader;

import java.util.ArrayList;

public class NPCGenerator {

    private static final String[] NPC_NAMES = new String[]{"John", "Mystery person", "Man in hood"};

    public static NPCLines generateNPC(ArrayList<Quest> quests, ItemQuality quality, int itemCount){
        DialogLine[] lines = generateDialogLines(quests);
        String npcName=generateNPCName();

        return new NPCLines(npcName,lines,generateTrader(quality, itemCount));
    }

    private static String generateNPCName(){
        return NPC_NAMES[GameManager.getRandom().nextInt(NPC_NAMES.length)];
    }

    private static DialogLine[] generateDialogLines(ArrayList<Quest> quests){
        ArrayList<Integer> entryResponse=new ArrayList<>();
        DialogLine[] dialog = new DialogLine[1+ quests.size()*2];
        int i=0;
        //Add quests
        for(Quest quest:quests){
            entryResponse.add(++i);
            dialog[i] = new DialogQuestLine("Start quest","Ok","",new int[]{i+1},true,quest);
            dialog[++i] = new DialogQuestLine("End quest","You do not finish quest","Well done",new int[]{},false,quest);
        }
        int[] startDialogBranch = new int[entryResponse.size()];
        for(i=0;i<entryResponse.size();i++)
            startDialogBranch[i]=entryResponse.get(i);

        //Add start dialog
        dialog[0] = new DialogLine("Hi","Good evening",startDialogBranch ,true);

        return dialog;
    }

    private static Trader generateTrader(ItemQuality quality, int itemCount){
        ArrayList<Item> items = new ArrayList<>(UsableItemGenerator.generateTrader(quality));
        for (int i = 0; i < itemCount; i++) {
            items.add(ArmorGenerator.generateArmor(quality));
            items.add(DiceItemGenerator.generateItem(quality));
            items.add(SpellItemGenerator.generateItem(quality));
        }

        return new Trader(items);
    }
}
