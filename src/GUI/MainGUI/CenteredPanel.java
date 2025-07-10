package GUI.MainGUI;

import Game.GameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class CenteredPanel extends JPanel {

    public CenteredPanel() {
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.BLACK);
        this.addComponentListener(new ResizeListener());
    }

    private class ResizeListener extends ComponentAdapter {
        @Override
        public void componentResized(ComponentEvent e) {
            try {
                GameManager.getMainFrame().getMainPanel().resize(Math.min(e.getComponent().getWidth(),e.getComponent().getHeight()));
            } catch(NullPointerException ignore){}
        }
    }
}
