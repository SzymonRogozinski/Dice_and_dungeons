import Dice.DiceAction.*;
import Dice.DiceFactory;
import Equipment.CharacterEquipment;
import Equipment.EquipmentModule;
import Equipment.Items.*;
import Fight.ActionTarget;
import Fight.FightModule;
import Fight.GameActions.ItemAction;
import Fight.GameActions.SpellAction;
import Fight.GameActions.UsableItemAction;
import GUI.EquipmentGUI.EquipmentGUIState;
import GUI.EquipmentGUI.EquipmentView;
import GUI.MainFrame;
import Character.*;
import Game.GameCollection;
import Game.Tags;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Test {

    private static final JFrame mainFrame=new MainFrame();
    private static EquipmentView view;

    public static void main(String[] args) {
        //Test Data
        ActionItem item1 = new ActionItem(new ItemAction(DiceFactory.buildDice(new int[][]{{0},{0},{0},{1,4},{1,4},{1,6}}), ActionTarget.ENEMY_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getStrength()),new Tags[]{Tags.ATTACK}), new Tags[]{},new ImageIcon("ItemsIcons/dice-pl.png"),"Sword");
        ActionItem item2 = new ActionItem(new ItemAction(DiceFactory.buildDice(new int[][]{{0},{2,3,0},{2,3,0},{2,3,0},{2,3,0},{2,3,0}}), ActionTarget.PLAYER_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getCharisma()),new Tags[]{Tags.DEFENCE}), new Tags[]{},new ImageIcon("ItemsIcons/dice-pl.png"),"Shield");
        ActionItem item3 = new ActionItem(new ItemAction(DiceFactory.buildDice(new int[][]{{0},{0},{0},{6,1},{6,1},{6,1}}), ActionTarget.ENEMY_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getCunning()), new Tags[]{}),new Tags[]{},new ImageIcon("ItemsIcons/dice-pl.png"),"Trap");
        ActionItem item4 = new ActionItem(new ItemAction(DiceFactory.buildDice(new int[][]{{0},{8,3,0},{8,3,0},{8,3,0},{8,3,0},{8,3,0}}), ActionTarget.PLAYER_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getCharisma()),new Tags[]{Tags.DEFENCE}), new Tags[]{},new ImageIcon("ItemsIcons/dice-pl.png"),"Sword breaker");
        ActionItem item5 = new ActionItem(new ItemAction(DiceFactory.buildDice(new int[][]{{0},{0},{0},{1,4},{1,4},{1,6}}), ActionTarget.ENEMY_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getStrength()),new Tags[]{Tags.ATTACK}), new Tags[]{},new ImageIcon("ItemsIcons/dice-pl.png"),"Mace");
        ActionItem item6 = new ActionItem(new ItemAction(DiceFactory.buildDice(new int[][]{{0},{0},{4,3},{4,3},{4,3},{4,3}}), ActionTarget.PLAYER_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getIntelligence()),new Tags[]{Tags.MAGIC}), new Tags[]{},new ImageIcon("ItemsIcons/dice-pl.png"),"Magic sphere");
        ActionItem item7 = new ActionItem(new ItemAction(DiceFactory.buildDice(new int[][]{{0},{0},{5,3},{5,3},{5,3},{5,3}}), ActionTarget.ENEMY_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getCunning()),new Tags[]{}), new Tags[]{},new ImageIcon("ItemsIcons/dice-pl.png"),"Snake");

        SpellItem spell1 = new SpellItem(new SpellAction(DiceFactory.buildDice(new int[][]{{0},{3,3,1},{3,3,1},{3,3,1},{3,3,1},{3,3,1}}), ActionTarget.PLAYER_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getIntelligence()),4,new Tags[]{Tags.MAGIC}), new Tags[]{},new ImageIcon("ItemsIcons/scroll-pl.png"),"Heal");
        SpellItem spell2 = new SpellItem(new SpellAction(DiceFactory.buildDice(new int[][]{{0},{1,4},{1,4},{1,4},{1,4},{1,4}}), ActionTarget.ALL_ENEMIES,(PlayerCharacter p)->p.getDiceNumber(p.getIntelligence()),8,new Tags[]{Tags.MAGIC}), new Tags[]{},new ImageIcon("ItemsIcons/scroll-pl.png"),"Fire Vortex");

        UsableItem usItem1 = new UsableItem(new UsableItemAction(ActionTarget.ENEMY_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new StunAction()})),new Tags[]{Tags.NO_ROLL,Tags.FREE_ACTION}),1,new Tags[]{},new ImageIcon("ItemsIcons/bag-pl.png"),"Rock");
        UsableItem usItem2 = new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new AttackBonusAction(3,true)})),new Tags[]{Tags.NO_ROLL,Tags.FREE_ACTION}),3,new Tags[]{},new ImageIcon("ItemsIcons/bag-pl.png"),"Power up");
        UsableItem usItem3 = new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new MagicBonusAction(2,true)})),new Tags[]{Tags.NO_ROLL,Tags.FREE_ACTION}),3,new Tags[]{},new ImageIcon("ItemsIcons/bag-pl.png"),"Magic powder");
        UsableItem usItem4 = new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new DefenseBonusAction(2,true)})),new Tags[]{Tags.NO_ROLL,Tags.FREE_ACTION}),3,new Tags[]{},new ImageIcon("ItemsIcons/bag-pl.png"),"Defense potion");
        UsableItem usItem5 = new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new HealAction(5,true)})),new Tags[]{Tags.NO_ROLL,Tags.FREE_ACTION}),3,new Tags[]{Tags.USABLE_OUT_OF_FIGHT},new ImageIcon("ItemsIcons/bag-pl.png"),"Health potion");

        ArmorItem arItem1=new ArmorItem(5,0,0,2,2,1,CharacterEquipment.HEAD_ARMOR,new Tags[]{Tags.WARRIOR},new ImageIcon("ItemsIcons/helm-pl.png"),"Gold helmet");
        ArmorItem arItem2=new ArmorItem(1,2,4,2,4,3,CharacterEquipment.CHEST_ARMOR,new Tags[]{Tags.THIEF},new ImageIcon("ItemsIcons/chest-pl.png"),"Rogue cloak");

        PlayerCharacter player=new PlayerCharacter(24,12,12,12,12,12,"Warrior",new ImageIcon("CharacterTexture/player.png"),new Tags[]{Tags.WARRIOR});
        PlayerCharacter player2=new PlayerCharacter(12,18,12,12,12,12,"Bandit",new ImageIcon("CharacterTexture/player.png"),new Tags[]{Tags.THIEF});

        ArrayList<Item> usableItems=new ArrayList<>(List.of(new Item[]{usItem1,usItem2,usItem3,usItem4,usItem5}));

        PlayerParty party = new PlayerParty(new ArrayList<>(List.of(new PlayerCharacter[]{player,player2})),usableItems);

        //Set eq
        player.getEquipment().equip(item1,0, CharacterEquipment.ACTION_SLOT);
        player.getEquipment().equip(item2,1, CharacterEquipment.ACTION_SLOT);
        player.getEquipment().equip(item3,2, CharacterEquipment.ACTION_SLOT);
        player.getEquipment().equip(spell1,0, CharacterEquipment.SPELL_SLOT);
        player.getEquipment().equip(arItem1,0,CharacterEquipment.ARMOR_SLOT);

        player2.getEquipment().equip(item5,0, CharacterEquipment.ACTION_SLOT);
        player2.getEquipment().equip(item6,1, CharacterEquipment.ACTION_SLOT);
        player2.getEquipment().equip(spell2,0, CharacterEquipment.SPELL_SLOT);

        //Set backpack
        party.getBackpack().putToBackpack(item7);
        party.getBackpack().putToBackpack(item4);
        party.getBackpack().putToBackpack(arItem2);
        //Code

        view = new EquipmentView();
        EquipmentGUIState state=new EquipmentGUIState(view);
        EquipmentModule module = new EquipmentModule(state,party);
        FightModule fModule = new FightModule(null,party,null);

        GameCollection.setEquipment(module);
        GameCollection.setFight(fModule);
        state.refresh();

        mainFrame.add(view);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}
