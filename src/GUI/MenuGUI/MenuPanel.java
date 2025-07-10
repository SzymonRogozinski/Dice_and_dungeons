package GUI.MenuGUI;

import GUI.Components.GameButton;
import GUI.GUISettings;
import Game.GameManager;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    private final static int menuSize = GUISettings.heightAndWidth / 3;
    private final JLabel title;
    private final FlowLayout layout;
    private final JPanel menu;
    private final GameButton newGame;

    public MenuPanel() {
        //Setting panel
        this.setLayout(null);
        this.setPreferredSize(new Dimension(GUISettings.heightAndWidth, GUISettings.heightAndWidth));
        this.setBackground(Color.BLACK);

        //Set title
        title = new JLabel("Dice and Dungeons", SwingConstants.CENTER);
        title.setBounds(0, 0, GUISettings.heightAndWidth, GUISettings.heightAndWidth / 5);
        title.setFont(GUISettings.TITLE_FONT);
        title.setForeground(Color.WHITE);

        //Set menu
        layout = new FlowLayout();
        layout.setVgap(menuSize / 4);

        menu = new JPanel();
        menu.setLayout(layout);
        menu.setBounds(menuSize, menuSize, menuSize, menuSize);
        menu.setBackground(Color.BLACK);

        newGame = new GameButton(
                "New game",
                menuSize * 3 / 5, menuSize / 8,
                _ -> GameManager.getMenuModule().changeToChoose()
        );
        newGame.setFont(GUISettings.BUTTON_FONT);

        menu.add(newGame);

        this.add(title);
        this.add(menu);
    }

    public void resize(){
        this.setPreferredSize(new Dimension(GUISettings.getResizedValue(GUISettings.heightAndWidth),GUISettings.getResizedValue(GUISettings.heightAndWidth)));

        title.setBounds(0, 0, GUISettings.getResizedValue(GUISettings.heightAndWidth), GUISettings.getResizedValue(GUISettings.heightAndWidth / 5));
        title.setFont(GUISettings.TITLE_FONT);

        layout.setVgap(GUISettings.getResizedValue(menuSize / 4));

        menu.setBounds(GUISettings.getResizedValue(menuSize), GUISettings.getResizedValue(menuSize), GUISettings.getResizedValue(menuSize), GUISettings.getResizedValue(menuSize));

        newGame.setPreferredSize(new Dimension(GUISettings.getResizedValue(menuSize * 3 / 5),GUISettings.getResizedValue(menuSize / 8)));
        newGame.setFont(GUISettings.BUTTON_FONT);
    }
}
