package GUI.StartGUI;

import GUI.GUISettings;
import GUI.MainPanel;
import GUI.StartGUI.MenuComponents.*;

import javax.swing.*;
import java.util.ArrayList;

public class ChoosePartyPanel extends MainPanel {

    private final PartyPanel partyPanel;
    private final StartPanel startPanel;
    public ChoosePartyPanel() {
        super(new ChoosePanel(getSharedBorder()),new StartPanel(getSharedBorder()),new CharacterInfoPanel(getSharedBorder()),new PartyPanel(getSharedBorder()));

        ArrayList<JPanel> childPanels=getChildPanels();
        partyPanel = (PartyPanel)childPanels.get(3);
        startPanel = (StartPanel)childPanels.get(1);
        //Refresh
        this.revalidate();
        this.repaint();
    }

    public void refresh(){
        partyPanel.refresh();
        startPanel.refresh();
        //Refresh
        this.revalidate();
        this.repaint();
    }

}
