package GUI.EquipmentGUI;

import GUI.GUISettings;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class SwitchPanel extends JPanel {

    public SwitchPanel(Border border){
        //Set display
        this.setSize(GUISettings.SMALL_PANEL_SIZE,GUISettings.SMALL_PANEL_SIZE);
        FlowLayout layout=new FlowLayout(FlowLayout.CENTER);
        this.setLayout(layout);
        layout.setVgap((int)(GUISettings.SMALL_PANEL_SIZE*0.1));
        this.setBackground(Color.BLACK);
        this.setBorder(border);

        JButton eqButton=new JButton("Equipment");
        eqButton.setPreferredSize(new Dimension((int)(GUISettings.SMALL_PANEL_SIZE*0.8),(int)(GUISettings.SMALL_PANEL_SIZE*0.20)));

        JButton backButton=new JButton("Backpack");
        backButton.setPreferredSize(new Dimension((int)(GUISettings.SMALL_PANEL_SIZE*0.8),(int)(GUISettings.SMALL_PANEL_SIZE*0.20)));

        JButton returnButton=new JButton("Close");
        returnButton.setPreferredSize(new Dimension((int)(GUISettings.SMALL_PANEL_SIZE*0.8),(int)(GUISettings.SMALL_PANEL_SIZE*0.20)));

        this.add(eqButton);
        this.add(backButton);
        this.add(returnButton);

    }

}
