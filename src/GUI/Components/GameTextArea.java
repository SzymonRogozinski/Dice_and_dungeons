package GUI.Components;

import GUI.GUISettings;

import javax.swing.*;
import java.awt.*;

public class GameTextArea extends JTextArea {

    private final int fontId,originWidth, originHeight;

    public GameTextArea(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.setForeground(Color.WHITE);
        this.setBackground(Color.BLACK);
        this.setLineWrap(true);
        this.setWrapStyleWord(true);
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
