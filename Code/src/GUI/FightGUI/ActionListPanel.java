package GUI.FightGUI;

import Character.PlayerCharacter;
import Equipment.Items.ActionItem;
import Equipment.Items.SpellItem;
import Equipment.Items.UsableItem;
import GUI.Compents.DimensionlessGameLabel;
import GUI.Compents.GameButton;
import GUI.GUISettings;
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
    private final static int buttonHGap = GUISettings.PANEL_SIZE / 8 - buttonWidth / 2;
    private final static int backpackButtonHGap = buttonHGap / 2;
    private final static int buttonVGap = (GUISettings.SMALL_PANEL_SIZE - buttonHeight) / 2;
    private final static int backpackButtonVGap = buttonVGap / 3;
    private final CardLayout layout;
    private final CardPanel fightPanel, magicPanel;
    private final BackpackCardPanel itemPanel;

    public ActionListPanel(Border border) {
        //Set display
        this.setSize(GUISettings.PANEL_SIZE, GUISettings.SMALL_PANEL_SIZE);
        this.layout = new CardLayout();
        this.setLayout(layout);
        this.setBackground(Color.BLACK);

        ArrayList<GameButton> actionButtons1 = new ArrayList<>();
        //Setting buttons
        String[] names = {"Attack", "Items", "Spells"};
        for (int i = 0; i < 3; i++)
            actionButtons1.add(new GameButton(names[i], buttonWidth, buttonHeight));

        actionButtons1.get(0).addActionListener(_ -> changePage("Fight"));
        actionButtons1.get(1).addActionListener(_ -> changePage("Items"));
        actionButtons1.get(2).addActionListener(_ -> changePage("Magic"));

        CardPanel startPanel = new CardPanel(border, actionButtons1);
        fightPanel = new CardPanel(border, new ArrayList<>(), "Start");
        magicPanel = new CardPanel(border, new ArrayList<>(), "Start");
        itemPanel = new BackpackCardPanel(border, new ArrayList<>(), "Start");

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
                    _ -> {
                        GameManager.getFight().chosenAction(item.getAction());
                        changePage("Start");
                    }
            );
            button.setMargin(new Insets(0, 0, 0, 0));
            button.addMouseListener(new ButtonItemMouseListener(item.name));
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
        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER);
        for (SpellItem spell : spells) {
            DimensionlessGameLabel spellName = new DimensionlessGameLabel(
                    spell.shortName, SwingConstants.CENTER,
                    GUISettings.BUTTON_FONT,
                    Color.BLACK
            );
            spellName.setBackground(GUISettings.TRANSPARENT);

            DimensionlessGameLabel spellCost = new DimensionlessGameLabel(
                    " " + spell.getAction().getManaCost(), SwingConstants.CENTER,
                    GUISettings.BUTTON_FONT,
                    Color.BLUE
            );
            spellCost.setBackground(GUISettings.TRANSPARENT);

            //Button setup
            GameButton button = new GameButton(
                    "", buttonWidth, buttonHeight,
                    _ -> {
                        if (!(PlayerInfo.getParty().getCurrentMana() < spell.getAction().getManaCost())) {
                            GameManager.getFight().chosenAction(spell.getAction());
                            changePage("Start");
                        }
                    }
            );

            JPanel buttonTextPanel = new JPanel();
            buttonTextPanel.setLayout(flowLayout);
            buttonTextPanel.setBackground(GUISettings.TRANSPARENT);
            buttonTextPanel.add(spellName);
            buttonTextPanel.add(spellCost);

            button.add(buttonTextPanel);
            button.setMargin(new Insets(0, 0, 0, 0));

            buttons.add(button);
        }
        magicPanel.loadNewAction(buttons);
    }

    private void changePage(String pageName) {
        layout.show(this, pageName);
    }

    private class CardPanel extends JPanel {
        final ArrayList<GameButton> buttons;
        private final FlowLayout layout;
        private GameButton goBackButton;

        CardPanel(Border border, ArrayList<GameButton> buttons) {
            this.buttons = buttons;
            //Set display
            this.setSize(GUISettings.PANEL_SIZE, GUISettings.SMALL_PANEL_SIZE);
            layout = new FlowLayout();
            layout.setHgap(buttonHGap);
            layout.setVgap(buttonVGap);
            this.setLayout(layout);
            this.setBorder(border);
            this.setBackground(Color.BLACK);

            for (GameButton button : buttons)
                this.add(button);
        }

        CardPanel(Border border, ArrayList<GameButton> buttons, String goBackName) {
            this.buttons = buttons;
            //Set display
            this.setSize(GUISettings.PANEL_SIZE, GUISettings.SMALL_PANEL_SIZE);
            layout = new FlowLayout();
            layout.setHgap(buttonHGap);
            layout.setVgap(buttonVGap);

            this.setLayout(layout);
            this.setBorder(border);
            this.setBackground(Color.BLACK);

            for (GameButton button : buttons)
                this.add(button);

            goBackButton = new GameButton(
                    "go back", buttonWidth, buttonHeight,
                    _ -> changePage(goBackName)
            );

            this.add(goBackButton);
        }

        void loadNewAction(ArrayList<GameButton> buttons) {
            this.removeAll();

            for (GameButton button : buttons)
                this.add(button);

            this.add(goBackButton);
            this.repaint();
            this.revalidate();
        }

        FlowLayout getFlowLayout() {
            return layout;
        }

        GameButton getGoBackButton() {
            return goBackButton;
        }
    }

    private class BackpackCardPanel extends CardPanel {

        private final int pageSize = 8; //Plus goBackButton
        private final int itemButtonHeight = buttonHeight / 2;
        private final GameButton next, prev;
        private int pageNumber;
        private ArrayList<UsableItem> items;

        BackpackCardPanel(Border border, ArrayList<GameButton> buttons, String goBackName) {
            super(border, buttons, goBackName);
            getGoBackButton().setPreferredSize(new Dimension(buttonWidth, itemButtonHeight));
            getGoBackButton().setMargin(new Insets(0, 0, 0, 0));

            getFlowLayout().setHgap(backpackButtonHGap);
            getFlowLayout().setVgap(backpackButtonVGap / 3);

            next = new GameButton(
                    "Next",
                    buttonWidth, itemButtonHeight,
                    _ -> next()
            );
            next.setMargin(new Insets(0, 0, 0, 0));

            prev = new GameButton(
                    "Prev",
                    buttonWidth, itemButtonHeight,
                    _ -> prev()
            );
            prev.setMargin(new Insets(0, 0, 0, 0));
        }

        void reset(ArrayList<UsableItem> items) {
            this.items = items;
            pageNumber = 0;
            ArrayList<GameButton> buttons = new ArrayList<>();
            //Check if add next button
            int itemCount = items.size() <= pageSize ? items.size() : pageSize - 1;
            //Loading action
            for (int i = 0; i < itemCount; i++) {
                UsableItem item = items.get(i);
                GameButton button = new GameButton(
                        item.shortName,
                        buttonWidth, itemButtonHeight,
                        _ -> {
                            GameManager.getFight().chosenAction(item.getAction());
                            PlayerInfo.getParty().getBackpack().removeFromBackpack(item);
                            changePage("Start");
                        }
                );
                button.setMargin(new Insets(0, 0, 0, 0));
                button.addMouseListener(new ButtonItemMouseListener(item.name));
                buttons.add(button);
            }
            if (items.size() > pageSize)
                buttons.add(next);
            loadNewAction(buttons);
        }

        void next() {
            //check if there is more items on next page
            if (items.size() <= pageSize)
                return;
            int startIndex, itemCount;
            startIndex = pageNumber == 0 ? 0 : ((pageSize - 1) + (pageNumber - 1) * (pageSize - 2));
            if (startIndex + pageSize - 1 >= items.size())
                return;
            pageNumber++;
            ArrayList<GameButton> buttons = new ArrayList<>();
            startIndex += pageNumber == 1 ? (pageSize - 1) : (pageSize - 2);
            itemCount = (startIndex + pageSize - 1) >= items.size() ? (items.size() - startIndex) : (pageSize - 2);

            //Loading action
            for (int i = startIndex; i < startIndex + itemCount; i++) {
                UsableItem item = items.get(i);
                GameButton button = new GameButton(
                        item.shortName,
                        buttonWidth, itemButtonHeight,
                        _ -> {
                            GameManager.getFight().chosenAction(item.getAction());
                            PlayerInfo.getParty().getBackpack().removeFromBackpack(item);
                            changePage("Start");
                        }
                );
                button.setMargin(new Insets(0, 0, 0, 0));
                button.addMouseListener(new ButtonItemMouseListener(item.name));
                buttons.add(button);
            }
            buttons.add(prev);
            if (items.size() > startIndex + itemCount)
                buttons.add(next);
            loadNewAction(buttons);
        }

        void prev() {
            if (pageNumber == 0)
                return;
            pageNumber--;
            int startIndex, itemCount;
            ArrayList<GameButton> buttons = new ArrayList<>();
            if (pageNumber == 0) {
                startIndex = 0;
                itemCount = pageSize - 1;
            } else {
                startIndex = ((pageSize - 1) + (pageNumber - 1) * (pageSize - 2));
                itemCount = (startIndex + pageSize - 1) >= items.size() ? (items.size() - startIndex) : (pageSize - 2);
            }
            //Loading action
            for (int i = startIndex; i < startIndex + itemCount; i++) {
                UsableItem item = items.get(i);
                GameButton button = new GameButton(
                        item.shortName,
                        buttonWidth, itemButtonHeight,
                        _ -> {
                            GameManager.getFight().chosenAction(item.getAction());
                            PlayerInfo.getParty().getBackpack().removeFromBackpack(item);
                            changePage("Start");
                        }
                );
                button.setMargin(new Insets(0, 0, 0, 0));
                button.addMouseListener(new ButtonItemMouseListener(item.name));
                buttons.add(button);
            }
            if (pageNumber > 0)
                buttons.add(prev);
            if ((startIndex + pageSize - 1) < items.size())
                buttons.add(next);
            loadNewAction(buttons);
        }
    }

    private class ButtonItemMouseListener implements MouseListener {

        private final String itemName;

        public ButtonItemMouseListener(String itemName) {
            this.itemName = itemName;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            GameManager.getFight().setCombatInfo("Pointed item: " + itemName);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            GameManager.getFight().setCombatInfo("");
        }
    }
}
