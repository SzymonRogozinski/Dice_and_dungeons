import Character.PlayerCharacter;
import Character.PlayerParty;
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
import GUI.TestFrame;
import Game.GameManager;
import Game.Tags;
import Game.PlayerInfo;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class EquipmentTest {

    private static final JFrame mainFrame=new TestFrame();
    private static EquipmentView view;

    public static void main(String[] args) {
        //Test Data
        ActionItem item1 = new ActionItem(new ItemAction(DiceFactory.buildDice(new int[][]{{0},{0},{0},{1,4},{1,4},{1,6}}), ActionTarget.ENEMY_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getStrength()),new Tags[]{Tags.ATTACK}), new Tags[]{Tags.WARRIOR},new ImageIcon("Texture/Items/dice-pl.png"),"Sword","Sword",ItemQuality.COMMON);
        ActionItem item2 = new ActionItem(new ItemAction(DiceFactory.buildDice(new int[][]{{0},{2,3,0},{2,3,0},{2,3,0},{2,3,0},{2,3,0}}), ActionTarget.PLAYER_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getCharisma()),new Tags[]{Tags.DEFENCE}), new Tags[]{},new ImageIcon("Texture/Items/dice-pl.png"),"Shield","Shield",ItemQuality.RARE);
        ActionItem item3 = new ActionItem(new ItemAction(DiceFactory.buildDice(new int[][]{{0},{0},{0},{6,1},{6,1},{6,1}}), ActionTarget.ENEMY_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getCunning()), new Tags[]{}),new Tags[]{},new ImageIcon("Texture/Items/dice-pl.png"),"Trap","Trap",ItemQuality.COMMON);
        ActionItem item4 = new ActionItem(new ItemAction(DiceFactory.buildDice(new int[][]{{0},{8,3,0},{8,3,0},{8,3,0},{8,3,0},{8,3,0}}), ActionTarget.PLAYER_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getCharisma()),new Tags[]{Tags.DEFENCE}), new Tags[]{},new ImageIcon("Texture/Items/dice-pl.png"),"Sword breaker","Sword breaker",ItemQuality.LEGENDARY);
        ActionItem item5 = new ActionItem(new ItemAction(DiceFactory.buildDice(new int[][]{{0},{0},{0},{1,4},{1,4},{1,6}}), ActionTarget.ENEMY_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getStrength()),new Tags[]{Tags.ATTACK}), new Tags[]{},new ImageIcon("Texture/Items/dice-pl.png"),"Mace","Mace",ItemQuality.COMMON);
        ActionItem item6 = new ActionItem(new ItemAction(DiceFactory.buildDice(new int[][]{{0},{0},{4,3},{4,3},{4,3},{4,3}}), ActionTarget.PLAYER_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getIntelligence()),new Tags[]{Tags.MAGIC}), new Tags[]{},new ImageIcon("Texture/Items/dice-pl.png"),"Magic sphere","Magic sphere",ItemQuality.COMMON);
        ActionItem item7 = new ActionItem(new ItemAction(DiceFactory.buildDice(new int[][]{{0},{0},{5,3},{5,3},{5,3},{5,3}}), ActionTarget.ENEMY_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getCunning()),new Tags[]{}), new Tags[]{},new ImageIcon("Texture/Items/dice-pl.png"),"Snake","Snake",ItemQuality.COMMON);

        SpellItem spell1 = new SpellItem(new SpellAction(DiceFactory.buildDice(new int[][]{{0},{3,3,1},{3,3,1},{3,3,1},{3,3,1},{3,3,1}}), ActionTarget.PLAYER_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getIntelligence()),4,new Tags[]{Tags.MAGIC}), new Tags[]{},new ImageIcon("Texture/Items/scroll-pl.png"),"Heal","Heal",ItemQuality.COMMON);
        SpellItem spell2 = new SpellItem(new SpellAction(DiceFactory.buildDice(new int[][]{{0},{1,4},{1,4},{1,4},{1,4},{1,4}}), ActionTarget.ALL_ENEMIES,(PlayerCharacter p)->p.getDiceNumber(p.getIntelligence()),8,new Tags[]{Tags.MAGIC}), new Tags[]{},new ImageIcon("Texture/Items/scroll-pl.png"),"Fire Vortex","Fire Vortex",ItemQuality.COMMON);

        UsableItem usItem1 = new UsableItem(new UsableItemAction(ActionTarget.ENEMY_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new StunAction()})),new Tags[]{Tags.NO_ROLL,Tags.FREE_ACTION}),1,new Tags[]{},new ImageIcon("Texture/Items/bag-pl.png"),"Rock","Rock",ItemQuality.COMMON);
        UsableItem usItem2 = new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new AttackBonusAction(3,true)})),new Tags[]{Tags.NO_ROLL,Tags.FREE_ACTION}),3,new Tags[]{},new ImageIcon("Texture/Items/bag-pl.png"),"Power up","Power up",ItemQuality.COMMON);
        UsableItem usItem3 = new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new MagicBonusAction(2,true)})),new Tags[]{Tags.NO_ROLL,Tags.FREE_ACTION}),3,new Tags[]{},new ImageIcon("Texture/Items/bag-pl.png"),"Magic powder","Magic powder",ItemQuality.COMMON);
        UsableItem usItem4 = new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new DefenseBonusAction(2,true)})),new Tags[]{Tags.NO_ROLL,Tags.FREE_ACTION}),3,new Tags[]{},new ImageIcon("Texture/Items/bag-pl.png"),"Defense potion","Defense potion",ItemQuality.COMMON);
        UsableItem usItem5 = new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER,new ArrayList<>(List.of(new DiceAction[]{new HealAction(5,true)})),new Tags[]{Tags.NO_ROLL,Tags.FREE_ACTION}),3,new Tags[]{Tags.USABLE_OUT_OF_FIGHT},new ImageIcon("Texture/Items/bag-pl.png"),"Health potion","Health potion",ItemQuality.COMMON);

        ArmorItem arItem1=new ArmorItem(5,0,0,2,2,1,CharacterEquipment.HEAD_ARMOR,new Tags[]{Tags.WARRIOR},new ImageIcon("Texture/Items/helm-pl.png"),"Gold helmet","Gold helmet",ItemQuality.COMMON);
        ArmorItem arItem2=new ArmorItem(1,2,4,2,4,3,CharacterEquipment.CHEST_ARMOR,new Tags[]{Tags.THIEF},new ImageIcon("Texture/Items/chest-pl.png"),"Rogue cloak","Rogue cloak",ItemQuality.COMMON);

        PlayerCharacter player=new PlayerCharacter(24,12,12,12,12,12,"Warrior",new ImageIcon("CharacterTexture/player.png"),new Tags[]{Tags.WARRIOR});
        PlayerCharacter player2=new PlayerCharacter(12,18,12,12,12,12,"Bandit",new ImageIcon("CharacterTexture/player.png"),new Tags[]{Tags.THIEF});

        ArrayList<Item> usableItems=new ArrayList<>(List.of(new Item[]{usItem1,usItem2,usItem3,usItem4,usItem5}));

        PlayerParty party = new PlayerParty(new ArrayList<>(List.of(new PlayerCharacter[]{player,player2})),usableItems);
        PlayerInfo.setParty(party);

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
        party.getBackpack().putToBackpack(item1);
        party.getBackpack().putToBackpack(item2);
        party.getBackpack().putToBackpack(item3);
        party.getBackpack().putToBackpack(arItem2);
        party.getBackpack().putToBackpack(item5);
        party.getBackpack().putToBackpack(item6);
        party.getBackpack().putToBackpack(item1);
        party.getBackpack().putToBackpack(arItem2);
        party.getBackpack().putToBackpack(item2);
        party.getBackpack().putToBackpack(item3);
        party.getBackpack().putToBackpack(arItem2);
        party.getBackpack().putToBackpack(item5);
        party.getBackpack().putToBackpack(item6);
        party.getBackpack().putToBackpack(item1);
        party.getBackpack().putToBackpack(arItem2);
        party.getBackpack().putToBackpack(item2);
        party.getBackpack().putToBackpack(item3);
        party.getBackpack().putToBackpack(item5);
        party.getBackpack().putToBackpack(arItem2);
        party.getBackpack().putToBackpack(item6);
        party.getBackpack().putToBackpack(item1);
        party.getBackpack().putToBackpack(arItem2);
        party.getBackpack().putToBackpack(item2);
        party.getBackpack().putToBackpack(item3);
        party.getBackpack().putToBackpack(item5);

        party.getBackpack().putToBackpack(item6);
        party.getBackpack().putToBackpack(item1);
        party.getBackpack().putToBackpack(item2);
        party.getBackpack().putToBackpack(arItem2);
        party.getBackpack().putToBackpack(item3);
        party.getBackpack().putToBackpack(item5);
        party.getBackpack().putToBackpack(arItem2);
        party.getBackpack().putToBackpack(item6);
        party.getBackpack().putToBackpack(item1);
        party.getBackpack().putToBackpack(item2);
        party.getBackpack().putToBackpack(arItem2);
        party.getBackpack().putToBackpack(item3);
        party.getBackpack().putToBackpack(item5);
        party.getBackpack().putToBackpack(item6);
        party.getBackpack().putToBackpack(arItem2);
        party.getBackpack().putToBackpack(item1);
        party.getBackpack().putToBackpack(item2);
        party.getBackpack().putToBackpack(arItem2);
        party.getBackpack().putToBackpack(item3);
        party.getBackpack().putToBackpack(item5);
        party.getBackpack().putToBackpack(item6);
        party.getBackpack().putToBackpack(arItem2);
        party.getBackpack().putToBackpack(item1);
        party.getBackpack().putToBackpack(item2);
        party.getBackpack().putToBackpack(item3);
        party.getBackpack().putToBackpack(arItem2);
        party.getBackpack().putToBackpack(item5);
        party.getBackpack().putToBackpack(item6);

        party.getBackpack().putToBackpack(item1);
        party.getBackpack().putToBackpack(item2);
        party.getBackpack().putToBackpack(item3);
        party.getBackpack().putToBackpack(item5);
        party.getBackpack().putToBackpack(item6);
        party.getBackpack().putToBackpack(item1);
        party.getBackpack().putToBackpack(item2);
        party.getBackpack().putToBackpack(item3);
        party.getBackpack().putToBackpack(item5);
        party.getBackpack().putToBackpack(item6);
        party.getBackpack().putToBackpack(item1);
        party.getBackpack().putToBackpack(item2);
        party.getBackpack().putToBackpack(item3);
        party.getBackpack().putToBackpack(item5);
        party.getBackpack().putToBackpack(item6);
        party.getBackpack().putToBackpack(item1);
        party.getBackpack().putToBackpack(item2);
        party.getBackpack().putToBackpack(item3);
        party.getBackpack().putToBackpack(item5);
        party.getBackpack().putToBackpack(item6);
        //Code

        view = new EquipmentView();
        EquipmentGUIState state=new EquipmentGUIState(view);
        EquipmentModule module = new EquipmentModule(state);
        FightModule fModule = new FightModule(null,null);

        GameManager.setEquipment(module);
        GameManager.setFight(fModule);
        state.refresh();

        mainFrame.add(view);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}
