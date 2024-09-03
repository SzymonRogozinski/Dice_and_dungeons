package GUI.StartGUI.MenuComponents;

import GUI.GUISettings;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CharacterInfoPanel extends JPanel {

    public CharacterInfoPanel(Border border) {
        //Set display
        this.setPreferredSize(new Dimension(GUISettings.PANEL_SIZE,GUISettings.SMALL_PANEL_SIZE));
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        this.setBorder(border);
    }
}
