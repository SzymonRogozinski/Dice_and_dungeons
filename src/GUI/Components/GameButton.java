package GUI.Components;

import GUI.GUISettings;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameButton extends JButton {

    private final int fontId,originWidth, originHeight;

    public GameButton(String name, int width, int height) {
        this.setMargin(new Insets(0, 0, 0, 0));
        this.setText(name);
        this.setPreferredSize(new Dimension(width, height));
        this.setFont(GUISettings.BUTTON_FONT);

        fontId = GUISettings.getFontID(this.getFont());
        originWidth=width;
        originHeight =height;
    }

    public GameButton(String name, int width, int height, ActionListener l) {
        this.setMargin(new Insets(0, 0, 0, 0));
        this.setText(name);
        this.setPreferredSize(new Dimension(width, height));
        this.addActionListener(l);
        this.setFont(GUISettings.BUTTON_FONT);

        fontId = GUISettings.getFontID(this.getFont());
        originWidth=width;
        originHeight =height;
    }

    public GameButton(String name, int width, int height, ActionListener l, Color foreground, Color background, Border border) {
        this.setMargin(new Insets(0, 0, 0, 0));
        this.setText(name);
        this.setPreferredSize(new Dimension(width, height));
        this.addActionListener(l);
        this.setForeground(foreground);
        this.setBackground(background);
        this.setBorder(border);
        this.setFont(GUISettings.BUTTON_FONT);

        fontId = GUISettings.getFontID(this.getFont());
        originWidth=width;
        originHeight =height;
    }

    public void resize(){
        this.setPreferredSize(new Dimension(GUISettings.getResizedValue(originWidth), GUISettings.getResizedValue(originHeight)));
        this.setFont(GUISettings.getFontFromID(fontId));
    }
}
