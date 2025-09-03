package Quest;

import Equipment.Items.Item;
import Game.GameManager;
import Walking.Drones.EnemyDrone;
import Walking.Places.EnemyGamePlace;
import Walking.Places.QuestPlace;
import Walking.Places.TreasureGamePlace;
import dg.generator.dungeon.Coordinate;
import org.json.JSONObject;

import java.util.ArrayList;

public class Quest {

    private final String questName;
    private final String questDescription;
    private final QuestType questType;
    private final ArrayList<Item> reward;
    private boolean questDone, questStarted;
    private QuestPlace questPlace;
    private Coordinate questCoordinate;

    public Quest(String questName, String questDescription, QuestType questType,ArrayList<Item> reward) {
        this.questName = questName;
        this.questDescription = questDescription;
        this.questType=questType;
        this.reward=reward;
        questDone=false;
        questStarted=false;
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

    public QuestPlace getQuestPlace() {
        return questPlace;
    }

    public void setQuestPlace(QuestPlace questPlace) {
        if(this.questPlace==null)
            this.questPlace = questPlace;
    }

    public Coordinate getQuestCoordinate(){
        Coordinate c;
        switch (questType){
            case ENEMY_DEFEAT -> {
                EnemyGamePlace t = (EnemyGamePlace) questPlace.getSuperPlace();
                EnemyDrone d = (EnemyDrone) t.getReference();
                c = new Coordinate(d.getPosX(),d.getPosY());
            }
            case CHEST_OPEN -> {
                if(questCoordinate==null)
                    questCoordinate = GameManager.getWalkingManager().getWalking().getMap().getCoordinate(questPlace);
                c=questCoordinate;
            }
            case null, default -> throw new RuntimeException("Cannot determine quest coordinate");
        }
        return c;
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

    public boolean isQuestStarted() {
        return questStarted;
    }

    public void startQuest() {
        this.questStarted = true;
    }
}
