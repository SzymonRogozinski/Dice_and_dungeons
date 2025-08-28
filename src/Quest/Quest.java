package Quest;

import Equipment.Items.Item;
import org.json.JSONObject;

import java.util.ArrayList;

public class Quest {

    private final String questName;
    private final String questDescription;
    private final QuestType questType;
    private final ArrayList<Item> reward;
    private boolean questDone;

    public Quest(String questName, String questDescription, QuestType questType,ArrayList<Item> reward) {
        this.questName = questName;
        this.questDescription = questDescription;
        this.questType=questType;
        this.reward=reward;
        questDone=false;
    }

    public Quest(JSONObject questJson,ArrayList<Item> reward){
        this.questName = questJson.getString("questName");
        this.questDescription = questJson.getString("questDescription");
        switch (questJson.getString("questType")){
            case "CHEST_OPEN" -> this.questType=QuestType.CHEST_OPEN;
            case "ENEMY_DEFEAT" -> this.questType=QuestType.ENEMY_DEFEAT;
            case null, default -> throw new RuntimeException("Error while loading quest. QuestType cannot be null.");
        }

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

    public QuestType getQuestType() {
        return questType;
    }

    public boolean isQuestDone() {
        return questDone;
    }

    public void questWasDone() {
        questDone=true;
    }
}
