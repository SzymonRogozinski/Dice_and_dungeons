package GUI.MenuGUI;

import GUI.Components.GameLabel;
import GUI.GUISettings;

import javax.swing.*;
import java.awt.*;

public class PlayerWinPanel extends JPanel {

    private static final int TEXT_HEIGHT = GUISettings.heightAndWidth / 10;
    private static final int SUB_PANEL_HEIGHT = GUISettings.heightAndWidth / 4;

    private final FlowLayout layout;
    private final JPanel textPanel;
    private final GameLabel gameOverText;
    private final GameLabel congratulation;

    public PlayerWinPanel() {
        //Setting panel
        layout = new FlowLayout(FlowLayout.CENTER);
        layout.setVgap((GUISettings.heightAndWidth - SUB_PANEL_HEIGHT) / 2);
        this.setLayout(layout);
        this.setPreferredSize(new Dimension(GUISettings.heightAndWidth, GUISettings.heightAndWidth));
        this.setBackground(Color.BLACK);

        //Set panels
        textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        textPanel.setPreferredSize(new Dimension(GUISettings.heightAndWidth, SUB_PANEL_HEIGHT));
        textPanel.setBackground(Color.BLACK);
        this.add(textPanel);

        //Set text
        gameOverText = new GameLabel(
                "You win!", SwingConstants.CENTER,
                GUISettings.heightAndWidth, TEXT_HEIGHT,
                Color.WHITE,GUISettings.TITLE_FONT

        );
        textPanel.add(gameOverText);

        congratulation = new GameLabel(
                "Thank you for playing", SwingConstants.CENTER,
                GUISettings.heightAndWidth, TEXT_HEIGHT / 2,
                Color.WHITE,GUISettings.SMALL_FONT

        );
        textPanel.add(congratulation);
    }

    public void resize(){
        this.setPreferredSize(new Dimension(GUISettings.getResizedValue(GUISettings.heightAndWidth), GUISettings.getResizedValue(GUISettings.heightAndWidth)));

        layout.setVgap(GUISettings.getResizedValue((GUISettings.heightAndWidth - SUB_PANEL_HEIGHT) / 2));

        textPanel.setPreferredSize(new Dimension(GUISettings.getResizedValue(GUISettings.heightAndWidth), GUISettings.getResizedValue(SUB_PANEL_HEIGHT)));

        gameOverText.resize();
        congratulation.resize();
    }
}
