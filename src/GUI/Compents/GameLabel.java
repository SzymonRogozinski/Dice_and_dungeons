package GUI.Compents;

import GUI.GUISettings;

import javax.swing.*;
import java.awt.*;

public class GameLabel extends JLabel {

    //Simple label settings
    public GameLabel(String text, int orientation, int width, int height, Color foreground) {
        this.setText(text);
        this.setHorizontalAlignment(orientation);
        this.setPreferredSize(new Dimension(width, height));
        this.setForeground(foreground);
        this.setFont(GUISettings.DEFAULT_FONT);
    }

    public GameLabel(ImageIcon icon, int orientation, int width, int height, Color foreground, int verticalAlignment, int horizontalAlignment) {
        this.setIcon(icon);
        this.setHorizontalAlignment(orientation);
        this.setPreferredSize(new Dimension(width, height));
        this.setForeground(foreground);
        this.setVerticalAlignment(verticalAlignment);
        this.setHorizontalAlignment(horizontalAlignment);
        this.setFont(GUISettings.DEFAULT_FONT);
    }

    public GameLabel(String text, int orientation, int width, int height, Color foreground, int verticalAlignment, int horizontalAlignment) {
        this.setText(text);
        this.setHorizontalAlignment(orientation);
        this.setPreferredSize(new Dimension(width, height));
        this.setForeground(foreground);
        this.setVerticalAlignment(verticalAlignment);
        this.setHorizontalAlignment(horizontalAlignment);
        this.setFont(GUISettings.SMALL_FONT);
    }
}
