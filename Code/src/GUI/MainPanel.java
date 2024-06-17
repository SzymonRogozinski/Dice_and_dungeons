package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public abstract class MainPanel extends JPanel {

    private final JPanel smallPanel,bigPanel,bottomPanel,sidePanel;

    public MainPanel( ){
        //Setting panel
        this.setLayout(null);
        this.setPreferredSize(new Dimension(GUISettings.heightAndWidth,GUISettings.heightAndWidth));
        this.setBackground(Color.BLACK);

        //Panel components
        //Big four
        bigPanel=new JPanel();
        smallPanel = new JPanel();
        bottomPanel = new JPanel();
        sidePanel = new JPanel();
        Border border = BorderFactory.createLineBorder(Color.WHITE, 2);

        //Settings components
        bigPanel.setBounds(0,0, GUISettings.PANEL_SIZE,GUISettings.PANEL_SIZE);
        bigPanel.setLayout(null);
        bigPanel.setBackground(Color.BLACK);
        bigPanel.setBorder(border);

        smallPanel.setBounds(GUISettings.PANEL_SIZE,GUISettings.PANEL_SIZE,GUISettings.SMALL_PANEL_SIZE,GUISettings.SMALL_PANEL_SIZE);
        smallPanel.setLayout(null);
        smallPanel.setBackground(Color.BLACK);
        smallPanel.setBorder(border);

        sidePanel.setBounds(GUISettings.PANEL_SIZE,0,GUISettings.SMALL_PANEL_SIZE,GUISettings.PANEL_SIZE);
        sidePanel.setLayout(null);
        sidePanel.setBackground(Color.BLACK);
        sidePanel.setBorder(border);

        bottomPanel.setBounds(0,GUISettings.PANEL_SIZE,GUISettings.PANEL_SIZE,GUISettings.SMALL_PANEL_SIZE);
        bottomPanel.setLayout(null);
        bottomPanel.setBackground(Color.BLACK);
        bottomPanel.setBorder(border);

        //Adding component to panel
        this.add(bigPanel);
        this.add(smallPanel);
        this.add(sidePanel);
        this.add(bottomPanel);

        //Refresh
        this.revalidate();
        this.repaint();
    }

    public Border getBorder(){
        return BorderFactory.createLineBorder(Color.WHITE, 2);
    }

    public void setPanelsContent(JPanel smallPanelContent,JPanel bigPanelContent,JPanel bottomPanelContent,JPanel sidePanelContent){
        smallPanel.add(smallPanelContent);
        bigPanel.add(bigPanelContent);
        bottomPanel.add(bottomPanelContent);
        sidePanel.add(sidePanelContent);

        //Refresh
        this.revalidate();
        this.repaint();
    }
}
