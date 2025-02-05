package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class ViewPanel extends JLayeredPane {

    private final ArrayList<JPanel> childPanels;

    public ViewPanel(JPanel bigPanelContent, JPanel smallPanelContent, JPanel bottomPanelContent, JPanel sidePanelContent) {
        //Setting panel
        this.setLayout(null);
        this.setPreferredSize(new Dimension(GUISettings.heightAndWidth, GUISettings.heightAndWidth));
        this.setBackground(Color.BLACK);

        //Panel components
        //Big four
        JPanel bigPanel = new JPanel();
        JPanel smallPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        JPanel sidePanel = new JPanel();
        Border border = BorderFactory.createLineBorder(Color.WHITE, 2);

        //Settings components
        bigPanel.setBounds(0, 0, GUISettings.PANEL_SIZE, GUISettings.PANEL_SIZE);
        bigPanel.setLayout(null);
        bigPanel.setBackground(Color.BLACK);
        bigPanel.setBorder(border);

        smallPanel.setBounds(GUISettings.PANEL_SIZE, GUISettings.PANEL_SIZE, GUISettings.SMALL_PANEL_SIZE, GUISettings.SMALL_PANEL_SIZE);
        smallPanel.setLayout(null);
        smallPanel.setBackground(Color.BLACK);
        smallPanel.setBorder(border);

        sidePanel.setBounds(GUISettings.PANEL_SIZE, 0, GUISettings.SMALL_PANEL_SIZE, GUISettings.PANEL_SIZE);
        sidePanel.setLayout(null);
        sidePanel.setBackground(Color.BLACK);
        sidePanel.setBorder(border);

        bottomPanel.setBounds(0, GUISettings.PANEL_SIZE, GUISettings.PANEL_SIZE, GUISettings.SMALL_PANEL_SIZE);
        bottomPanel.setLayout(null);
        bottomPanel.setBackground(Color.BLACK);
        bottomPanel.setBorder(border);

        //Adding component to panel
        this.add(bigPanel, JLayeredPane.DEFAULT_LAYER);
        this.add(smallPanel, JLayeredPane.DEFAULT_LAYER);
        this.add(sidePanel, JLayeredPane.DEFAULT_LAYER);
        this.add(bottomPanel, JLayeredPane.DEFAULT_LAYER);

        //Adding child components
        bigPanel.add(bigPanelContent);
        smallPanel.add(smallPanelContent);
        bottomPanel.add(bottomPanelContent);
        sidePanel.add(sidePanelContent);

        childPanels = new ArrayList<>(List.of(bigPanelContent, smallPanelContent, bottomPanelContent, sidePanelContent));

        //Refresh
        this.revalidate();
        this.repaint();
    }

    public static Border getSharedBorder() {
        return BorderFactory.createLineBorder(Color.WHITE, 2);
    }

    public ArrayList<JPanel> getChildPanels() {
        return childPanels;
    }
}
