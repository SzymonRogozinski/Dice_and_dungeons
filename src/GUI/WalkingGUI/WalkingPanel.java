package GUI.WalkingGUI;

import GUI.GUISettings;
import Game.GameManager;
import Walking.FogOfWar;

import javax.swing.*;
import java.awt.*;

//Panel for rendering map view
public class WalkingPanel extends JPanel {

    private final int margin = 3;
    private final int scale;
    private final int viewSize;

    public WalkingPanel() {
        //set panel
        this.setSize(GUISettings.PANEL_SIZE - margin * 2, GUISettings.PANEL_SIZE - margin * 2);
        this.setLayout(null);
        //this.setBackground(Color.BLACK);
        this.setOpaque(true);

        viewSize = FogOfWar.getSize();
        scale = (GUISettings.PANEL_SIZE - margin * 2) / FogOfWar.getSize();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

        for (int i = 0; i < viewSize; i++) {
            for (int j = 0; j < viewSize; j++) {
                g2D.drawImage(GameManager.getWalkingManager().getWalking().getMap().getPlace(j + GameManager.getWalkingManager().getWalking().fogOfWar.getMinX(), i + GameManager.getWalkingManager().getWalking().fogOfWar.getMinY()).getImage(), j * scale + margin, i * scale + margin, scale, scale, null);
            }
        }
    }
}
