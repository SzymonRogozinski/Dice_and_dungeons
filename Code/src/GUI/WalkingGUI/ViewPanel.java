package GUI.WalkingGUI;

import GUI.GUISettings;
import Game.GameCollection;
import Walking.FogOfWar;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

//Panel for rendering map view
public class ViewPanel extends JPanel {
    private final int scale;
    private final int viewSize;

    public ViewPanel() {
        //set panel
        this.setSize(GUISettings.PANEL_SIZE,GUISettings.PANEL_SIZE);
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        this.setOpaque(true);

        viewSize= FogOfWar.getSize();
        scale= GUISettings.PANEL_SIZE / FogOfWar.getSize();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

        for (int i = 0; i < viewSize; i++) {
            for (int j = 0; j < viewSize; j++) {
                g2D.drawImage(GameCollection.getWalkingManager().getWalking().getMap().getPlace(j+ GameCollection.getWalkingManager().getWalking().fogOfWar.getMinX(), i+ GameCollection.getWalkingManager().getWalking().fogOfWar.getMinY()).getImage(), j * scale, i * scale, scale, scale, null);
            }
        }
    }
}
