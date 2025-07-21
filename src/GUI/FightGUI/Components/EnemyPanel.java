package GUI.FightGUI.Components;

import Character.Enemy.EnemyCategory;
import Character.Enemy.EnemyCharacter;
import Fight.Statuses.GameStatus;
import GUI.Components.GameLabel;
import GUI.Components.GameProgressBar;
import GUI.FightGUI.FightPanel;
import GUI.GUISettings;
import Game.GameManager;
import Game.GameUtils;

import javax.swing.*;
import java.awt.*;

public class EnemyPanel  extends JPanel {

    private final GameLabel enemyLabel;
    private final GameProgressBar healthBar;
    public final EnemyCharacter enemy;
    private final GameLabel statusLabel;
    private final FlowLayout layout1,layout2;


    public EnemyPanel(int i, FightPanelState state) {

        layout1 = new FlowLayout();
        layout1.setVgap(1);
        this.setLayout(layout1);
        enemy = GameManager.getFight().getEnemies().get(i);

        if(enemy.getCategory()== EnemyCategory.Boss){
            this.setSize(GUISettings.CHARACTER_WIDTH * 2, FightPanelState.SUM_UP_HEIGHT_PANEL * 2);
            healthBar = new GameProgressBar(Color.RED, (GUISettings.CHARACTER_WIDTH - 2) * 2, (FightPanelState.HP_BAR_HEIGHT_PANEL - 1) * 2);
            statusLabel = new GameLabel(
                    "", SwingConstants.CENTER,
                    (GUISettings.CHARACTER_WIDTH - 2) * 2, FightPanelState.STATUS_HEIGHT_PANEL - 1,
                    Color.WHITE
            );
            enemyLabel = new GameLabel(GameUtils.resizeIcon(enemy.getImage(), (GUISettings.CHARACTER_WIDTH - 2) * 2, (FightPanelState.CHARACTER_HEIGHT_PANEL - 2) * 2), (GUISettings.CHARACTER_WIDTH - 2) * 2, (FightPanelState.CHARACTER_HEIGHT_PANEL - 2) * 2);
        }else{
            statusLabel = new GameLabel(
                    "", SwingConstants.CENTER,
                    GUISettings.CHARACTER_WIDTH - 2, FightPanelState.STATUS_HEIGHT_PANEL - 1,
                    Color.WHITE
            );
            healthBar = new GameProgressBar(Color.RED, GUISettings.CHARACTER_WIDTH - 2, FightPanelState.HP_BAR_HEIGHT_PANEL - 1);
            enemyLabel = new GameLabel(GameUtils.resizeIcon(enemy.getImage(), GUISettings.CHARACTER_WIDTH - 2, FightPanelState.CHARACTER_HEIGHT_PANEL - 2), GUISettings.CHARACTER_WIDTH - 2, FightPanelState.CHARACTER_HEIGHT_PANEL - 2);
        }


        healthBar.setMinimum(0);
        healthBar.setMaximum(enemy.getMaxHealth());

        layout2 = new FlowLayout();
        layout2.setHgap(1);
        layout2.setVgap(0);
        statusLabel.setLayout(layout2);

        this.add(enemyLabel);
        this.add(healthBar);
        this.add(statusLabel);

        this.setSize(GUISettings.CHARACTER_WIDTH, FightPanelState.SUM_UP_HEIGHT_PANEL);
        this.setBackground(Color.BLACK);
        this.setBorder(FightPanelState.labelBorder);
        enemyLabel.addMouseListener(new EnemyMouseListener(i, true,state));
        refresh();
    }

    public void refresh() {
        if (enemy.getCurrentHealth() == 0 && this.isVisible())
            this.setVisible(false);
        else {
            healthBar.setValue(enemy.getCurrentHealth());
            String healthString = STR."\{enemy.getCurrentHealth()}/\{enemy.getMaxHealth()}";
            if (enemy.getShield() > 0)
                healthString += STR." +\{enemy.getShield()}";
            healthBar.setString(healthString);

            //Set statuses
            //Refresh status only if one was deleted/added
            if (statusLabel.getComponentCount() == enemy.getStatuses().size())
                return;
            statusLabel.removeAll();
            for (GameStatus status : enemy.getStatuses()) {

                GameLabel statLabel = new GameLabel(GameUtils.resizeIcon(status.getIcon(), FightPanelState.statusIconSize),FightPanelState.statusIconSize,FightPanelState.statusIconSize);
                statLabel.resize();
                statLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
                statLabel.addMouseListener(new StatusMouseListener(status));
                statusLabel.add(statLabel);
            }
        }
    }

    public void resize(){
        this.setSize(GUISettings.getResizedValue(GUISettings.CHARACTER_WIDTH), GUISettings.getResizedValue(FightPanelState.SUM_UP_HEIGHT_PANEL));

        layout1.setVgap(GUISettings.getResizedValue(1));
        layout2.setHgap(GUISettings.getResizedValue(1));

        enemyLabel.resize();
        healthBar.resize();
        statusLabel.resize();

        statusLabel.removeAll();
        for (GameStatus status : enemy.getStatuses()) {

            GameLabel statLabel = new GameLabel(GameUtils.resizeIcon(status.getIcon(), FightPanelState.statusIconSize),FightPanelState.statusIconSize,FightPanelState.statusIconSize);
            statLabel.resize();
            statLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
            statLabel.addMouseListener(new StatusMouseListener(status));
            statusLabel.add(statLabel);
        }
    }

}
