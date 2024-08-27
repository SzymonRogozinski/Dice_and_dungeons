package GUI.WalkingGUI;

import GUI.GUISettings;
import Game.Game;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Arrows extends JPanel {
    public Arrows(Border border) {
        //set panel
        this.setSize(GUISettings.SMALL_PANEL_SIZE,GUISettings.SMALL_PANEL_SIZE);
        this.setBackground(Color.BLACK);
        this.setBorder(border);

        this.setLayout(null);

        JButton up = new JButton(new ImageIcon("Texture/Buttons/up_arrow.png"));
        up.setFont(GUISettings.SMALL_FONT);
        up.setFocusable(false);
        up.setBounds(GUISettings.SMALL_PANEL_SIZE / 3 + 1, 2, GUISettings.SMALL_PANEL_SIZE / 3, GUISettings.SMALL_PANEL_SIZE / 3 - 1);
        up.addActionListener(e -> Game.getWalkingManager().getWalking().playerMove(0, -1));

        JButton left = new JButton(new ImageIcon("Texture/Buttons/left_arrow.png"));
        left.setFont(GUISettings.SMALL_FONT);
        left.setFocusable(false);
        left.setBounds(2, GUISettings.SMALL_PANEL_SIZE / 3, GUISettings.SMALL_PANEL_SIZE / 3, GUISettings.SMALL_PANEL_SIZE / 3);
        left.addActionListener(e -> Game.getWalkingManager().getWalking().playerMove(-1, 0));

        JButton right = new JButton(new ImageIcon("Texture/Buttons/right_arrow.png"));
        right.setFont(GUISettings.SMALL_FONT);
        right.setFocusable(false);
        right.setBounds(2 * GUISettings.SMALL_PANEL_SIZE / 3, GUISettings.SMALL_PANEL_SIZE / 3, GUISettings.SMALL_PANEL_SIZE / 3 - 1, GUISettings.SMALL_PANEL_SIZE / 3);
        right.addActionListener(e -> Game.getWalkingManager().getWalking().playerMove(1, 0));

        JButton down = new JButton(new ImageIcon("Texture/Buttons/down_arrow.png"));
        down.setFont(GUISettings.SMALL_FONT);
        down.setFocusable(false);
        down.setBounds(GUISettings.SMALL_PANEL_SIZE / 3 + 1, 2 * GUISettings.SMALL_PANEL_SIZE / 3 - 2, GUISettings.SMALL_PANEL_SIZE / 3, GUISettings.SMALL_PANEL_SIZE / 3);
        down.addActionListener(e -> Game.getWalkingManager().getWalking().playerMove(0, 1));

        this.add(up);
        this.add(left);
        this.add(right);
        this.add(down);
    }
}
