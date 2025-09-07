package GUI.Components;

import GUI.GUISettings;
import Game.GameUtils;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private final int originWidth, originHeight;

    public GamePanel(int width, int height, Color backgroundColor) {
        FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
        layout.setHgap(0);
        layout.setVgap(0);
        this.setLayout(layout);
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(backgroundColor);

        this.originWidth = width;
        this.originHeight = height;
    }

    public void resize(){
        this.setPreferredSize(new Dimension(GUISettings.getResizedValue(originWidth), GUISettings.getResizedValue(originHeight)));
    }
}
