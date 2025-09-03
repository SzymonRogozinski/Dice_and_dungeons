package GUI.WalkingGUI;

import GUI.Components.DimensionlessGameLabel;
import GUI.Components.GameLabel;
import GUI.GUISettings;
import Game.GameManager;
import Game.GameUtils;
import Game.PlayerInfo;
import Quest.Quest;
import dg.generator.dungeon.Coordinate;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Arrows extends JPanel {

    private static final int IMAGE_SIZE = 50;
    private final JButton up, left, right, down;
    private final DimensionlessGameLabel navigate;

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

        navigate=new DimensionlessGameLabel(IMAGE_SIZE,IMAGE_SIZE);

        this.add(new JLabel());
        this.add(up);
        this.add(new JLabel());
        this.add(left);
        this.add(navigate);
        this.add(right);
        this.add(new JLabel());
        this.add(down);
        this.add(new JLabel());
    }

    public void refresh(){
        if(GameManager.getQuestModule().getSelectedQuest()==-1 ||
                GameManager.getQuestModule().getQuests().get(GameManager.getQuestModule().getSelectedQuest()).isQuestDone()) {
            navigate.setIcon(null);
            return;
        }
        Quest q = GameManager.getQuestModule().getQuests().get(GameManager.getQuestModule().getSelectedQuest());
        Coordinate c = q.getQuestCoordinate();

        int dx = c.x - GameManager.getWalkingManager().getWalking().getPlayer().getPosX();
        int dy = c.y - GameManager.getWalkingManager().getWalking().getPlayer().getPosY();
        double ang = Math.atan2(dy,dx)+Math.PI/2; //Shift
        if (ang<0)
            navigate.setIcon(GameUtils.resizeIcon(new ImageIcon("Texture/Navigation/up-left.png"),GUISettings.getResizedValue(IMAGE_SIZE)));
        else if(ang<Math.PI/4)
            navigate.setIcon(GameUtils.resizeIcon(new ImageIcon("Texture/Navigation/up.png"),GUISettings.getResizedValue(IMAGE_SIZE)));
        else if (ang<Math.PI*1/2)
            navigate.setIcon(GameUtils.resizeIcon(new ImageIcon("Texture/Navigation/up-right.png"),GUISettings.getResizedValue(IMAGE_SIZE)));
        else if (ang<Math.PI*3/4)
            navigate.setIcon(GameUtils.resizeIcon(new ImageIcon("Texture/Navigation/right.png"),GUISettings.getResizedValue(IMAGE_SIZE)));
        else if (ang<Math.PI)
            navigate.setIcon(GameUtils.resizeIcon(new ImageIcon("Texture/Navigation/down-right.png"),GUISettings.getResizedValue(IMAGE_SIZE)));
        else if (ang<Math.PI*5/4)
            navigate.setIcon(GameUtils.resizeIcon(new ImageIcon("Texture/Navigation/down.png"),GUISettings.getResizedValue(IMAGE_SIZE)));
        else if (ang<Math.PI*3/2)
            navigate.setIcon(GameUtils.resizeIcon(new ImageIcon("Texture/Navigation/down-left.png"),GUISettings.getResizedValue(IMAGE_SIZE)));
        else
            navigate.setIcon(GameUtils.resizeIcon(new ImageIcon("Texture/Navigation/left.png"),GUISettings.getResizedValue(IMAGE_SIZE)));
    }

    public void resize(){
        this.setSize(GUISettings.getResizedValue(GUISettings.SMALL_PANEL_SIZE), GUISettings.getResizedValue(GUISettings.SMALL_PANEL_SIZE));

        up.setIcon(GameUtils.resizeIcon(new ImageIcon("Texture/Buttons/up_arrow.png"),GUISettings.getResizedValue(IMAGE_SIZE)));
        left.setIcon(GameUtils.resizeIcon(new ImageIcon("Texture/Buttons/left_arrow.png"),GUISettings.getResizedValue(IMAGE_SIZE)));
        right.setIcon(GameUtils.resizeIcon(new ImageIcon("Texture/Buttons/right_arrow.png"),GUISettings.getResizedValue(IMAGE_SIZE)));
        down.setIcon(GameUtils.resizeIcon(new ImageIcon("Texture/Buttons/down_arrow.png"),GUISettings.getResizedValue(IMAGE_SIZE)));
        navigate.resize();
    }
}
