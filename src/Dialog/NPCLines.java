package Dialog;

import Game.GameManager;
import Quest.Quest;
import Trade.Trader;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class NPCLines {

    private final String name;
    private final DialogLine[] dialogLines;
    private final ArrayList<DialogLine> chooseAbleDialogLines;
    private final Trader trader;
    private String currentResponse;

    public NPCLines(String name, DialogLine[] dialogLines, Trader trader) {
        this.name = name;
        currentResponse="";
        this.dialogLines = dialogLines;
        this.trader=trader;
        chooseAbleDialogLines =new ArrayList<>();
        chooseAbleDialogLines.add(dialogLines[0]);   //Start dialog
    }

    public NPCLines(JSONObject npcJson, ArrayList<Quest> quests) {
        this.name = npcJson.getString("Name");
        currentResponse="";
        this.dialogLines = loadDialogLines(npcJson.getJSONArray("Dialogs"),npcJson.getJSONArray("QuestDialogs"),quests);
        this.trader=new Trader(npcJson.getJSONObject("Trader"));
        chooseAbleDialogLines =new ArrayList<>();
        chooseAbleDialogLines.add(dialogLines[0]);   //Start dialog
    }

    public void setDefaultResponse(){
        currentResponse="";
    }

    public String getName() {
        return name;
    }

    public String getCurrentResponse() {
        return currentResponse;
    }

    public Trader getTrader() {
        return trader;
    }

    public ArrayList<DialogLine> getChooseAbleDialogLines() {
        return chooseAbleDialogLines;
    }

    public void chooseDialog(int id){
        DialogLine line = chooseAbleDialogLines.get(id);
        currentResponse= line.getResponse();
        //Check if quest line
        if(line instanceof DialogQuestLine questLine){
            if(questLine.isStartQuestLine()) { //Add quest
                questLine.getQuest().startQuest();
                GameManager.getQuestModule().getQuests().add(questLine.getQuest());
            }else if(GameManager.getQuestModule().tryEndQuest(questLine.getQuest())) //End quest
                chooseAbleDialogLines.remove(id);
        }
        //Line was read, do nothing
        if(line.isRead())
            return;
        line.setRead();
        for(int next: line.getNextDialogs())
            chooseAbleDialogLines.add(dialogLines[next]);

        if(line.isRemoveAfterUsed())
            chooseAbleDialogLines.remove(id);
    }

    private DialogLine[] loadDialogLines(JSONArray dialogs, JSONArray questDialog, ArrayList<Quest> quests){
        DialogLine[] dialogLines = new DialogLine[dialogs.length()+questDialog.length()];
        for (int i = 0; i < dialogs.length(); i++) {
            JSONObject json = dialogs.getJSONObject(i);
            dialogLines[json.getInt("id")] = new DialogLine(json.getString("line"),
                    json.getString("response"),
                    castJSONArrayToIntArray(json.getJSONArray("nextDialogs")),
                    json.getBoolean("removeAfterUsed"));
        }
        for (int i = 0; i < questDialog.length(); i++) {
            JSONObject json = questDialog.getJSONObject(i);
            dialogLines[json.getInt("id")] = new DialogQuestLine(json.getString("line"),
                    json.getString("startResponse"),
                    json.getString("endResponse"),
                    castJSONArrayToIntArray(json.getJSONArray("nextDialogs")),
                    json.getBoolean("startQuestLine"),
                    quests.get(json.getInt("questId")));
        }
        return dialogLines;
    }

    private int[] castJSONArrayToIntArray(JSONArray jsonArray){
        int[] arr = new int[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++)
            arr[i] = jsonArray.getInt(i);
        return arr;
    }
}
