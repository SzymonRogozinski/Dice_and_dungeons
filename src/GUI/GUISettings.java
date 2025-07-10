package GUI;

import java.awt.*;

public class GUISettings {

    public static Font SMALL_FONT = new Font("Audiowide", Font.ITALIC, 12);
    public static Font DEFAULT_FONT = new Font("Audiowide", Font.PLAIN,12);
    public static Font BIG_FONT = new Font("Audiowide", Font.BOLD, 16);
    public static Font TITLE_FONT = new Font("Audiowide", Font.BOLD, 40);
    public static Font BUTTON_FONT = new Font("Audiowide", Font.BOLD, 12);
    public static final int heightAndWidth = 600;
    public final static int PANEL_SIZE = heightAndWidth * 3 / 4;
    public final static int SMALL_PANEL_SIZE = PANEL_SIZE / 3;
    public final static int CHARACTER_HEIGHT = GUISettings.PANEL_SIZE / 4;
    public final static int CHARACTER_WIDTH = GUISettings.PANEL_SIZE / 6;
    public static final int ITEM_ICON_SIZE = GUISettings.heightAndWidth / 10;
    public final static Color TRANSPARENT = new Color(0, 0, 0, 0);
    private static double windowProportion = 1.0;
    public static void setProportion(int newWindowSize){
        windowProportion = ((double) newWindowSize)/heightAndWidth;
        resizeFont();
    }

    public static int getResizedValue(int value){
        return (int)(value*windowProportion);
    }

    public static int getFontID(Font font){
        if(font==SMALL_FONT)
            return 1;
        else if(font==DEFAULT_FONT)
            return 2;
        else if(font==BIG_FONT)
            return 3;
        if(font==TITLE_FONT)
            return 4;
        if(font==BUTTON_FONT)
            return 5;
        else
            throw new IllegalArgumentException("Font cannot be found!");
    }

    public static Font getFontFromID(int id){
        if(id==1)
            return SMALL_FONT;
        else if(id==2)
            return DEFAULT_FONT;
        else if(id==3)
            return BIG_FONT;
        if(id==4)
            return TITLE_FONT;
        if(id==5)
            return BUTTON_FONT;
        else
            throw new IllegalArgumentException("Font cannot be found!");
    }

    private static void resizeFont(){
        float scale = (float) windowProportion;
        SMALL_FONT = SMALL_FONT.deriveFont(scale*12);
        DEFAULT_FONT = DEFAULT_FONT.deriveFont(scale*12);
        BIG_FONT = BIG_FONT.deriveFont(scale*16);
        BUTTON_FONT = BUTTON_FONT.deriveFont(scale*12);
        TITLE_FONT = TITLE_FONT.deriveFont(scale*40);
    }
}


