package GUI.DialogGUI;

import GUI.Components.PartyStatusInfoPanel;
import GUI.ViewPanel;
import Game.GameManager;

import javax.swing.*;
import java.util.ArrayList;

public class DialogView extends ViewPanel {

    private final NPCDialogPanel npcDialogPanel;
    private final DialogOptionPanel dialogOptionPanel;
    private final PartyStatusInfoPanel partyStatusInfoPanel;
    private final NavigationPanel navigationPanel;
    public DialogView() {
        super(new NPCDialogPanel(getSharedBorder()), new NavigationPanel(getSharedBorder()), new DialogOptionPanel(getSharedBorder()), new PartyStatusInfoPanel(getSharedBorder()));

        ArrayList<JPanel> panels = super.getChildPanels();
        npcDialogPanel = (NPCDialogPanel) panels.get(0);
        navigationPanel = (NavigationPanel) panels.get(1);
        dialogOptionPanel = (DialogOptionPanel) panels.get(2);
        partyStatusInfoPanel = (PartyStatusInfoPanel) panels.get(3);
    }

    public void refresh(){
        if(!GameManager.getDialogModule().isRefreshDialog())
            return;
        npcDialogPanel.refresh();
        dialogOptionPanel.refresh();
        partyStatusInfoPanel.refresh();
        GameManager.getDialogModule().setRefreshDialog(false);
    }

    public void resize(){
        super.resize();

        npcDialogPanel.resize();
        navigationPanel.resize();
        dialogOptionPanel.resize();
        partyStatusInfoPanel.resize();
    }
}
