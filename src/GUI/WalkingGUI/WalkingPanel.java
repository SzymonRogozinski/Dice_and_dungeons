package GUI.WalkingGUI;

import GUI.GUISettings;
import Game.GameManager;
import Walking.FogOfWar;
import Walking.Places.GamePlace;
import Walking.Places.QuestPlace;

import javax.swing.*;
import java.awt.*;

//Panel for rendering map view
public class WalkingPanel extends JPanel {

    private final int margin = 3;
    private final int originScale, originViewSize;
    private int scale, viewSize;

    public WalkingPanel() {
        //set panel
        this.setSize(GUISettings.PANEL_SIZE - margin * 2, GUISettings.PANEL_SIZE - margin * 2);
        this.setLayout(null);
        this.setOpaque(true);

        originViewSize = FogOfWar.getSize();
        originScale = (GUISettings.PANEL_SIZE - margin * 2) / originViewSize;
        scale=originScale;
        viewSize=originViewSize;

    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

        for (int i = 0; i < originViewSize; i++) {
            for (int j = 0; j < originViewSize; j++) {
                GamePlace p = GameManager.getWalkingManager().getWalking().getMap().getPlace(
                        j + GameManager.getWalkingManager().getWalking().fogOfWar.getMinX(),
                        i + GameManager.getWalkingManager().getWalking().fogOfWar.getMinY());

                g2D.drawImage(p.getImage(), j * scale + margin, i * scale + margin, scale, scale, null);
                //Mark quest
                if(p instanceof QuestPlace questPlace && GameManager.getQuestModule().getSelectedQuest()!=-1){
                    if(questPlace.getQuest()==GameManager.getQuestModule().getQuests().get(GameManager.getQuestModule().getSelectedQuest())
                            && !questPlace.getQuest().isQuestDone()) {
                        g2D.setColor(Color.YELLOW);
                        g2D.drawRect(j * scale + margin, i * scale + margin, scale-1, scale-1);
                    }
                }
            }
        }
    }

    public void resize(){
        this.setSize(GUISettings.getResizedValue(GUISettings.PANEL_SIZE - margin * 2), GUISettings.getResizedValue(GUISettings.PANEL_SIZE - margin * 2));
        scale = GUISettings.getResizedValue(originScale);
        viewSize = GUISettings.getResizedValue(originViewSize);
    }
}
