package GUI.EquipmentGUI;

import GUI.GUISettings;
import Game.GameManager;
import Game.GameStates;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class SwitchPanel extends JPanel {

    //private EquipmentModule equipment;

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
        eqButton.addActionListener(e-> GameManager.getEquipment().changeViewToEquipment());

        JButton backButton=new JButton("Backpack");
        backButton.setPreferredSize(new Dimension((int)(GUISettings.SMALL_PANEL_SIZE*0.8),(int)(GUISettings.SMALL_PANEL_SIZE*0.20)));
        backButton.addActionListener(e-> GameManager.getEquipment().changeViewToBackpack());

        JButton returnButton=new JButton("Close");
        returnButton.setPreferredSize(new Dimension((int)(GUISettings.SMALL_PANEL_SIZE*0.8),(int)(GUISettings.SMALL_PANEL_SIZE*0.20)));
        returnButton.addActionListener(e-> GameManager.changeState(GameStates.WALKING));

        this.add(eqButton);
        this.add(backButton);
        this.add(returnButton);

    }
}
