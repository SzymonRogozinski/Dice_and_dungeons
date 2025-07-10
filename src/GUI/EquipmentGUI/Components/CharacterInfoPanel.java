package GUI.EquipmentGUI.Components;

import GUI.Components.GameLabel;
import GUI.GUISettings;
import Game.GameManager;
import Character.PlayerCharacter;

import javax.swing.*;
import java.awt.*;

public class CharacterInfoPanel extends JPanel {

    //name,strength,endurance,intelligence,charisma, cunning,luck
    private final GameLabel[] statisticLabels;
    private final FlowLayout layout;

    public CharacterInfoPanel() {
        this.setPreferredSize(new Dimension(GUISettings.SMALL_PANEL_SIZE - 20, GUISettings.PANEL_SIZE * 25/100));
        layout = new FlowLayout(FlowLayout.CENTER);
        layout.setVgap(1);
        this.setLayout(layout);
        this.setBackground(Color.BLACK);

        statisticLabels = new GameLabel[7];
        for (int i = 0; i < 7; i++) {
            statisticLabels[i] = new GameLabel(
                    "",
                    SwingConstants.LEFT,
                    GUISettings.SMALL_PANEL_SIZE - 20,
                    GUISettings.SMALL_PANEL_SIZE / 10,
                    Color.WHITE
            );
            this.add(statisticLabels[i]);
        }
    }

    public void refresh() {
        PlayerCharacter player = GameManager.getEquipment().getCurrentCharacter();
        statisticLabels[0].setText(player.getName());
        statisticLabels[1].setText(STR."Strength: \{player.getStrength()}");
        statisticLabels[2].setText(STR."Endurance: \{player.getEndurance()}");
        statisticLabels[3].setText(STR."Intelligence: \{player.getIntelligence()}");
        statisticLabels[4].setText(STR."Charisma: \{player.getCharisma()}");
        statisticLabels[5].setText(STR."Cunning: \{player.getCunning()}");
        statisticLabels[6].setText(STR."Luck: \{player.getLuck()}");
    }

    public void resize(){
        this.setPreferredSize(new Dimension(GUISettings.getResizedValue(GUISettings.SMALL_PANEL_SIZE - 20), GUISettings.getResizedValue(GUISettings.PANEL_SIZE * 25/100)));

        layout.setVgap(GUISettings.getResizedValue(1));

        for (int i = 0; i < 7; i++)
            statisticLabels[i].resize();
    }
}
