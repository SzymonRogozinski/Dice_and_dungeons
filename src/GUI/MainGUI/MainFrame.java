package GUI.MainGUI;

import GUI.DialogGUI.DialogView;
import GUI.EquipmentGUI.EquipmentView;
import GUI.FightGUI.FightView;
import GUI.MenuGUI.MenuView;
import GUI.QuestGUI.QuestView;
import GUI.TradeGUI.TradeView;
import GUI.WalkingGUI.WalkingKeyListener;
import GUI.WalkingGUI.WalkingView;
import Game.GameStates;

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame {

    private final MainPanel mainPanel;

    public MainFrame(MenuView menuView, WalkingView walkingView, FightView fightView, EquipmentView equipmentView, QuestView questView, DialogView dialogView, TradeView tradeView) {
        this.setTitle("Dice and Dungeons");
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(new WalkingKeyListener());

        CenteredPanel centeredPanel = new CenteredPanel();

        mainPanel = new MainPanel(menuView, walkingView, fightView, equipmentView,questView, dialogView, tradeView);
        centeredPanel.add(mainPanel);
        this.add(centeredPanel);
    }

    public void changeState(GameStates state) {
        mainPanel.changeView(state);
    }

    public void refresh() {
        mainPanel.refresh();
    }

    public void setMinimumSize(){
        this.setMinimumSize(new Dimension(getWidth(), getHeight()));
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }
}
