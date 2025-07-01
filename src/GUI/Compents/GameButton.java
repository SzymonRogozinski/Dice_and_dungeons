package GUI.Compents;

import GUI.GUISettings;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameButton extends JButton {


    public GameButton(String name, int width, int height) {
        this.setMargin(new Insets(0, 0, 0, 0));
        this.setText(name);
        this.setPreferredSize(new Dimension(width, height));
        this.setFont(GUISettings.DEFAULT_FONT);
    }

    public GameButton(String name, int width, int height, ActionListener l) {
        this.setMargin(new Insets(0, 0, 0, 0));
        this.setText(name);
        this.setPreferredSize(new Dimension(width, height));
        this.addActionListener(l);
        this.setFont(GUISettings.DEFAULT_FONT);
    }

    public GameButton(String name, int width, int height, ActionListener l, Color foreground, Color background, Border border) {
        this.setMargin(new Insets(0, 0, 0, 0));
        this.setText(name);
        this.setPreferredSize(new Dimension(width, height));
        this.addActionListener(l);
        this.setForeground(foreground);
        this.setBackground(background);
        this.setBorder(border);
        this.setFont(GUISettings.DEFAULT_FONT);
    }
}
