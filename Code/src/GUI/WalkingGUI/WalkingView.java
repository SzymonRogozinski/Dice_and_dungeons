package GUI.WalkingGUI;

import GUI.MainPanel;

import javax.swing.*;
import java.util.ArrayList;

public class WalkingView extends MainPanel {

    public WalkingView(){
        super(new WalkingPanel(),new Arrows(getSharedBorder()),new JPanel(),new JPanel());

        //Get child component
        ArrayList<JPanel> panels = getChildPanels();
        WalkingPanel walkingPanel = (WalkingPanel) panels.get(0);
        Arrows arrows = (Arrows) panels.get(1);

        //Refresh
        this.revalidate();
        this.repaint();
    }

    public void refresh(){
        this.repaint();
        this.revalidate();
    }

}
