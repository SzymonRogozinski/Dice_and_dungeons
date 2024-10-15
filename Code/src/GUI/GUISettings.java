package GUI;

import java.awt.*;

public class GUISettings {

    public static final Font SMALL_FONT = new Font("Audiowide", Font.ITALIC, 12);
    public static final Font BIG_FONT = new Font("Audiowide", Font.BOLD, 16);
    public static final Font TITLE_FONT = new Font("Audiowide", Font.BOLD, 40);
    public static final Font BUTTON_FONT = new Font("Audiowide", Font.BOLD, 12);
    public static final int heightAndWidth = 600;
    public final static int PANEL_SIZE = heightAndWidth * 3 / 4;
    public final static int SMALL_PANEL_SIZE = PANEL_SIZE / 3;
    public final static int CHARACTER_HEIGHT = GUISettings.PANEL_SIZE / 4;
    public final static int CHARACTER_WIDTH = GUISettings.PANEL_SIZE / 6;
    public static final int ITEM_ICON_SIZE = GUISettings.heightAndWidth / 10;
    public final static Color TRANSPARENT = new Color(0, 0, 0, 0);

}


