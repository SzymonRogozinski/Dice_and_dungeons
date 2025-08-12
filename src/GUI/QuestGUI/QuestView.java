package GUI.QuestGUI;

import GUI.ViewPanel;

import javax.swing.*;
import java.util.ArrayList;

public class QuestView extends ViewPanel {

    private final QuestListPanel questListPanel;
    private final QuestDescriptionPanel questDescriptionPanel;
    private final PartyStatusInfoPanel partyStatusInfoPanel;
    private final NavigationPanel navigationPanel;

    public QuestView() {
        super(new QuestListPanel(getSharedBorder()),new NavigationPanel(getSharedBorder()),new QuestDescriptionPanel(getSharedBorder()),new PartyStatusInfoPanel(getSharedBorder()));

        ArrayList<JPanel> panels = super.getChildPanels();
        questListPanel = (QuestListPanel) panels.get(0);
        navigationPanel = (NavigationPanel) panels.get(1);
        questDescriptionPanel = (QuestDescriptionPanel) panels.get(2);
        partyStatusInfoPanel = (PartyStatusInfoPanel) panels.get(3);
    }

    public void resize() {
        super.resize();

        navigationPanel.resize();
        partyStatusInfoPanel.resize();
        questListPanel.resize();
        questDescriptionPanel.resize();
    }

    public void refresh(){
        partyStatusInfoPanel.refresh();
        questListPanel.refresh();
        questDescriptionPanel.refresh();
    }
}
