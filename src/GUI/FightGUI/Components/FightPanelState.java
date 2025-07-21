package GUI.FightGUI.Components;

import GUI.GUISettings;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class FightPanelState {

    public static final int CHARACTER_HEIGHT_PANEL = (int) (GUISettings.CHARACTER_HEIGHT * 0.85);
    public static final int HP_BAR_HEIGHT_PANEL = (int) (GUISettings.CHARACTER_HEIGHT * 0.15);
    public static final int STATUS_HEIGHT_PANEL = (int) (GUISettings.CHARACTER_HEIGHT * 0.175);
    public static final int SUM_UP_HEIGHT_PANEL = CHARACTER_HEIGHT_PANEL + HP_BAR_HEIGHT_PANEL + STATUS_HEIGHT_PANEL;
    public static final int statusIconSize = Math.min((int) (GUISettings.CHARACTER_HEIGHT * 0.15) - 1, (GUISettings.CHARACTER_WIDTH - 2) / 3);
    public static final Border labelBorder = BorderFactory.createLineBorder(Color.BLACK, 1);

    public boolean selectableFlag;
    public int selectedEnemy;
    public Color selectedColor = new Color(255, 0, 0);
    public boolean isRaising;
    public int colorValue;
    public final Border selectedLabelBorder;
    public final ArrayList<EnemyPanel> enemyPanelList;
    public final ArrayList<PlayerPanel> playerPanelList;

    public FightPanelState(boolean selectableFlag, int selectedEnemy, ArrayList<EnemyPanel> enemyPanelList, ArrayList<PlayerPanel> playerPanelList, boolean isRaising, int colorValue) {
        this.selectableFlag = selectableFlag;
        this.selectedEnemy = selectedEnemy;
        this.selectedLabelBorder = new LineBorder(selectedColor, 1) {
            @Override
            public void paintBorder(final Component c, final Graphics g, final int x, final int y, final int width, final int height) {
                super.lineColor = selectedColor;
                super.paintBorder(c, g, x, y, width, height);
            }
        };
        this.enemyPanelList = enemyPanelList;
        this.playerPanelList = playerPanelList;
        this.isRaising=isRaising;
        this.colorValue=colorValue;
    }
}
