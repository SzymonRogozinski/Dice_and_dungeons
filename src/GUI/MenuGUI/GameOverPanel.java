package GUI.MenuGUI;

import GUI.Compents.GameLabel;
import GUI.GUISettings;

import javax.swing.*;
import java.awt.*;

public class GameOverPanel extends JPanel {

    private static final int TEXT_HEIGHT = GUISettings.heightAndWidth / 5;

    public GameOverPanel() {
        //Setting panel
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        layout.setVgap((GUISettings.heightAndWidth - TEXT_HEIGHT) / 2);
        this.setLayout(layout);
        this.setPreferredSize(new Dimension(GUISettings.heightAndWidth, GUISettings.heightAndWidth));
        this.setBackground(Color.BLACK);

        //Set text
        GameLabel gameOverText = new GameLabel(
                "Game Over", SwingConstants.CENTER,
                GUISettings.heightAndWidth, TEXT_HEIGHT,
                Color.WHITE
        );
        gameOverText.setFont(GUISettings.TITLE_FONT);

        this.add(gameOverText);
    }
}
