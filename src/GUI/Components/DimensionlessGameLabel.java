package GUI.Components;

import GUI.GUISettings;

import javax.swing.*;
import java.awt.*;

public class DimensionlessGameLabel extends JLabel {

    private final int fontId;

    public DimensionlessGameLabel(String text, int orientation, Font font, Color foreground) {
        this.setText(text);
        this.setHorizontalAlignment(orientation);
        this.setFont(font);
        this.setForeground(foreground);
        fontId = GUISettings.getFontID(this.getFont());
    }

    public DimensionlessGameLabel(String text, int orientation, Color foreground) {
        this.setText(text);
        this.setHorizontalAlignment(orientation);
        this.setForeground(foreground);
        this.setFont(GUISettings.DEFAULT_FONT);
        fontId = GUISettings.getFontID(this.getFont());
    }

    public void resize(){
        this.setFont(GUISettings.getFontFromID(fontId));
    }
}
