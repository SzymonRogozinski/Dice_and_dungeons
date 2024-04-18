package main;

import Dice.DiceFactory;
import Fight.ActionItem;
import GUI.GUIState;
import GUI.MainFrame;
import GUI.MainPanel;

import Character.PlayerCharacter;
import Character.PlayerParty;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final JFrame mainFrame=new MainFrame();
    private static MainPanel mainPanel;
    private static GUIState state;

    public static void main(String[] args) {

        mainPanel =new MainPanel();
        state=new GUIState(mainPanel);
        ActionItem item = new ActionItem("Sword", DiceFactory.buildDice(new int[][]{{0},{0},{0},{1,4},{1,4},{1,6}}));
        ArrayList<ActionItem> items = new ArrayList<>();
        items.add(item);
        PlayerCharacter player=new PlayerCharacter(24,12,12,12,12,12,items);
        PlayerParty party = new PlayerParty(new ArrayList<>(List.of(new PlayerCharacter[]{player})));

        FightModule fight = new FightModule(mainPanel,state,party);
        fight.initFight();

        mainFrame.add(mainPanel);

        mainFrame.pack();
        mainFrame.setVisible(true);

    }
}
