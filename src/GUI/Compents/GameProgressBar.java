package GUI.Compents;

import GUI.GUISettings;

import javax.swing.*;
import java.awt.*;

public class GameProgressBar extends JProgressBar {

    public GameProgressBar(Color color, int width, int height) {
        this.setForeground(color);
        this.setStringPainted(true);
        this.setPreferredSize(new Dimension(width, height));
        this.setFont(GUISettings.DEFAULT_FONT);
    }
}
