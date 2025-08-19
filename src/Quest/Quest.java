package Quest;

import Equipment.Items.Item;

import java.util.ArrayList;

public class Quest {

    private final String questName;
    private final String questDescription;
    private final ArrayList<Item> reward;
    private boolean questDone;

    public Quest(String questName, String questDescription,ArrayList<Item> reward) {
        this.questName = questName;
        this.questDescription = questDescription;
        this.reward=reward;
        questDone=false;
    }

    public ArrayList<Item> getReward() {
        return reward;
    }

    public String getQuestName() {
        return questName;
    }

    public String getQuestDescription() {
        return questDescription;
    }

    public boolean isQuestDone() {
        return questDone;
    }

    public void questWasDone() {
        questDone=true;
    }
}
