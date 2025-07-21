package GUI.FightGUI;

import Character.PlayerCharacter;
import Equipment.Items.ActionItem;
import Equipment.Items.SpellItem;
import Equipment.Items.UsableItem;
import GUI.Components.DimensionlessGameLabel;
import GUI.Components.GameButton;
import GUI.FightGUI.Components.*;
import GUI.GUISettings;
import Game.GameActionQueue;
import Game.GameManager;
import Game.PlayerInfo;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class ActionListPanel extends JPanel {

    //Sizes and placement of button
    private final static int buttonWidth = GUISettings.SMALL_PANEL_SIZE * 7 / 10;
    private final static int buttonHeight = GUISettings.SMALL_PANEL_SIZE / 3;
    private final CardLayout layout;
    private final CardPanel fightPanel, magicPanel, startPanel;
    private final BackpackCardPanel itemPanel;

    public ActionListPanel(Border border) {
        //Set display
        this.setSize(GUISettings.PANEL_SIZE, GUISettings.SMALL_PANEL_SIZE);
        this.layout = new CardLayout();
        this.setLayout(layout);
        this.setBackground(Color.BLACK);

        ArrayList<GameButton> actions = new ArrayList<>();
        //Setting buttons
        String[] names = {"Attack", "Items", "Spells"};
        String[] pages = {"Fight", "Items", "Magic"};
        for (int i = 0; i < 3; i++) {
            int finalI = i;
            actions.add(new GameButton(names[i],
                    buttonWidth, buttonHeight,
                    _ -> GameActionQueue.action(()->changePage(pages[finalI]))
                    ));
        }

        startPanel = new CardPanel(border, actions);
        fightPanel = new CardPanel(border, new ArrayList<>(), "Start", this::changePage);
        magicPanel = new CardPanel(border, new ArrayList<>(), "Start", this::changePage);
        itemPanel = new BackpackCardPanel(border, new ArrayList<>(), "Start", this::changePage);

        this.add("Start", startPanel);
        this.add("Items", itemPanel);
        this.add("Fight", fightPanel);
        this.add("Magic", magicPanel);
    }

    public void loadAction() {
        if (!(GameManager.getFight().getCharacter() instanceof PlayerCharacter character))
            throw new RuntimeException("Illegal state, enemy and player character were mixed!");
        //items
        ArrayList<ActionItem> items = character.getEquipment().getNotNullActionItems();
        ArrayList<GameButton> buttons = new ArrayList<>();
        for (ActionItem item : items) {
            GameButton button = new GameButton(
                    item.shortName,
                    buttonWidth, buttonHeight,
                    _ -> GameActionQueue.action(()->{
                        GameManager.getFight().chosenAction(item.getAction());
                        changePage("Start");
                    })
            );
            button.addMouseListener(new ButtonItemMouseListener(item.name));
            button.resize();
            buttons.add(button);
        }
        fightPanel.loadNewAction(buttons);

        //usable items
        ArrayList<UsableItem> usableItems = PlayerInfo.getParty().getBackpack().getUsableItems();
        itemPanel.reset(usableItems);

        //spells
        ArrayList<SpellItem> spells = character.getEquipment().getNotNullSpellItems();
        buttons = new ArrayList<>();
        //Mana
        for (SpellItem spell : spells) {
            SpellButton button = new SpellButton("", buttonWidth, buttonHeight,
                    _ -> GameActionQueue.action(()->{
                        if (!(PlayerInfo.getParty().getCurrentMana() < spell.getAction().getManaCost())) {
                            GameManager.getFight().chosenAction(spell.getAction());
                            changePage("Start");
                        }
                    }), spell);
            button.resize();
            buttons.add(button);
        }
        magicPanel.loadNewAction(buttons);
    }

    public void resize(){
        this.setSize(GUISettings.getResizedValue(GUISettings.PANEL_SIZE), GUISettings.getResizedValue(GUISettings.SMALL_PANEL_SIZE));

        fightPanel.resize();
        magicPanel.resize();
        startPanel.resize();
        itemPanel.resize();
    }

    private void changePage(String pageName) {
        layout.show(this, pageName);
    }

}
