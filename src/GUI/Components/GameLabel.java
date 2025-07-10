package GUI.Components;

import GUI.GUISettings;

import javax.swing.*;
import java.awt.*;

public class GameLabel extends JLabel {

    private final int fontId,originWidth, originHeight;
    //Simple label settings
    public GameLabel(String text, int orientation, int width, int height, Color foreground) {
        this.setText(text);
        this.setHorizontalAlignment(orientation);
        this.setPreferredSize(new Dimension(width, height));
        this.setForeground(foreground);
        this.setFont(GUISettings.DEFAULT_FONT);

        fontId = GUISettings.getFontID(this.getFont());
        originWidth=width;
        originHeight =height;
    }

    public GameLabel(String text, int orientation, int width, int height, Color foreground, Font font) {
        this.setText(text);
        this.setHorizontalAlignment(orientation);
        this.setPreferredSize(new Dimension(width, height));
        this.setForeground(foreground);
        this.setFont(font);

        fontId = GUISettings.getFontID(this.getFont());
        originWidth=width;
        originHeight =height;
    }

    public GameLabel(int orientation, int width, int height, Color foreground, int verticalAlignment, int horizontalAlignment) {
        this.setHorizontalAlignment(orientation);
        this.setPreferredSize(new Dimension(width, height));
        this.setForeground(foreground);
        this.setVerticalAlignment(verticalAlignment);
        this.setHorizontalAlignment(horizontalAlignment);
        this.setFont(GUISettings.DEFAULT_FONT);

        fontId = GUISettings.getFontID(this.getFont());
        originWidth=width;
        originHeight =height;
    }

    public GameLabel(ImageIcon icon, int orientation, int width, int height, Color foreground, int verticalAlignment, int horizontalAlignment) {
        this.setIcon(icon);
        this.setHorizontalAlignment(orientation);
        this.setPreferredSize(new Dimension(width, height));
        this.setForeground(foreground);
        this.setVerticalAlignment(verticalAlignment);
        this.setHorizontalAlignment(horizontalAlignment);
        this.setFont(GUISettings.DEFAULT_FONT);

        fontId = GUISettings.getFontID(this.getFont());
        originWidth=width;
        originHeight =height;
    }

    public GameLabel(String text, int orientation, int width, int height, Color foreground, int verticalAlignment, int horizontalAlignment) {
        this.setText(text);
        this.setHorizontalAlignment(orientation);
        this.setPreferredSize(new Dimension(width, height));
        this.setForeground(foreground);
        this.setVerticalAlignment(verticalAlignment);
        this.setHorizontalAlignment(horizontalAlignment);
        this.setFont(GUISettings.SMALL_FONT);

        fontId = GUISettings.getFontID(this.getFont());
        originWidth=width;
        originHeight =height;
    }

    public GameLabel(String text, int orientation, int width, int height, Color foreground, int verticalAlignment, int horizontalAlignment, Font font) {
        this.setText(text);
        this.setHorizontalAlignment(orientation);
        this.setPreferredSize(new Dimension(width, height));
        this.setForeground(foreground);
        this.setVerticalAlignment(verticalAlignment);
        this.setHorizontalAlignment(horizontalAlignment);
        this.setFont(font);

        fontId = GUISettings.getFontID(this.getFont());
        originWidth=width;
        originHeight =height;
    }

    public void resize(){
        this.setPreferredSize(new Dimension(GUISettings.getResizedValue(originWidth), GUISettings.getResizedValue(originHeight)));
        this.setFont(GUISettings.getFontFromID(fontId));
    }
}
