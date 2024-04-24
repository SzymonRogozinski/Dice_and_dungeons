package main;

import Dice.DiceFactory;
import Fight.ActionItem;
import GUI.GUIState;
import GUI.MainFrame;
import GUI.MainPanel;

import Character.PlayerCharacter;
import Character.PlayerParty;
import Character.EnemyCharacter;

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
        PlayerCharacter player=new PlayerCharacter(24,12,12,12,12,12,"Warrior",new ImageIcon("CharacterTexture/player.png"),items);

        item = new ActionItem("Mace", DiceFactory.buildDice(new int[][]{{0},{0},{0},{1,4},{1,4},{1,6}}));
        items = new ArrayList<>(List.of(new ActionItem[]{item}));
        PlayerCharacter player2=new PlayerCharacter(12,18,12,12,12,12,"Bandit",new ImageIcon("CharacterTexture/player.png"),items);

        PlayerParty party = new PlayerParty(new ArrayList<>(List.of(new PlayerCharacter[]{player,player2})));

        EnemyCharacter enemy = new EnemyCharacter(12,12,12,12,12,12,"Skeleton",new ImageIcon("CharacterTexture/skeleton.png"));
        EnemyCharacter enemy2 = new EnemyCharacter(12,12,12,12,12,12,"Skeleton",new ImageIcon("CharacterTexture/skeleton.png"));

        FightModule fight = new FightModule(mainPanel,state,party,new ArrayList<>(List.of(new EnemyCharacter[]{enemy,enemy2})));
        fight.initFight();

        mainFrame.add(mainPanel);

        mainFrame.pack();
        mainFrame.setVisible(true);

    }
}
