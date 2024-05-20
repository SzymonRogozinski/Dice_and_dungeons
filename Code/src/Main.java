import Dice.DiceAction.*;
import Dice.DiceFactory;
import Fight.GameActions.*;
import Fight.ActionTarget;
import Fight.FightModule;
import Fight.Tags;
import GUI.GUIState;
import GUI.MainFrame;
import GUI.MainPanel;

import Character.PlayerCharacter;
import Character.PlayerParty;
import Character.Enemy.EnemyCharacter;
import Character.Enemy.EnemyAI;
import Character.Enemy.EnemyActionFactory;

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
        ActionItem item = new ActionItem("Sword", DiceFactory.buildDice(new int[][]{{0},{0},{0},{1,4},{1,4},{1,6}}), ActionTarget.ENEMY_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getStrength()),new Tags[]{Tags.ATTACK});
        ActionItem item2 = new ActionItem("Shield", DiceFactory.buildDice(new int[][]{{0},{0},{2,3,1},{2,3,1},{2,3,1},{2,3,1}}), ActionTarget.PLAYER_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getEndurance()),new Tags[]{Tags.DEFENCE});
        //ActionItem item3 = new ActionItem("Trap", DiceFactory.buildDice(new int[][]{{0},{0},{0},{6,1},{6,1},{6,1}}), ActionTarget.ENEMY_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getCunning()),new Tags[]{});
        ActionItem item3 = new ActionItem("Sword breaker", DiceFactory.buildDice(new int[][]{{0},{8,3,0},{8,3,0},{8,3,0},{8,3,0},{8,3,0}}), ActionTarget.PLAYER_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getCharisma()),new Tags[]{Tags.DEFENCE});


        SpellAction spell = new SpellAction("Heal", DiceFactory.buildDice(new int[][]{{0},{3,3,1},{3,3,1},{3,3,1},{3,3,1},{3,3,1}}), ActionTarget.PLAYER_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getIntelligence()),4,new Tags[]{Tags.MAGIC});

        ArrayList<ActionItem> items = new ArrayList<>(List.of(new ActionItem[]{item,item2,item3}));
        ArrayList<SpellAction> spells = new ArrayList<>(List.of(new SpellAction[]{spell}));
        PlayerCharacter player=new PlayerCharacter(24,12,12,12,12,12,"Warrior",new ImageIcon("CharacterTexture/player.png"),items,spells);

        item = new ActionItem("Mace", DiceFactory.buildDice(new int[][]{{0},{0},{0},{1,4},{1,4},{1,6}}), ActionTarget.ENEMY_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getStrength()),new Tags[]{Tags.ATTACK});
        item2 = new ActionItem("Magic sphere", DiceFactory.buildDice(new int[][]{{0},{0},{4,3},{4,3},{4,3},{4,3}}), ActionTarget.PLAYER_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getIntelligence()),new Tags[]{Tags.MAGIC});
        item3 = new ActionItem("Snake", DiceFactory.buildDice(new int[][]{{0},{0},{5,3},{5,3},{5,3},{5,3}}), ActionTarget.ENEMY_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getCunning()),new Tags[]{});
        spell = new SpellAction("Fire Vortex", DiceFactory.buildDice(new int[][]{{0},{1,4},{1,4},{1,4},{1,4},{1,4}}), ActionTarget.ALL_ENEMIES,(PlayerCharacter p)->p.getDiceNumber(p.getIntelligence()),8,new Tags[]{Tags.MAGIC});

        items = new ArrayList<>(List.of(new ActionItem[]{item,item2,item3}));
        spells = new ArrayList<>(List.of(new SpellAction[]{spell}));
        PlayerCharacter player2=new PlayerCharacter(12,18,12,12,12,12,"Bandit",new ImageIcon("CharacterTexture/player.png"),items,spells);

        UsableItemAction usItem1=new UsableItemAction("Rock",ActionTarget.ENEMY_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new StunAction()})),new Tags[]{Tags.NO_ROLL,Tags.FREE_ACTION});
        UsableItemAction usItem2=new UsableItemAction("Power up",ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new AttackBonusAction(3,true)})),new Tags[]{Tags.NO_ROLL,Tags.FREE_ACTION});
        UsableItemAction usItem3=new UsableItemAction("Magic powder",ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new MagicBonusAction(2,true)})),new Tags[]{Tags.NO_ROLL,Tags.FREE_ACTION});
        UsableItemAction usItem4=new UsableItemAction("Defense potion",ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new DefenseBonusAction(2,true)})),new Tags[]{Tags.NO_ROLL,Tags.FREE_ACTION});


        ArrayList<UsableItemAction> usableItems=new ArrayList<>(List.of(new UsableItemAction[]{usItem1,usItem2,usItem3,usItem4}));

        PlayerParty party = new PlayerParty(new ArrayList<>(List.of(new PlayerCharacter[]{player,player2})),usableItems);


        EnemyActionFactory factory1 = new EnemyActionFactory(e->e.getStrength(),0.25,false,1);
        EnemyActionFactory factory2 = new EnemyActionFactory(e->e.getCunning(),0.20,false,6);

        EnemyAction enemyAction1 = new EnemyAction(ActionTarget.PLAYER_CHARACTER,new Tags[]{Tags.ATTACK},new ArrayList<>(List.of(new EnemyActionFactory[]{factory1})));
        EnemyAction enemyAction2 = new EnemyAction(ActionTarget.PLAYER_CHARACTER,new Tags[]{},new ArrayList<>(List.of(new EnemyActionFactory[]{factory2})));
        EnemyAI ai1=new EnemyAI(new ArrayList<>(List.of(new EnemyAction[]{enemyAction1,enemyAction2})));
        EnemyAI ai2=new EnemyAI(new ArrayList<>(List.of(new EnemyAction[]{enemyAction1,enemyAction2})));

        EnemyCharacter enemy = new EnemyCharacter(12,12,12,12,12,12,"Skeleton",new ImageIcon("CharacterTexture/skeleton.png"),ai1);
        EnemyCharacter enemy2 = new EnemyCharacter(12,12,12,12,12,12,"Skeleton",new ImageIcon("CharacterTexture/skeleton.png"),ai2);

        FightModule fight = new FightModule(mainPanel,state,party,new ArrayList<>(List.of(new EnemyCharacter[]{enemy,enemy2})));
        fight.initFight();

        mainFrame.add(mainPanel);

        mainFrame.pack();
        mainFrame.setVisible(true);

    }
}
