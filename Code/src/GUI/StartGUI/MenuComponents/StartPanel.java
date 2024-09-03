package GUI.StartGUI.MenuComponents;

import GUI.GUISettings;
import Game.Game;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class StartPanel extends JPanel {

    private JButton start;
    private JLabel status;

    public StartPanel(Border border) {
        FlowLayout layout = new FlowLayout();
        layout.setVgap(GUISettings.SMALL_PANEL_SIZE/5);
        this.setLayout(layout);
        this.setSize(GUISettings.SMALL_PANEL_SIZE,GUISettings.SMALL_PANEL_SIZE);
        this.setBorder(border);
        this.setBackground(Color.BLACK);

        start = new JButton("Start game");
        start.setPreferredSize(new Dimension(GUISettings.SMALL_PANEL_SIZE*2/3,GUISettings.SMALL_PANEL_SIZE/4));
        start.setEnabled(false);

        status = new JLabel("Your Party is too small!", SwingConstants.CENTER);
        status.setForeground(Color.WHITE);
        status.setPreferredSize(new Dimension(GUISettings.SMALL_PANEL_SIZE,GUISettings.SMALL_PANEL_SIZE/4));

        this.add(start);
        this.add(status);
    }

    public void refresh(){
        if(Game.getMenuModule().getParty().size()==3) {
            start.setEnabled(true);
            status.setVisible(false);
        }else{
            start.setEnabled(false);
            status.setVisible(true);
        }
    }
}
