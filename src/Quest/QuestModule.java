package Quest;

import Game.GameManager;

import java.util.ArrayList;

public class QuestModule {

    private final ArrayList<Quest> quests;
    private int pointedQuest;

    public QuestModule() {
        this.quests = new ArrayList<>();
        pointedQuest=-1;
    }

    public ArrayList<Quest> getQuests() {
        return quests;
    }

    public boolean tryEndQuest(Quest quest){
        if(!quest.isQuestDone())
            return false;
        quests.remove(quest);
        GameManager.getLootModule().getLoot(quest.getReward());
        return true;
    }

    public int getPointedQuest() {
        return pointedQuest;
    }

    public void setPointedQuest(int pointedQuest) {
        this.pointedQuest = pointedQuest;
    }
}
