package GUI.QuestGUI;

import GUI.Components.GameButton;
import GUI.GUISettings;
import Game.GameActionQueue;
import Game.GameManager;
import Game.GameStates;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class NavigationPanel extends JPanel {

    private final GameButton equipment, backpack, goBack;
    private final FlowLayout layout;

    public NavigationPanel(Border border){
        this.setSize(GUISettings.SMALL_PANEL_SIZE, GUISettings.SMALL_PANEL_SIZE);
        this.setBackground(Color.BLACK);
        this.setBorder(border);

        layout=new FlowLayout(FlowLayout.CENTER);
        layout.setVgap(GUISettings.SMALL_PANEL_SIZE/10);
        this.setLayout(layout);

        equipment = new GameButton("Equipment",GUISettings.SMALL_PANEL_SIZE*8/10,GUISettings.SMALL_PANEL_SIZE/5,
                _ -> GameActionQueue.action(()-> {
                    GameManager.getEquipment().changeViewToEquipment();
                    GameManager.changeState(GameStates.EQUIPMENT);;
                }));
        backpack = new GameButton("Backpack",GUISettings.SMALL_PANEL_SIZE*8/10,GUISettings.SMALL_PANEL_SIZE/5,
                _ -> GameActionQueue.action(()-> {
                    GameManager.getEquipment().changeViewToBackpack();
                    GameManager.changeState(GameStates.EQUIPMENT);
                }));
        goBack = new GameButton("Close",GUISettings.SMALL_PANEL_SIZE*8/10,GUISettings.SMALL_PANEL_SIZE/5,
                _ -> GameActionQueue.action(()->GameManager.changeState(GameStates.WALKING)));

        this.add(equipment);
        this.add(backpack);
        this.add(goBack);
    }

    public void resize(){
        this.setSize(GUISettings.getResizedValue(GUISettings.SMALL_PANEL_SIZE), GUISettings.getResizedValue(GUISettings.SMALL_PANEL_SIZE));

        layout.setVgap(GUISettings.getResizedValue(GUISettings.SMALL_PANEL_SIZE/10));

        equipment.resize();
        backpack.resize();
        goBack.resize();
    }
}
