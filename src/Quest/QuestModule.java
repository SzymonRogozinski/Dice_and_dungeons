package Quest;

import Game.GameManager;

import java.util.ArrayList;

public class QuestModule {

    private final ArrayList<Quest> quests;
    private int pointedQuest;
    private int selectedQuest;

    public QuestModule() {
        this.quests = new ArrayList<>();
        pointedQuest=-1;
        selectedQuest=-1;
    }

    public ArrayList<Quest> getQuests() {
        return quests;
    }

    public boolean tryEndQuest(Quest quest){
        if(!quest.isQuestDone())
            return false;
        int id = quests.indexOf(quest);
        if(selectedQuest==id)
            selectedQuest=-1;
        else if (selectedQuest>id)
            selectedQuest--;
        quests.remove(id);
        GameManager.getLootModule().getLoot(quest.getReward());
        return true;
    }

    public int getPointedQuest() {
        return pointedQuest;
    }

    public void setPointedQuest(int pointedQuest) {
        this.pointedQuest = pointedQuest;
    }

    public int getSelectedQuest() {
        return selectedQuest;
    }

    public void setSelectedQuest(int selectedQuest) {
        this.selectedQuest = selectedQuest;
    }
}
