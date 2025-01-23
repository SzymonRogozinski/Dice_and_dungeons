package GUI.Compents;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameButton extends JButton {


    public GameButton(String name, int width, int height) {
        this.setText(name);
        this.setPreferredSize(new Dimension(width, height));
    }

    public GameButton(String name, int width, int height, ActionListener l) {
        this.setText(name);
        this.setPreferredSize(new Dimension(width, height));
        this.addActionListener(l);
    }

    public GameButton(String name, int width, int height, ActionListener l, Color foreground, Color background, Border border) {
        this.setText(name);
        this.setPreferredSize(new Dimension(width, height));
        this.addActionListener(l);
        this.setForeground(foreground);
        this.setBackground(background);
        this.setBorder(border);
    }
}
