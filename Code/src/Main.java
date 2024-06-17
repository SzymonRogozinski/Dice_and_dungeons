import Character.Enemy.EnemyAI;
import Character.Enemy.EnemyActionFactory;
import Character.Enemy.EnemyCharacter;
import Character.PlayerCharacter;
import Character.PlayerParty;
import Dice.DiceAction.*;
import Dice.DiceFactory;
import Equipment.CharacterEquipment;
import Equipment.Items.ActionItem;
import Equipment.Items.Item;
import Equipment.Items.SpellItem;
import Equipment.Items.UsableItem;
import Fight.ActionTarget;
import Fight.FightModule;
import Fight.GameActions.EnemyAction;
import Fight.GameActions.ItemAction;
import Fight.GameActions.SpellAction;
import Fight.GameActions.UsableItemAction;
import GUI.GUIState;
import GUI.MainFrame;
import GUI.FightGUI.FightView;
import Game.Tags;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final JFrame mainFrame=new MainFrame();
    private static FightView fightView;
    private static GUIState state;

    public static void main(String[] args) {

        fightView =new FightView();
        state=new GUIState(fightView);
        //ItemAction item = new ItemAction("Sword", DiceFactory.buildDice(new int[][]{{0},{0},{0},{1,4},{1,4},{1,6}}), ActionTarget.ENEMY_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getStrength()),new Tags[]{Tags.ATTACK});
        //ItemAction item2 = new ItemAction("Shield", DiceFactory.buildDice(new int[][]{{0},{0},{2,3,1},{2,3,1},{2,3,1},{2,3,1}}), ActionTarget.PLAYER_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getEndurance()),new Tags[]{Tags.DEFENCE});
        //ActionItem item3 = new ActionItem("Trap", DiceFactory.buildDice(new int[][]{{0},{0},{0},{6,1},{6,1},{6,1}}), ActionTarget.ENEMY_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getCunning()),new Tags[]{});
        //ItemAction item3 = new ItemAction("Sword breaker", DiceFactory.buildDice(new int[][]{{0},{8,3,0},{8,3,0},{8,3,0},{8,3,0},{8,3,0}}), ActionTarget.PLAYER_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getCharisma()),new Tags[]{Tags.DEFENCE});
        //item = new ItemAction("Mace", DiceFactory.buildDice(new int[][]{{0},{0},{0},{1,4},{1,4},{1,6}}), ActionTarget.ENEMY_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getStrength()),new Tags[]{Tags.ATTACK});
        //item2 = new ItemAction("Magic sphere", DiceFactory.buildDice(new int[][]{{0},{0},{4,3},{4,3},{4,3},{4,3}}), ActionTarget.PLAYER_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getIntelligence()),new Tags[]{Tags.MAGIC});
        //item3 = new ItemAction("Snake", DiceFactory.buildDice(new int[][]{{0},{0},{5,3},{5,3},{5,3},{5,3}}), ActionTarget.ENEMY_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getCunning()),new Tags[]{});

        //SpellAction spell = new SpellAction("Heal", DiceFactory.buildDice(new int[][]{{0},{3,3,1},{3,3,1},{3,3,1},{3,3,1},{3,3,1}}), ActionTarget.PLAYER_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getIntelligence()),4,new Tags[]{Tags.MAGIC});
        //spell = new SpellAction("Fire Vortex", DiceFactory.buildDice(new int[][]{{0},{1,4},{1,4},{1,4},{1,4},{1,4}}), ActionTarget.ALL_ENEMIES,(PlayerCharacter p)->p.getDiceNumber(p.getIntelligence()),8,new Tags[]{Tags.MAGIC});

        //UsableItemAction usItem1=new UsableItemAction("Rock",ActionTarget.ENEMY_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new StunAction()})),new Tags[]{Tags.NO_ROLL,Tags.FREE_ACTION});
        //UsableItemAction usItem2=new UsableItemAction("Power up",ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new AttackBonusAction(3,true)})),new Tags[]{Tags.NO_ROLL,Tags.FREE_ACTION});
        //UsableItemAction usItem3=new UsableItemAction("Magic powder",ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new MagicBonusAction(2,true)})),new Tags[]{Tags.NO_ROLL,Tags.FREE_ACTION});
        //UsableItemAction usItem4=new UsableItemAction("Defense potion",ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new DefenseBonusAction(2,true)})),new Tags[]{Tags.NO_ROLL,Tags.FREE_ACTION});

        //ArrayList<ItemAction> items = new ArrayList<>(List.of(new ItemAction[]{item,item2,item3}));
        //ArrayList<SpellAction> spells = new ArrayList<>(List.of(new SpellAction[]{spell}));

        //items = new ArrayList<>(List.of(new ItemAction[]{item,item2,item3}));
        //spells = new ArrayList<>(List.of(new SpellAction[]{spell}));

        ActionItem item1 = new ActionItem(new ItemAction(DiceFactory.buildDice(new int[][]{{0},{0},{0},{1,4},{1,4},{1,6}}), ActionTarget.ENEMY_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getStrength()),new Tags[]{Tags.ATTACK}), new Tags[]{},null,"Sword");
        ActionItem item2 = new ActionItem(new ItemAction(DiceFactory.buildDice(new int[][]{{0},{8,3,0},{8,3,0},{8,3,0},{8,3,0},{8,3,0}}), ActionTarget.PLAYER_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getCharisma()),new Tags[]{Tags.DEFENCE}), new Tags[]{},null,"Shield");
        ActionItem item3 = new ActionItem(new ItemAction(DiceFactory.buildDice(new int[][]{{0},{0},{0},{6,1},{6,1},{6,1}}), ActionTarget.ENEMY_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getCunning()), new Tags[]{}),new Tags[]{},null,"Trap");
        ActionItem item4 = new ActionItem(new ItemAction(DiceFactory.buildDice(new int[][]{{0},{8,3,0},{8,3,0},{8,3,0},{8,3,0},{8,3,0}}), ActionTarget.PLAYER_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getCharisma()),new Tags[]{Tags.DEFENCE}), new Tags[]{},null,"Sword breaker");
        ActionItem item5 = new ActionItem(new ItemAction(DiceFactory.buildDice(new int[][]{{0},{0},{0},{1,4},{1,4},{1,6}}), ActionTarget.ENEMY_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getStrength()),new Tags[]{Tags.ATTACK}), new Tags[]{},null,"Mace");
        ActionItem item6 = new ActionItem(new ItemAction(DiceFactory.buildDice(new int[][]{{0},{0},{4,3},{4,3},{4,3},{4,3}}), ActionTarget.PLAYER_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getIntelligence()),new Tags[]{Tags.MAGIC}), new Tags[]{},null,"Magic sphere");
        ActionItem item7 = new ActionItem(new ItemAction(DiceFactory.buildDice(new int[][]{{0},{0},{5,3},{5,3},{5,3},{5,3}}), ActionTarget.ENEMY_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getCunning()),new Tags[]{}), new Tags[]{},null,"Snake");

        SpellItem spell1 = new SpellItem(new SpellAction(DiceFactory.buildDice(new int[][]{{0},{3,3,1},{3,3,1},{3,3,1},{3,3,1},{3,3,1}}), ActionTarget.PLAYER_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getIntelligence()),4,new Tags[]{Tags.MAGIC}), new Tags[]{},null,"Heal");
        SpellItem spell2 = new SpellItem(new SpellAction(DiceFactory.buildDice(new int[][]{{0},{1,4},{1,4},{1,4},{1,4},{1,4}}), ActionTarget.ALL_ENEMIES,(PlayerCharacter p)->p.getDiceNumber(p.getIntelligence()),8,new Tags[]{Tags.MAGIC}), new Tags[]{},null,"Fire Vortex");

        UsableItem usItem1 = new UsableItem(new UsableItemAction(ActionTarget.ENEMY_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new StunAction()})),new Tags[]{Tags.NO_ROLL,Tags.FREE_ACTION}),1,new Tags[]{},null,"Rock");
        UsableItem usItem2 = new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new AttackBonusAction(3,true)})),new Tags[]{Tags.NO_ROLL,Tags.FREE_ACTION}),3,new Tags[]{},null,"Power up");
        UsableItem usItem3 = new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new DefenseBonusAction(2,true)})),new Tags[]{Tags.NO_ROLL,Tags.FREE_ACTION}),3,new Tags[]{},null,"Magic powder");
        UsableItem usItem4 = new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new DefenseBonusAction(2,true)})),new Tags[]{Tags.NO_ROLL,Tags.FREE_ACTION}),3,new Tags[]{},null,"Defense potion");

        PlayerCharacter player=new PlayerCharacter(24,12,12,12,12,12,"Warrior",new ImageIcon("CharacterTexture/player.png"));

        PlayerCharacter player2=new PlayerCharacter(12,18,12,12,12,12,"Bandit",new ImageIcon("CharacterTexture/player.png"));

        //Set eq
        player.getEquipment().equip(item1,0, CharacterEquipment.ACTION_SLOT);
        player.getEquipment().equip(item2,1, CharacterEquipment.ACTION_SLOT);
        player.getEquipment().equip(item3,2, CharacterEquipment.ACTION_SLOT);
        player.getEquipment().equip(spell1,0, CharacterEquipment.SPELL_SLOT);

        player2.getEquipment().equip(item5,0, CharacterEquipment.ACTION_SLOT);
        player2.getEquipment().equip(item6,1, CharacterEquipment.ACTION_SLOT);
        player2.getEquipment().equip(item7,2, CharacterEquipment.ACTION_SLOT);
        player2.getEquipment().equip(spell2,0, CharacterEquipment.SPELL_SLOT);

        ArrayList<Item> usableItems=new ArrayList<>(List.of(new Item[]{usItem1,usItem2,usItem3,usItem4}));

        PlayerParty party = new PlayerParty(new ArrayList<>(List.of(new PlayerCharacter[]{player,player2})),usableItems);

        EnemyActionFactory factory1 = new EnemyActionFactory(e->e.getStrength(),0.25,false,1);
        EnemyActionFactory factory2 = new EnemyActionFactory(e->e.getCunning(),0.20,false,6);

        EnemyAction enemyAction1 = new EnemyAction(ActionTarget.PLAYER_CHARACTER,new Tags[]{Tags.ATTACK},new ArrayList<>(List.of(new EnemyActionFactory[]{factory1})));
        EnemyAction enemyAction2 = new EnemyAction(ActionTarget.PLAYER_CHARACTER,new Tags[]{},new ArrayList<>(List.of(new EnemyActionFactory[]{factory2})));
        EnemyAI ai1=new EnemyAI(new ArrayList<>(List.of(new EnemyAction[]{enemyAction1,enemyAction2})));
        EnemyAI ai2=new EnemyAI(new ArrayList<>(List.of(new EnemyAction[]{enemyAction1,enemyAction2})));

        EnemyCharacter enemy = new EnemyCharacter(12,12,12,12,12,12,"Skeleton1",new ImageIcon("CharacterTexture/skeleton.png"),ai1);
        EnemyCharacter enemy2 = new EnemyCharacter(12,12,12,12,12,12,"Skeleton2",new ImageIcon("CharacterTexture/skeleton.png"),ai2);

        FightModule fight = new FightModule(fightView,state,party,new ArrayList<>(List.of(new EnemyCharacter[]{enemy,enemy2})));
        fight.initFight();

        mainFrame.add(fightView);

        mainFrame.pack();
        mainFrame.setVisible(true);

    }
}
