package GUI.WalkingGUI;

import GUI.GUISettings;
import Game.GameManager;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Arrows extends JPanel {
    public Arrows(Border border) {
        //set panel
        this.setSize(GUISettings.SMALL_PANEL_SIZE,GUISettings.SMALL_PANEL_SIZE);
        this.setBackground(Color.BLACK);
        this.setBorder(border);

        this.setLayout(new GridLayout(3,3));

        JButton up = new JButton(new ImageIcon("Texture/Buttons/up_arrow.png"));
        up.setFocusable(false);
        up.addActionListener(e -> GameManager.getWalkingManager().getWalking().playerMove(0, -1));

        JButton left = new JButton(new ImageIcon("Texture/Buttons/left_arrow.png"));
        left.setFocusable(false);
        left.addActionListener(e -> GameManager.getWalkingManager().getWalking().playerMove(-1, 0));

        JButton right = new JButton(new ImageIcon("Texture/Buttons/right_arrow.png"));
        right.setFocusable(false);
        right.addActionListener(e -> GameManager.getWalkingManager().getWalking().playerMove(1, 0));

        JButton down = new JButton(new ImageIcon("Texture/Buttons/down_arrow.png"));
        down.setFocusable(false);
        down.addActionListener(e -> GameManager.getWalkingManager().getWalking().playerMove(0, 1));

        this.add(new JLabel());
        this.add(up);
        this.add(new JLabel());
        this.add(left);
        this.add(new JLabel());
        this.add(right);
        this.add(new JLabel());
        this.add(down);
        this.add(new JLabel());
    }
}
