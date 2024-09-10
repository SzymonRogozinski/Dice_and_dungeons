package GUI.WalkingGUI;

import GUI.ViewPanel;

import javax.swing.*;
import java.util.ArrayList;

public class WalkingView extends ViewPanel {

    private final WalkingPanel walkingPanel;
    private final Arrows arrows;
    private final PartyStatusPanel partyStatusPanel;

    public WalkingView(){
        super(new WalkingPanel(),new Arrows(getSharedBorder()),new JPanel(),new PartyStatusPanel(getSharedBorder()));

        //Get child component
        ArrayList<JPanel> panels = getChildPanels();
        walkingPanel = (WalkingPanel) panels.get(0);
        arrows = (Arrows) panels.get(1);
        partyStatusPanel = (PartyStatusPanel) panels.get(3);

        //Refresh
        this.revalidate();
        this.repaint();
    }

    public void refresh(){
        partyStatusPanel.refresh();
        this.repaint();
        this.revalidate();
    }

}
