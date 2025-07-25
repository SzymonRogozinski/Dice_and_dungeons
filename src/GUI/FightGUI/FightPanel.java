package GUI.FightGUI;

import Character.Enemy.EnemyCategory;
import GUI.GUISettings;
import Game.GameManager;
import Game.PlayerInfo;
import GUI.FightGUI.Components.*;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.util.ArrayList;

public class FightPanel extends JPanel {

    private final FightPanelState state;

    public FightPanel(Border border) {
        //Set display
        this.setBounds(0, 0, GUISettings.PANEL_SIZE, GUISettings.PANEL_SIZE);
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        this.setBorder(border);

        state = new FightPanelState(false,-1,new ArrayList<>(),new ArrayList<>(), false, 255);

    }

    public void refresh() {
        if (state.enemyPanelList.isEmpty() && state.playerPanelList.isEmpty()) {
            //Draw enemies
            for (int i = 0; i < GameManager.getFight().getEnemyCount(); i++) {
                EnemyPanel enemy = new EnemyPanel(i,state);
                enemy.resize();
                state.enemyPanelList.add(enemy);
            }
            for (EnemyPanel enemy : state.enemyPanelList)
                this.add(enemy);
            //Draw player
            for (int i = 0; i < PlayerInfo.getParty().getCharacters().size(); i++) {
                PlayerPanel player = new PlayerPanel(i,state);
                player.resize();
                state.playerPanelList.add(player);
            }
            for (PlayerPanel player : state.playerPanelList)
                this.add(player);

            setLabels();
        } else if (state.enemyPanelList.getFirst().enemy != GameManager.getFight().getEnemies().getFirst()) {
            this.removeAll();
            state.enemyPanelList.clear();
            state.playerPanelList.clear();
            //Draw enemies
            for (int i = 0; i < GameManager.getFight().getEnemyCount(); i++) {
                EnemyPanel enemy = new EnemyPanel(i,state);
                enemy.resize();
                state.enemyPanelList.add(enemy);
            }
            for (EnemyPanel enemy : state.enemyPanelList)
                this.add(enemy);
            //Draw player
            for (int i = 0; i < PlayerInfo.getParty().getCharacters().size(); i++) {
                PlayerPanel player = new PlayerPanel(i,state);
                player.resize();
                state.playerPanelList.add(player);
            }
            for (PlayerPanel player : state.playerPanelList)
                this.add(player);
            setLabels();
        } else {
            for (EnemyPanel enemy : state.enemyPanelList)
                enemy.refresh();
            for (PlayerPanel player : state.playerPanelList)
                player.refresh();
        }
        //Border flash
        state.colorValue += state.isRaising ? 3 : -3;
        if (state.colorValue >= 255 || state.colorValue <= 0)
            state.isRaising = !state.isRaising;
        state.selectedColor = new Color(state.colorValue, 0, 0);
    }

    public void enemySelectable(boolean selectableFlag) {
        state.selectableFlag = selectableFlag;
    }

    private void setLabels() {
        int yOffSet = GUISettings.getResizedValue(GUISettings.CHARACTER_HEIGHT / 2);
        int playerYOffSet = GUISettings.getResizedValue(GUISettings.PANEL_SIZE - 3 * GUISettings.CHARACTER_HEIGHT / 2);
        //Set boss
        if (state.enemyPanelList.size() == 1 && state.enemyPanelList.getFirst().enemy.getCategory() == EnemyCategory.Boss) {
            EnemyPanel bossPanel = state.enemyPanelList.getFirst();
            int bossX = GUISettings.getResizedValue((GUISettings.PANEL_SIZE - GUISettings.CHARACTER_WIDTH * 2) / 2);
            bossPanel.setLocation(bossX, yOffSet / 2);
        } else { //Set enemies labels. Max 3!
            int xSpace = GUISettings.getResizedValue((GUISettings.PANEL_SIZE - GUISettings.CHARACTER_WIDTH * state.enemyPanelList.size()) / (state.enemyPanelList.size() + 1));
            for (int i = 0; i < state.enemyPanelList.size(); i++) {
                state.enemyPanelList.get(i).setLocation((xSpace + GUISettings.getResizedValue(GUISettings.CHARACTER_WIDTH)) * i + xSpace, yOffSet);
            }
        }
        //Set player labels. Max 3!
        int xSpace = GUISettings.getResizedValue((GUISettings.PANEL_SIZE - GUISettings.CHARACTER_WIDTH * state.playerPanelList.size()) / (state.playerPanelList.size() + 1));
        for (int i = 0; i < state.playerPanelList.size(); i++) {
            state.playerPanelList.get(i).setLocation((xSpace + GUISettings.getResizedValue(GUISettings.CHARACTER_WIDTH)) * i + xSpace, playerYOffSet);
        }
    }

    public void resize(){
        this.setBounds(0, 0, GUISettings.getResizedValue(GUISettings.PANEL_SIZE), GUISettings.getResizedValue(GUISettings.PANEL_SIZE));

        for(EnemyPanel enemyPanel:state.enemyPanelList)
            enemyPanel.resize();

        for(PlayerPanel playerPanel: state.playerPanelList)
            playerPanel.resize();

        setLabels();
    }

}
