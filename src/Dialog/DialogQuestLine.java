package Dialog;

import Quest.Quest;

public class DialogQuestLine extends DialogLine{

    private final Quest quest;
    private final boolean startQuestLine;
    private final String endResponse;
    public DialogQuestLine(String line, String startResponse,String endResponse, int[] nextDialogs, boolean startQuestLine, Quest quest) {
        super(line, startResponse, nextDialogs, startQuestLine);
        this.quest=quest;
        this.startQuestLine=startQuestLine;
        this.endResponse=endResponse;
    }

    public Quest getQuest() {
        return quest;
    }

    public boolean isStartQuestLine() {
        return startQuestLine;
    }

    @Override
    public String getResponse() {
        return !startQuestLine & quest.isQuestDone() ? endResponse : super.getResponse();
    }
}
