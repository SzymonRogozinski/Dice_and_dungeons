package Dialog;

import Game.GameManager;

import java.util.ArrayList;

public class NPCLines {

    private final String name;
    private final DialogLine[] dialogLines;
    private final ArrayList<DialogLine> chooseAbleDialogLines;
    private String currentResponse;

    public NPCLines(String name, DialogLine[] dialogLines) {
        this.name = name;
        currentResponse="";
        this.dialogLines = dialogLines;
        chooseAbleDialogLines =new ArrayList<>();
        chooseAbleDialogLines.add(dialogLines[0]);   //Start dialog
    }

    public String getName() {
        return name;
    }

    public String getCurrentResponse() {
        return currentResponse;
    }

    public ArrayList<DialogLine> getChooseAbleDialogLines() {
        return chooseAbleDialogLines;
    }

    public void chooseDialog(int id){
        DialogLine line = chooseAbleDialogLines.get(id);
        currentResponse= line.getResponse();
        //Check if quest line
        if(line instanceof DialogQuestLine questLine){
            //Add quest
            if(questLine.isStartQuestLine())
                GameManager.getQuestModule().getQuests().add(questLine.getQuest());
            //End quest
            else if(GameManager.getQuestModule().tryEndQuest(questLine.getQuest()))
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
}
