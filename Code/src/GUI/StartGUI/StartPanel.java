package GUI.StartGUI;

import GUI.GUISettings;

import javax.swing.*;
import java.awt.*;

public class StartPanel extends JPanel {

    public StartPanel() {
        //Setting panel
        this.setLayout(null);
        this.setPreferredSize(new Dimension(GUISettings.heightAndWidth,GUISettings.heightAndWidth));
        this.setBackground(Color.BLACK);

        //Set title
        JLabel title = new JLabel("Dice and Dungeons", SwingConstants.CENTER);
        title.setBounds(0,0,GUISettings.heightAndWidth,GUISettings.heightAndWidth/5);
        title.setFont(GUISettings.TITLE_FONT);
        title.setForeground(Color.WHITE);

        //Set menu
        JPanel menu = new JPanel();
        menu.setBounds(GUISettings.heightAndWidth/3,GUISettings.heightAndWidth/3,GUISettings.heightAndWidth/3,GUISettings.heightAndWidth/3);
        menu.setBackground(Color.BLUE);

        this.add(title);
        this.add(menu);

        this.repaint();
        this.revalidate();

    }
}
