package GUI.MenuGUI;

import GUI.ViewPanel;
import GUI.MenuGUI.MenuComponents.*;

import javax.swing.*;
import java.util.ArrayList;

public class ChoosePartyPanel extends ViewPanel {

    private final PartyPanel partyPanel;
    private final StartPanel startPanel;
    private final CharacterInfoPanel characterInfoPanel;
    public ChoosePartyPanel() {
        super(new ChoosePanel(getSharedBorder()),new StartPanel(getSharedBorder()),new CharacterInfoPanel(getSharedBorder()),new PartyPanel(getSharedBorder()));

        ArrayList<JPanel> childPanels=getChildPanels();
        partyPanel = (PartyPanel)childPanels.get(3);
        startPanel = (StartPanel)childPanels.get(1);
        characterInfoPanel = (CharacterInfoPanel) childPanels.get(2);
        //Refresh
        this.revalidate();
        this.repaint();
    }

    public void refresh(){
        partyPanel.refresh();
        startPanel.refresh();
        characterInfoPanel.refresh();
        //Refresh
        this.revalidate();
        this.repaint();
    }

}
