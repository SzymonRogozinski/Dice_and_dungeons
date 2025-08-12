package GUI.QuestGUI;

import GUI.Components.GameTextArea;
import GUI.GUISettings;
import Game.GameManager;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Objects;

public class QuestDescriptionPanel  extends JPanel {

    private final GameTextArea questDescription;

    public QuestDescriptionPanel(Border border) {
        this.setSize(GUISettings.PANEL_SIZE, GUISettings.SMALL_PANEL_SIZE);
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        this.setLayout(layout);
        this.setBackground(Color.BLACK);
        this.setBorder(border);

        questDescription = new GameTextArea(GUISettings.PANEL_SIZE - 5, GUISettings.SMALL_PANEL_SIZE - 8);
        questDescription.setText("");

        this.add(questDescription);
    }

    public void refresh(){
        int questId=GameManager.getQuestModule().getPointedQuest();
        if(questId==-1 && !questDescription.getText().isEmpty())
            questDescription.setText("");
        else if (questId!=-1 && !Objects.equals(questDescription.getText(), GameManager.getQuestModule().getQuests().get(questId).getQuestDescription()))
            questDescription.setText(GameManager.getQuestModule().getQuests().get(questId).getQuestDescription());
    }

    public void resize(){
        this.setSize(GUISettings.getResizedValue(GUISettings.PANEL_SIZE), GUISettings.getResizedValue(GUISettings.SMALL_PANEL_SIZE));

        questDescription.resize();
    }
}
