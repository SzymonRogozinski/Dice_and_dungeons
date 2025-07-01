package GUI.Compents;

import GUI.GUISettings;

import javax.swing.*;
import java.awt.*;

public class GameTextArea extends JTextArea {

    public GameTextArea(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.setForeground(Color.WHITE);
        this.setBackground(Color.BLACK);
        this.setLineWrap(true);
        this.setWrapStyleWord(true);
        this.setFont(GUISettings.DEFAULT_FONT);
    }
}
