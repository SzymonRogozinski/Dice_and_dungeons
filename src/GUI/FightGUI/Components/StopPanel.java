package GUI.FightGUI.Components;

import GUI.Components.DimensionlessGameButton;
import GUI.Components.GameButton;
import GUI.GUISettings;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class StopPanel extends JPanel {

    private final DimensionlessGameButton pauseButton;

    public StopPanel(ActionListener l, String buttonName, Border border, boolean isSmall) {
        this.setSize(GUISettings.PANEL_SIZE, GUISettings.SMALL_PANEL_SIZE);
        this.setLayout(new GridLayout(3, 1));
        this.setBackground(Color.BLACK);
        this.setBorder(border);

        this.add(new JLabel());
        if (isSmall) {
            pauseButton = new DimensionlessGameButton(buttonName,l,GUISettings.PANEL_SIZE / 3, GUISettings.SMALL_PANEL_SIZE / 6);

            JPanel reducer = new JPanel();
            reducer.setLayout(new FlowLayout(FlowLayout.CENTER));
            reducer.setBackground(Color.BLACK);
            reducer.add(pauseButton);
            this.add(reducer);
        } else {
            pauseButton = new DimensionlessGameButton(buttonName,l);
            this.add(pauseButton);
        }
    }

    public void resize(){
        this.setSize(GUISettings.getResizedValue(GUISettings.PANEL_SIZE), GUISettings.getResizedValue(GUISettings.SMALL_PANEL_SIZE));

        pauseButton.resize();
    }
}
