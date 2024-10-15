package Tests;

import Character.Enemy.EnemyAI;
import Character.Enemy.EnemyActionFactory;
import Character.Enemy.EnemyCategory;
import Character.Enemy.EnemyCharacter;
import Character.PlayerCharacter;
import Character.PlayerParty;
import Dice.DiceAction.*;
import Dice.DiceFactory;
import Equipment.CharacterEquipment;
import Equipment.Items.*;
import Fight.ActionTarget;
import Fight.FightModule;
import Fight.GameActions.EnemyAction;
import Fight.GameActions.ItemAction;
import Fight.GameActions.SpellAction;
import Fight.GameActions.UsableItemAction;
import GUI.FightGUI.FightGUIState;
import GUI.FightGUI.FightView;
import Game.GameManager;
import Game.PlayerInfo;
import Game.Tags;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class FightingTest {
    private static final JFrame mainFrame = new TestFrame();

    public static void main(String[] args) {

        FightView fightView = new FightView();
        FightGUIState state = new FightGUIState(fightView);

        ActionItem item1 = new ActionItem(new ItemAction(DiceFactory.buildDice(new int[][]{{0}, {0}, {0}, {1, 4}, {1, 4}, {1, 6}}), ActionTarget.ENEMY_CHARACTER, (PlayerCharacter p) -> p.getDiceNumber(p.getStrength()), new Tags[]{Tags.ATTACK}), new Tags[]{}, null, "Sword", "Sword", ItemQuality.COMMON, "");
        ActionItem item2 = new ActionItem(new ItemAction(DiceFactory.buildDice(new int[][]{{0}, {2, 3, 0}, {2, 3, 0}, {2, 3, 0}, {2, 3, 0}, {2, 3, 0}}), ActionTarget.PLAYER_CHARACTER, (PlayerCharacter p) -> p.getDiceNumber(p.getCharisma()), new Tags[]{Tags.DEFENCE}), new Tags[]{}, null, "Shield", "Shield", ItemQuality.COMMON, "");
        ActionItem item3 = new ActionItem(new ItemAction(DiceFactory.buildDice(new int[][]{{0}, {0}, {0}, {6, 1}, {6, 1}, {6, 1}}), ActionTarget.ENEMY_CHARACTER, (PlayerCharacter p) -> p.getDiceNumber(p.getCunning()), new Tags[]{}), new Tags[]{}, null, "Trap", "Trap", ItemQuality.COMMON, "");
        //ActionItem item4 = new ActionItem(new ItemAction(DiceFactory.buildDice(new int[][]{{0},{8,3,0},{8,3,0},{8,3,0},{8,3,0},{8,3,0}}), ActionTarget.PLAYER_CHARACTER,(PlayerCharacter p)->p.getDiceNumber(p.getCharisma()),new Tags[]{Tags.DEFENCE}), new Tags[]{},null,"Sword breaker","Sword breaker",ItemQuality.COMMON,"");
        ActionItem item5 = new ActionItem(new ItemAction(DiceFactory.buildDice(new int[][]{{0}, {0}, {0}, {1, 4}, {1, 4}, {1, 6}}), ActionTarget.ENEMY_CHARACTER, (PlayerCharacter p) -> p.getDiceNumber(p.getStrength()), new Tags[]{Tags.ATTACK}), new Tags[]{}, null, "Mace", "Mace", ItemQuality.COMMON, "");
        ActionItem item6 = new ActionItem(new ItemAction(DiceFactory.buildDice(new int[][]{{0}, {0}, {4, 3}, {4, 3}, {4, 3}, {4, 3}}), ActionTarget.PLAYER_CHARACTER, (PlayerCharacter p) -> p.getDiceNumber(p.getIntelligence()), new Tags[]{Tags.MAGIC}), new Tags[]{}, null, "Magic sphere", "Magic sphere", ItemQuality.COMMON, "");
        ActionItem item7 = new ActionItem(new ItemAction(DiceFactory.buildDice(new int[][]{{0}, {0}, {5, 3}, {5, 3}, {5, 3}, {5, 3}}), ActionTarget.ENEMY_CHARACTER, (PlayerCharacter p) -> p.getDiceNumber(p.getCunning()), new Tags[]{}), new Tags[]{}, null, "Snake", "Snake", ItemQuality.COMMON, "");

        SpellItem spell1 = new SpellItem(new SpellAction(DiceFactory.buildDice(new int[][]{{0}, {3, 3, 1}, {3, 3, 1}, {3, 3, 1}, {3, 3, 1}, {3, 3, 1}}), ActionTarget.PLAYER_CHARACTER, (PlayerCharacter p) -> p.getDiceNumber(p.getIntelligence()), 4, new Tags[]{Tags.MAGIC}), new Tags[]{}, null, "Heal", "Heal", ItemQuality.COMMON);
        SpellItem spell2 = new SpellItem(new SpellAction(DiceFactory.buildDice(new int[][]{{0}, {1, 4}, {1, 4}, {1, 4}, {1, 4}, {1, 4}}), ActionTarget.ALL_ENEMIES, (PlayerCharacter p) -> p.getDiceNumber(p.getIntelligence()), 8, new Tags[]{Tags.MAGIC}), new Tags[]{}, null, "Fire Vortex", "Fire Vortex", ItemQuality.COMMON);

        UsableItem usItem1 = new UsableItem(new UsableItemAction(ActionTarget.ENEMY_CHARACTER, new ArrayList<>(List.of(new DiceAction[]{new StunAction()})), new Tags[]{Tags.NO_ROLL, Tags.FREE_ACTION}), 1, new Tags[]{}, null, "Rock", "Rock", ItemQuality.COMMON);
        UsableItem usItem2 = new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER, new ArrayList<>(List.of(new DiceAction[]{new AttackBonusAction(3, true)})), new Tags[]{Tags.NO_ROLL, Tags.FREE_ACTION}), 3, new Tags[]{}, null, "Power up", "Power up", ItemQuality.COMMON);
        UsableItem usItem3 = new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER, new ArrayList<>(List.of(new DiceAction[]{new DefenseBonusAction(2, true)})), new Tags[]{Tags.NO_ROLL, Tags.FREE_ACTION}), 3, new Tags[]{}, null, "Magic powder", "Magic powder", ItemQuality.COMMON);
        UsableItem usItem4 = new UsableItem(new UsableItemAction(ActionTarget.PLAYER_CHARACTER, new ArrayList<>(List.of(new DiceAction[]{new MagicBonusAction(2, true)})), new Tags[]{Tags.NO_ROLL, Tags.FREE_ACTION}), 3, new Tags[]{}, null, "Defense potion", "Defense potion", ItemQuality.COMMON);

        PlayerCharacter player = new PlayerCharacter(24, 12, 12, 12, 12, 12, "Warrior", new ImageIcon("Texture/CharacterTexture/player.png"), new Tags[]{Tags.WARRIOR});
        PlayerCharacter player2 = new PlayerCharacter(12, 18, 12, 12, 12, 12, "Bandit", new ImageIcon("Texture/CharacterTexture/player.png"), new Tags[]{Tags.THIEF});

        //Set eq
        player.getEquipment().equip(item1, 0, CharacterEquipment.ACTION_SLOT);
        player.getEquipment().equip(item2, 1, CharacterEquipment.ACTION_SLOT);
        player.getEquipment().equip(item3, 2, CharacterEquipment.ACTION_SLOT);
        player.getEquipment().equip(spell1, 0, CharacterEquipment.SPELL_SLOT);

        player2.getEquipment().equip(item5, 0, CharacterEquipment.ACTION_SLOT);
        player2.getEquipment().equip(item6, 1, CharacterEquipment.ACTION_SLOT);
        player2.getEquipment().equip(item7, 2, CharacterEquipment.ACTION_SLOT);
        player2.getEquipment().equip(spell2, 0, CharacterEquipment.SPELL_SLOT);

        ArrayList<Item> usableItems = new ArrayList<>(List.of(usItem1, usItem2, usItem3, usItem4, usItem1, usItem2, usItem3, usItem1, usItem2, usItem3, usItem4, usItem1, usItem2, usItem3, usItem4));

        PlayerParty party = new PlayerParty(new ArrayList<>(List.of(new PlayerCharacter[]{player, player2})), usableItems);
        PlayerInfo.setParty(party);

        FightModule fight = new FightModule(state);
        GameManager.setFight(fight);
        fight.startFight(getEnemies());
        state.refresh();

        mainFrame.add(fightView);

        mainFrame.pack();
        mainFrame.setVisible(true);

    }

    private static ArrayList<EnemyCharacter> getEnemies() {
        EnemyActionFactory factory1 = new EnemyActionFactory(e -> e.getStrength(), 0.25, false, 1);
        EnemyAI ai1 = getEnemyAI(factory1);

        EnemyCharacter enemy = new EnemyCharacter(12, 12, 12, 12, 12, EnemyCategory.Minion, "Skeleton1", new ImageIcon("Texture/CharacterTexture/skeleton.png"), ai1);
        return new ArrayList<>(List.of(enemy));
    }

    private static EnemyAI getEnemyAI(EnemyActionFactory factory1) {
        EnemyActionFactory factory2 = new EnemyActionFactory(e -> e.getCunning(), 0.20, false, 6);

        EnemyAction enemyAction1 = new EnemyAction(ActionTarget.PLAYER_CHARACTER, new Tags[]{Tags.ATTACK}, new ArrayList<>(List.of(new EnemyActionFactory[]{factory1})));
        EnemyAction enemyAction2 = new EnemyAction(ActionTarget.PLAYER_CHARACTER, new Tags[]{}, new ArrayList<>(List.of(new EnemyActionFactory[]{factory2})));
        return new EnemyAI(new ArrayList<>(List.of(new EnemyAction[]{enemyAction1, enemyAction2})));
    }
}
