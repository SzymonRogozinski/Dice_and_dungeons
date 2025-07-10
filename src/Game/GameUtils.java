package Game;

import javax.swing.*;

public class GameUtils {
    public static ImageIcon resizeIcon(ImageIcon image, int width, int height) {
        return new ImageIcon(image.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH));
    }

    public static ImageIcon resizeIcon(ImageIcon image, int size) {
        return new ImageIcon(image.getImage().getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH));
    }
}
