package GUI.MenuGUI.MenuComponents;

import Character.PlayerCharacter;
import GUI.Components.GameLabel;
import GUI.GUISettings;
import Game.GameUtils;

import javax.swing.*;
import java.awt.*;

public class CharacterPanel extends JPanel {
    private final PlayerCharacter playerCharacter;
    private boolean isSelected;

    private final FlowLayout layout;
    private final JLabel playerLabel;
    private final GameLabel nameLabel;
    private final ImageIcon frontIcon;

    public CharacterPanel(PlayerCharacter playerCharacter, ImageIcon frontIcon) {
        layout = new FlowLayout();
        layout.setVgap(1);
        this.setLayout(layout);
        this.playerCharacter = playerCharacter;
        this.frontIcon=frontIcon;

        playerLabel = new JLabel(GameUtils.resizeIcon(frontIcon, GUISettings.CHARACTER_WIDTH - 2, GUISettings.CHARACTER_HEIGHT * 85/100 - 2));

        nameLabel = new GameLabel(playerCharacter.getName(), SwingConstants.CENTER,GUISettings.CHARACTER_WIDTH - 2, GUISettings.CHARACTER_HEIGHT * 15/100 - 1,Color.WHITE);

        this.add(playerLabel);
        this.add(nameLabel);

        this.setSize(GUISettings.CHARACTER_WIDTH, GUISettings.CHARACTER_HEIGHT);
        this.setBackground(Color.BLACK);
    }

    public PlayerCharacter getPlayerCharacter() {
        return playerCharacter;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public void resize(){
        this.setSize(GUISettings.getResizedValue(GUISettings.CHARACTER_WIDTH), GUISettings.getResizedValue(GUISettings.CHARACTER_HEIGHT));

        layout.setVgap(GUISettings.getResizedValue(1));

        playerLabel.setIcon(GameUtils.resizeIcon(frontIcon, GUISettings.getResizedValue(GUISettings.CHARACTER_WIDTH - 2), GUISettings.getResizedValue(GUISettings.CHARACTER_HEIGHT * 85/100 - 2)));

        nameLabel.resize();
    }

}
