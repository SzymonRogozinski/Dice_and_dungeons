package GUI.QuestGUI;

import GUI.Components.GameLabel;
import GUI.GUISettings;
import Game.GameActionQueue;
import Game.GameManager;
import Quest.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class QuestListPanel extends JPanel {

    private final GameLabel title;
    private final ArrayList<GameLabel> questLabels;

    public QuestListPanel(Border border) {
        this.setSize(GUISettings.PANEL_SIZE, GUISettings.PANEL_SIZE);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setBorder(border);
        this.setBackground(Color.BLACK);

        title = new GameLabel(
                "Quests", SwingConstants.CENTER,
                GUISettings.PANEL_SIZE * 4/5, GUISettings.PANEL_SIZE *2/15,
                Color.WHITE,GUISettings.BIG_FONT
        );

        this.add(title);

        questLabels=new ArrayList<>();
    }

    public void refresh(){
        if(questLabels.size() == GameManager.getQuestModule().getQuests().size())
            return;
        //Clear
        questLabels.clear();
        this.removeAll();
        //Add
        this.add(title);
        for(int i=0;i<GameManager.getQuestModule().getQuests().size();i++){
            Quest quest = GameManager.getQuestModule().getQuests().get(i);
            questLabels.add(new GameLabel(
                    STR."\{i+1} \{quest.getQuestName()}", SwingConstants.LEFT, GUISettings.PANEL_SIZE-30, GUISettings.PANEL_SIZE/15, Color.WHITE
            ));
            questLabels.getLast().addMouseListener(new QuestMouseListener(i));
            this.add(questLabels.getLast());
        }
        resize();
    }

    public void resize(){
        this.setSize(GUISettings.getResizedValue(GUISettings.PANEL_SIZE), GUISettings.getResizedValue(GUISettings.PANEL_SIZE));

        title.resize();
        for(GameLabel label: questLabels)
            label.resize();
    }

    private void markLabel(int labelId){
        if(labelId!=GameManager.getQuestModule().getSelectedQuest())
            questLabels.get(labelId).setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
        GameActionQueue.action(()-> GameManager.getQuestModule().setPointedQuest(labelId));
    }

    private void unMarkLabel(int labelId){
        if(labelId!=GameManager.getQuestModule().getSelectedQuest())
            questLabels.get(labelId).setBorder(null);
        GameActionQueue.action(()-> GameManager.getQuestModule().setPointedQuest(-1));
    }

    private void selectQuest(int questId){
        if(GameManager.getQuestModule().getSelectedQuest()!=-1)
            questLabels.get(GameManager.getQuestModule().getSelectedQuest()).setBorder(null);
        if(questId==GameManager.getQuestModule().getSelectedQuest()){
            questLabels.get(questId).setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
            GameActionQueue.action(()-> GameManager.getQuestModule().setSelectedQuest(-1));
        }else{
            questLabels.get(questId).setBorder(BorderFactory.createLineBorder(Color.YELLOW,1));
            GameActionQueue.action(()-> GameManager.getQuestModule().setSelectedQuest(questId));
        }
    }

    private class QuestMouseListener implements MouseListener {

        final int questId;

        QuestMouseListener(int questId){
            this.questId=questId;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            markLabel(questId);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            unMarkLabel(questId);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            selectQuest(questId);
        }

        //Ignored
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}
    }
}
