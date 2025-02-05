package GUI.MenuGUI;

import GUI.Compents.GameButton;
import GUI.GUISettings;
import Game.GameManager;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    private final static int menuSize = GUISettings.heightAndWidth / 3;

    public MenuPanel() {
        //Setting panel
        this.setLayout(null);
        this.setPreferredSize(new Dimension(GUISettings.heightAndWidth, GUISettings.heightAndWidth));
        this.setBackground(Color.BLACK);

        //Set title
        JLabel title = new JLabel("Dice and Dungeons", SwingConstants.CENTER);
        title.setBounds(0, 0, GUISettings.heightAndWidth, GUISettings.heightAndWidth / 5);
        title.setFont(GUISettings.TITLE_FONT);
        title.setForeground(Color.WHITE);

        //Set menu
        FlowLayout layout = new FlowLayout();
        layout.setVgap(menuSize / 4);

        JPanel menu = new JPanel();
        menu.setLayout(layout);
        menu.setBounds(menuSize, menuSize, menuSize, menuSize);
        menu.setBackground(Color.BLACK);

        GameButton newGame = new GameButton(
                "New game",
                menuSize * 3 / 5, menuSize / 8,
                _ -> GameManager.getMenuModule().changeToChoose()
        );
        newGame.setFont(GUISettings.BUTTON_FONT);

        menu.add(newGame);

        this.add(title);
        this.add(menu);

        this.repaint();
        this.revalidate();

    }
}
