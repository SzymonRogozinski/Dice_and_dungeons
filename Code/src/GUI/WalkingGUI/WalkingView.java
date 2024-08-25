package GUI.WalkingGUI;

import GUI.GUISettings;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class WalkingView extends JPanel {

    public WalkingView(){
        //TODO Refactor!
        //Setting panel
        this.setLayout(null);
        this.setPreferredSize(new Dimension(GUISettings.heightAndWidth,GUISettings.heightAndWidth));
        this.setBackground(Color.BLACK);

        //Panel components
        //Big four
        JPanel bigPanel=new JPanel();
        JPanel smallPanel = new JPanel();
        JPanel leftInfoPanel = new JPanel();
        JPanel downInfoPanel = new JPanel();
        Border border = BorderFactory.createLineBorder(Color.WHITE, 2);
        //Actual components
        Arrows arrows = new Arrows(border);
        ViewPanel viewPanel = new ViewPanel();

        //Settings components
        bigPanel.setBounds(0,0, GUISettings.PANEL_SIZE,GUISettings.PANEL_SIZE);
        bigPanel.setLayout(null);
        bigPanel.setBorder(border);
        bigPanel.add(viewPanel);

        smallPanel.setBounds(GUISettings.PANEL_SIZE,GUISettings.PANEL_SIZE,GUISettings.SMALL_PANEL_SIZE,GUISettings.SMALL_PANEL_SIZE);
        smallPanel.setLayout(null);
        smallPanel.setBorder(border);
        smallPanel.add(arrows);

        leftInfoPanel.setBounds(GUISettings.PANEL_SIZE,0,GUISettings.SMALL_PANEL_SIZE,GUISettings.PANEL_SIZE);
        leftInfoPanel.setLayout(null);
        leftInfoPanel.setBackground(Color.BLACK);
        leftInfoPanel.setBorder(border);

        downInfoPanel.setBounds(0,GUISettings.PANEL_SIZE,GUISettings.PANEL_SIZE,GUISettings.SMALL_PANEL_SIZE);
        downInfoPanel.setLayout(null);
        downInfoPanel.setBackground(Color.BLACK);
        downInfoPanel.setBorder(border);

        //Adding component to panel
        this.add(bigPanel);
        this.add(smallPanel);
        this.add(leftInfoPanel);
        this.add(downInfoPanel);

        //Refresh
        this.revalidate();
        this.repaint();
    }

    public void refresh(){
        this.repaint();
        this.revalidate();
    }

}
