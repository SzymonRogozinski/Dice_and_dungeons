package GUI.MenuGUI;

import GUI.Compents.GameLabel;
import GUI.GUISettings;

import javax.swing.*;
import java.awt.*;

public class PlayerWinPanel extends JPanel {

    private static final int TEXT_HEIGHT = GUISettings.heightAndWidth / 10;
    private static final int SUB_PANEL_HEIGHT = GUISettings.heightAndWidth / 4;

    public PlayerWinPanel() {
        //Setting panel
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        layout.setVgap((GUISettings.heightAndWidth - SUB_PANEL_HEIGHT) / 2);
        this.setLayout(layout);
        this.setPreferredSize(new Dimension(GUISettings.heightAndWidth, GUISettings.heightAndWidth));
        this.setBackground(Color.BLACK);

        //Set panels
        JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        textPanel.setPreferredSize(new Dimension(GUISettings.heightAndWidth, SUB_PANEL_HEIGHT));
        textPanel.setBackground(Color.BLACK);
        this.add(textPanel);

        //Set text
        GameLabel gameOverText = new GameLabel(
                "You win!", SwingConstants.CENTER,
                GUISettings.heightAndWidth, TEXT_HEIGHT,
                Color.WHITE

        );
        gameOverText.setFont(GUISettings.TITLE_FONT);
        textPanel.add(gameOverText);

        JLabel congratulation = new GameLabel(
                "Thank you for playing", SwingConstants.CENTER,
                GUISettings.heightAndWidth, TEXT_HEIGHT / 2,
                Color.WHITE

        );
        congratulation.setFont(GUISettings.SMALL_FONT);
        textPanel.add(congratulation);

        this.repaint();
        this.revalidate();
    }
}
