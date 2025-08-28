package Generators.StoryGenerator;

import Dialog.DialogLine;
import Dialog.DialogQuestLine;
import Dialog.NPCLines;
import Game.GameManager;
import Quest.Quest;

import java.util.ArrayList;

public class NPCGenerator {

    private static final String[] NPC_NAMES = new String[]{"John", "Mystery person", "Man in hood"};

    public static NPCLines generateNPC(ArrayList<Quest> quests){
        DialogLine[] lines = generateDialogLines(quests);
        String npcName=generateNPCName();

        return new NPCLines(npcName,lines);
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
}
