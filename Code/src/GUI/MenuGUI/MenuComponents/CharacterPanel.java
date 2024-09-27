package GUI.MenuGUI.MenuComponents;

import javax.swing.*;
import java.awt.*;

import Character.PlayerCharacter;
import GUI.GUISettings;
import Game.GameUtils;

public class CharacterPanel extends JPanel {
    private final JLabel playerLabel;
    private final PlayerCharacter playerCharacter;
    private final JLabel nameLabel;
    private boolean isSelected;

    public CharacterPanel(PlayerCharacter playerCharacter){
        FlowLayout layout=new FlowLayout();
        layout.setVgap(1);
        this.setLayout(layout);
        this.playerCharacter=playerCharacter;

        playerLabel = new JLabel(GameUtils.resizeIcon(playerCharacter.getImage(), GUISettings.CHARACTER_WIDTH-2, (int)(GUISettings.CHARACTER_HEIGHT*0.85)-2));

        nameLabel = new JLabel(playerCharacter.getName(),SwingConstants.CENTER);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setPreferredSize(new Dimension(GUISettings.CHARACTER_WIDTH-2,(int)(GUISettings.CHARACTER_HEIGHT*0.15)-1));

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

}
