package GUI.WalkingGUI;

import GUI.ViewPanel;

import javax.swing.*;
import java.util.ArrayList;

public class WalkingView extends ViewPanel {

    private final PartyStatusPanel partyStatusPanel;
    private final LootLogPanel lootLogPanel;

    public WalkingView() {
        super(new WalkingPanel(), new Arrows(getSharedBorder()), new LootLogPanel(getSharedBorder()), new PartyStatusPanel(getSharedBorder()));

        //Get child component
        ArrayList<JPanel> panels = getChildPanels();
        lootLogPanel = (LootLogPanel) panels.get(2);
        partyStatusPanel = (PartyStatusPanel) panels.get(3);

        //Refresh
        this.revalidate();
        this.repaint();
    }

    public void refresh() {
        partyStatusPanel.refresh();
        lootLogPanel.refresh();
        this.repaint();
        this.revalidate();
    }

}
