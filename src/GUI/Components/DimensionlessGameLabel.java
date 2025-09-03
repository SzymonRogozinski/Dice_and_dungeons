package GUI.Components;

import GUI.GUISettings;
import Game.GameUtils;

import javax.swing.*;
import java.awt.*;

public class DimensionlessGameLabel extends JLabel {

    private final int fontId,originWidth, originHeight;
    private ImageIcon icon;

    public DimensionlessGameLabel(String text, int orientation, Font font, Color foreground) {
        this.setText(text);
        this.setHorizontalAlignment(orientation);
        this.setFont(font);
        this.setForeground(foreground);
        fontId = GUISettings.getFontID(this.getFont());
        originWidth=0;
        originHeight=0;
    }

    public DimensionlessGameLabel(String text, int orientation, Color foreground) {
        this.setText(text);
        this.setHorizontalAlignment(orientation);
        this.setForeground(foreground);
        this.setFont(GUISettings.DEFAULT_FONT);
        fontId = GUISettings.getFontID(this.getFont());
        originWidth=0;
        originHeight =0;
    }

    public DimensionlessGameLabel(int width, int height){
        originWidth=width;
        originHeight =height;
        this.setForeground(Color.BLACK);

        this.setFont(GUISettings.DEFAULT_FONT);
        fontId = GUISettings.getFontID(this.getFont());
    }

    public void resize(){
        this.setFont(GUISettings.getFontFromID(fontId));

        if(icon!=null)
            this.setIcon(GameUtils.resizeIcon(icon, GUISettings.getResizedValue(originWidth), GUISettings.getResizedValue(originHeight)));
    }

    @Override
    public void setIcon(Icon icon) {
        this.icon=(ImageIcon) icon;
        super.setIcon(icon);
    }
}
