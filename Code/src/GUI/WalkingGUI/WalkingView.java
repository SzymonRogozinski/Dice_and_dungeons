package GUI.WalkingGUI;

import GUI.MainPanel;

import javax.swing.*;

public class WalkingView extends MainPanel {

    public WalkingView(){
        super();

        //Real components
        Arrows arrows = new Arrows(getSharedBorder());
        WalkingPanel walkingPanel = new WalkingPanel();
        JPanel placeholder1 = new JPanel();
        JPanel placeholder2=new JPanel();

        setPanelsContent(walkingPanel,arrows,placeholder1,placeholder2);
        //Refresh
        this.revalidate();
        this.repaint();
    }

    public void refresh(){
        this.repaint();
        this.revalidate();
    }

}
