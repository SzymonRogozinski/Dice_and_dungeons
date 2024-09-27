package GUI.MenuGUI;

import GUI.GUISettings;

import javax.swing.*;
import java.awt.*;

public class GameOverPanel extends JPanel {

    private static final int TEXT_HEIGHT = GUISettings.heightAndWidth/5;

    public GameOverPanel() {
        //Setting panel
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        layout.setVgap((GUISettings.heightAndWidth-TEXT_HEIGHT)/2);
        this.setLayout(layout);
        this.setPreferredSize(new Dimension(GUISettings.heightAndWidth,GUISettings.heightAndWidth));
        this.setBackground(Color.BLACK);

        //Set text
        JLabel gameOverText = new JLabel("Game Over", SwingConstants.CENTER);
        gameOverText.setPreferredSize(new Dimension(GUISettings.heightAndWidth,TEXT_HEIGHT));
        gameOverText.setFont(GUISettings.TITLE_FONT);
        gameOverText.setForeground(Color.WHITE);

        this.add(gameOverText);
    }
}
