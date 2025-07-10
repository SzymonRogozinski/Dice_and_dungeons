package GUI.WalkingGUI;

import GUI.GUISettings;
import Game.GameManager;
import Game.GameUtils;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Arrows extends JPanel {

    private static final int IMAGE_SIZE = 50;
    private final JButton up, left, right, down;

    public Arrows(Border border) {
        //set panel
        this.setSize(GUISettings.SMALL_PANEL_SIZE, GUISettings.SMALL_PANEL_SIZE);
        this.setBackground(Color.BLACK);
        this.setBorder(border);

        this.setLayout(new GridLayout(3, 3));

        up = new JButton(new ImageIcon("Texture/Buttons/up_arrow.png"));
        up.setFocusable(false);
        up.addActionListener(_-> GameManager.getWalkingManager().getWalking().playerMoveByOne(0, -1)
        );

        left = new JButton(new ImageIcon("Texture/Buttons/left_arrow.png"));
        left.setFocusable(false);
        left.addActionListener(_ -> GameManager.getWalkingManager().getWalking().playerMoveByOne(-1, 0));

        right = new JButton(new ImageIcon("Texture/Buttons/right_arrow.png"));
        right.setFocusable(false);
        right.addActionListener(_ -> GameManager.getWalkingManager().getWalking().playerMoveByOne(1, 0));

        down = new JButton(new ImageIcon("Texture/Buttons/down_arrow.png"));
        down.setFocusable(false);
        down.addActionListener(_ -> GameManager.getWalkingManager().getWalking().playerMoveByOne(0, 1));

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

    public void resize(){
        this.setSize(GUISettings.getResizedValue(GUISettings.SMALL_PANEL_SIZE), GUISettings.getResizedValue(GUISettings.SMALL_PANEL_SIZE));

        up.setIcon(GameUtils.resizeIcon(new ImageIcon("Texture/Buttons/up_arrow.png"),GUISettings.getResizedValue(IMAGE_SIZE)));
        left.setIcon(GameUtils.resizeIcon(new ImageIcon("Texture/Buttons/left_arrow.png"),GUISettings.getResizedValue(IMAGE_SIZE)));
        right.setIcon(GameUtils.resizeIcon(new ImageIcon("Texture/Buttons/right_arrow.png"),GUISettings.getResizedValue(IMAGE_SIZE)));
        down.setIcon(GameUtils.resizeIcon(new ImageIcon("Texture/Buttons/down_arrow.png"),GUISettings.getResizedValue(IMAGE_SIZE)));
    }
}
