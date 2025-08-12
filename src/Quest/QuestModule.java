package Quest;

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

    public int getPointedQuest() {
        return pointedQuest;
    }

    public void setPointedQuest(int pointedQuest) {
        this.pointedQuest = pointedQuest;
    }
}
