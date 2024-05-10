import Dice.DiceFactory;
import Fight.GameActions.ActionItem;
import Fight.ActionTarget;
import Fight.FightModule;
import Fight.GameActions.SpellAction;
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
        ActionItem item = new ActionItem("Sword", DiceFactory.buildDice(new int[][]{{0},{0},{0},{1,4},{1,4},{1,6}}), ActionTarget.ENEMY_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getStrength()));
        ActionItem item2 = new ActionItem("Shield", DiceFactory.buildDice(new int[][]{{0},{0},{2,3},{2,3},{2,3},{2,3}}), ActionTarget.PLAYER_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getEndurance()));
        SpellAction spell = new SpellAction("Heal", DiceFactory.buildDice(new int[][]{{0},{3,3},{3,3},{3,3},{3,3},{3,3}}), ActionTarget.PLAYER_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getIntelligence()),4);

        ArrayList<ActionItem> items = new ArrayList<>(List.of(new ActionItem[]{item,item2}));
        ArrayList<SpellAction> spells = new ArrayList<>(List.of(new SpellAction[]{spell}));
        PlayerCharacter player=new PlayerCharacter(24,12,12,12,12,12,"Warrior",new ImageIcon("CharacterTexture/player.png"),items,spells);

        item = new ActionItem("Mace", DiceFactory.buildDice(new int[][]{{0},{0},{0},{1,4},{1,4},{1,6}}), ActionTarget.ENEMY_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getStrength()));
        item2 = new ActionItem("Magic sphere", DiceFactory.buildDice(new int[][]{{0},{0},{4,3},{4,3},{4,3},{4,3}}), ActionTarget.PLAYER_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getIntelligence()));
        spell = new SpellAction("Fire Vortex", DiceFactory.buildDice(new int[][]{{0},{1,4},{1,4},{1,4},{1,4},{1,4}}), ActionTarget.ALL_ENEMIES,(PlayerCharacter p)->p.getDiceNumber(p.getIntelligence()),8);

        items = new ArrayList<>(List.of(new ActionItem[]{item,item2}));
        spells = new ArrayList<>(List.of(new SpellAction[]{spell}));
        PlayerCharacter player2=new PlayerCharacter(12,18,12,12,12,12,"Bandit",new ImageIcon("CharacterTexture/player.png"),items,spells);

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
