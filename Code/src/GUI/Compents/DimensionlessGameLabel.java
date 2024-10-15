package GUI.Compents;

import javax.swing.*;
import java.awt.*;

public class DimensionlessGameLabel extends JLabel {

    public DimensionlessGameLabel(String text, int orientation, Font font, Color foreground) {
        this.setText(text);
        this.setHorizontalAlignment(orientation);
        this.setFont(font);
        this.setForeground(foreground);
    }

    public DimensionlessGameLabel(String text, int orientation, Color foreground) {
        this.setText(text);
        this.setHorizontalAlignment(orientation);
        this.setForeground(foreground);
    }
}
