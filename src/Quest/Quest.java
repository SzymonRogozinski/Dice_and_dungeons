package Quest;

public class Quest {

    private final String questName;
    private final String questDescription;

    public Quest(String questName, String questDescription) {
        this.questName = questName;
        this.questDescription = questDescription;
    }

    public String getQuestName() {
        return questName;
    }

    public String getQuestDescription() {
        return questDescription;
    }
}
