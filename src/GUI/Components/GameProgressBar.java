package GUI.Components;

import GUI.GUISettings;

import javax.swing.*;
import java.awt.*;

public class GameProgressBar extends JProgressBar {

    private final int fontId,originWidth, originHeight;

    public GameProgressBar(Color color, int width, int height) {
        this.setForeground(color);
        this.setStringPainted(true);
        this.setPreferredSize(new Dimension(width, height));
        this.setFont(GUISettings.DEFAULT_FONT);

        fontId = GUISettings.getFontID(this.getFont());
        originWidth=width;
        originHeight =height;
    }

    public void resize(){
        this.setPreferredSize(new Dimension(GUISettings.getResizedValue(originWidth), GUISettings.getResizedValue(originHeight)));
        this.setFont(GUISettings.getFontFromID(fontId));
    }
}
