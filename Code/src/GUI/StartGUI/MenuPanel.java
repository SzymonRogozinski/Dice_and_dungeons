package GUI.StartGUI;

import GUI.GUISettings;
import Game.Game;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    public MenuPanel() {
        //Setting panel
        this.setLayout(null);
        this.setPreferredSize(new Dimension(GUISettings.heightAndWidth,GUISettings.heightAndWidth));
        this.setBackground(Color.BLACK);

        //Set title
        JLabel title = new JLabel("Dice and Dungeons", SwingConstants.CENTER);
        title.setBounds(0,0,GUISettings.heightAndWidth,GUISettings.heightAndWidth/5);
        title.setFont(GUISettings.TITLE_FONT);
        title.setForeground(Color.WHITE);

        //Set menu
        int menuSize= GUISettings.heightAndWidth/3;
        FlowLayout layout = new FlowLayout();
        layout.setVgap(menuSize/4);
        JPanel menu = new JPanel();
        menu.setLayout(layout);
        menu.setBounds(menuSize,menuSize,menuSize,menuSize);
        menu.setBackground(Color.BLACK);

        JButton newGame = new JButton("New game");
        newGame.setFont(GUISettings.BUTTON_FONT);
        newGame.setPreferredSize(new Dimension(menuSize/2,menuSize/8));
        newGame.addActionListener(e->Game.getMenuModule().changeToChoose());

        JButton loadGame = new JButton("Load game");
        loadGame.setFont(GUISettings.BUTTON_FONT);
        loadGame.setPreferredSize(new Dimension(menuSize/2,menuSize/8));

        menu.add(newGame);
        menu.add(loadGame);

        this.add(title);
        this.add(menu);

        this.repaint();
        this.revalidate();

    }
}
