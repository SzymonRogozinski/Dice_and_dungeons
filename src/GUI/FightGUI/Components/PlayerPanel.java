package GUI.FightGUI.Components;

import Fight.Statuses.GameStatus;
import GUI.Components.GameLabel;
import GUI.GUISettings;
import Game.GameUtils;
import Game.PlayerInfo;
import Character.PlayerCharacter;

import javax.swing.*;
import java.awt.*;

public class PlayerPanel extends JPanel {

    private final GameLabel playerLabel,statusLabel;
    private final PlayerCharacter playerCharacter;
    private final FlowLayout layout1,layout2;

    public PlayerPanel(int i, FightPanelState state) {
        layout1 = new FlowLayout();
        layout1.setVgap(1);
        this.setLayout(layout1);
        playerCharacter = PlayerInfo.getParty().getCharacters().get(i);

        playerLabel = new GameLabel(GameUtils.resizeIcon(playerCharacter.getImage(), GUISettings.CHARACTER_WIDTH - 2, FightPanelState.CHARACTER_HEIGHT_PANEL - 2), GUISettings.CHARACTER_WIDTH - 2, FightPanelState.CHARACTER_HEIGHT_PANEL - 2);

        statusLabel = new GameLabel(
                "", SwingConstants.CENTER,
                GUISettings.CHARACTER_WIDTH - 2, FightPanelState.STATUS_HEIGHT_PANEL - 1,
                Color.WHITE
        );
        layout2 = new FlowLayout();
        layout2.setHgap(1);
        layout2.setVgap(0);
        statusLabel.setLayout(layout2);

        this.add(playerLabel);
        this.add(statusLabel);

        this.setSize(GUISettings.CHARACTER_WIDTH, FightPanelState.SUM_UP_HEIGHT_PANEL - FightPanelState.HP_BAR_HEIGHT_PANEL);
        this.setBackground(Color.BLACK);
        this.setBorder(FightPanelState.labelBorder);
        playerLabel.addMouseListener(new EnemyMouseListener(i, false, state));
        refresh();
    }

    public void refresh() {
        //Refresh status only if one was deleted/added
        if (statusLabel.getComponentCount() == playerCharacter.getStatuses().size())
            return;
        statusLabel.removeAll();
        for (GameStatus status : playerCharacter.getStatuses()) {
            GameLabel statLabel = new GameLabel(GameUtils.resizeIcon(status.getIcon(), FightPanelState.statusIconSize),FightPanelState.statusIconSize,FightPanelState.statusIconSize);
            statLabel.resize();
            statLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
            statLabel.addMouseListener(new StatusMouseListener(status));
            statusLabel.add(statLabel);
        }
    }

    public void resize(){
        this.setSize(GUISettings.getResizedValue(GUISettings.CHARACTER_WIDTH), GUISettings.getResizedValue(FightPanelState.SUM_UP_HEIGHT_PANEL - FightPanelState.HP_BAR_HEIGHT_PANEL));

        layout1.setVgap(GUISettings.getResizedValue(1));
        layout2.setHgap(GUISettings.getResizedValue(1));

        playerLabel.resize();
        statusLabel.resize();

        statusLabel.removeAll();
        for (GameStatus status : playerCharacter.getStatuses()) {
            GameLabel statLabel = new GameLabel(GameUtils.resizeIcon(status.getIcon(), FightPanelState.statusIconSize),FightPanelState.statusIconSize,FightPanelState.statusIconSize);
            statLabel.resize();
            statLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
            statLabel.addMouseListener(new StatusMouseListener(status));
            statusLabel.add(statLabel);
        }
    }
}
