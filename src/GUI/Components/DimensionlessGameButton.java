package GUI.Components;

import GUI.GUISettings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DimensionlessGameButton extends JButton {

    private final int fontId, originHeight, originWidth;    //Size is optional

    public DimensionlessGameButton(String name, ActionListener l){
        this.setMargin(new Insets(0, 0, 0, 0));
        this.setText(name);
        this.addActionListener(l);
        this.setFont(GUISettings.BUTTON_FONT);

        fontId = GUISettings.getFontID(this.getFont());
        originHeight=0;
        originWidth=0;
    }

    public DimensionlessGameButton(String name, ActionListener l, int width,int height){
        this.setMargin(new Insets(0, 0, 0, 0));
        this.setText(name);
        this.setPreferredSize(new Dimension(width,height));
        this.addActionListener(l);
        this.setFont(GUISettings.BUTTON_FONT);

        fontId = GUISettings.getFontID(this.getFont());
        originHeight=height;
        originWidth=width;
    }

    public void resize(){
        if(originHeight!=0 && originWidth!=0)
            this.setPreferredSize(new Dimension(GUISettings.getResizedValue(originWidth), GUISettings.getResizedValue(originHeight)));

        this.setFont(GUISettings.getFontFromID(fontId));
    }
}
