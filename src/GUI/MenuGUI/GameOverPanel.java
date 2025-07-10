package GUI.MenuGUI;

import GUI.Components.GameLabel;
import GUI.GUISettings;

import javax.swing.*;
import java.awt.*;

public class GameOverPanel extends JPanel {

    private static final int TEXT_HEIGHT = GUISettings.heightAndWidth / 5;
    private final FlowLayout layout;
    private final GameLabel gameOverText;

    public GameOverPanel() {
        //Setting panel
        layout = new FlowLayout(FlowLayout.CENTER);
        layout.setVgap((GUISettings.heightAndWidth - TEXT_HEIGHT) / 2);
        this.setLayout(layout);
        this.setPreferredSize(new Dimension(GUISettings.heightAndWidth, GUISettings.heightAndWidth));
        this.setBackground(Color.BLACK);

        //Set text
        gameOverText = new GameLabel(
                "Game Over", SwingConstants.CENTER,
                GUISettings.heightAndWidth, TEXT_HEIGHT,
                Color.WHITE,GUISettings.TITLE_FONT
        );

        this.add(gameOverText);
    }

    public void resize(){
        this.setPreferredSize(new Dimension(GUISettings.getResizedValue(GUISettings.heightAndWidth), GUISettings.getResizedValue(GUISettings.heightAndWidth)));

        layout.setVgap(GUISettings.getResizedValue((GUISettings.heightAndWidth - TEXT_HEIGHT) / 2));

        gameOverText.resize();
    }
}
