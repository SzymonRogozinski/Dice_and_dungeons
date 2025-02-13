package GUI.EquipmentGUI;

import Dice.DiceAction.DiceAction;
import Dice.DiceSide;
import Equipment.Items.*;
import GUI.Compents.GameLabel;
import GUI.GUISettings;
import Game.GameManager;
import Game.Tags;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ItemInfoPanel extends JPanel {

    private final CardLayout layout;
    private final DiceItemInfoPanel diceItemInfoPanel;
    private final ArmorInfoPanel armorInfoPanel;
    private final DiceLessItemInfoPanel diceLessItemInfoPanel;

    public ItemInfoPanel(Border border) {
        this.setSize(GUISettings.PANEL_SIZE, GUISettings.SMALL_PANEL_SIZE);
        this.layout = new CardLayout();
        this.setLayout(layout);
        this.setBorder(border);
        this.setBackground(Color.BLACK);

        JPanel emptyPanel = new JPanel();
        emptyPanel.setBackground(Color.BLACK);

        diceItemInfoPanel = new DiceItemInfoPanel();
        armorInfoPanel = new ArmorInfoPanel();
        diceLessItemInfoPanel = new DiceLessItemInfoPanel();

        this.add(emptyPanel, "Empty");
        this.add(diceItemInfoPanel, "Dice");
        this.add(armorInfoPanel, "Armor");
        this.add(diceLessItemInfoPanel, "Diceless");
    }

    public void refresh() {
        Item item = GameManager.getEquipment().getPointedItem();
        if (item == null) {
            layout.show(this, "Empty");
        } else if (item instanceof ArmorItem) {
            layout.show(this, "Armor");
            armorInfoPanel.refresh();
        } else if (item instanceof ActionItem || item instanceof SpellItem) {
            layout.show(this, "Dice");
            diceItemInfoPanel.refresh();
        } else if (item instanceof UsableItem) {
            layout.show(this, "Diceless");
            diceLessItemInfoPanel.refresh();
        }
    }

    private class DiceItemInfoPanel extends JPanel {
        private final static double MANA_COST_TO_ALL_PROPORTION = 0.5;
        private final GameLabel nameLabel, requirements, manaCost, target, attribute;
        private final DiceSidesPanel diceSidesPanel;

        public DiceItemInfoPanel() {
            this.setSize(GUISettings.PANEL_SIZE, GUISettings.SMALL_PANEL_SIZE);
            this.setBackground(Color.BLACK);

            nameLabel = new GameLabel(
                    "", SwingConstants.CENTER,
                    GUISettings.PANEL_SIZE - 10, GUISettings.SMALL_PANEL_SIZE / 7,
                    Color.WHITE, SwingConstants.CENTER, SwingConstants.CENTER
            );
            nameLabel.setFont(GUISettings.BIG_FONT);

            diceSidesPanel = new DiceSidesPanel();

            requirements = new GameLabel(
                    "", SwingConstants.LEFT,
                    (int) ((GUISettings.PANEL_SIZE) * MANA_COST_TO_ALL_PROPORTION - 20),
                    GUISettings.SMALL_PANEL_SIZE / 10,
                    Color.WHITE
            );

            target = new GameLabel(
                    "", SwingConstants.LEFT,
                    (int) ((GUISettings.PANEL_SIZE) * MANA_COST_TO_ALL_PROPORTION - 20),
                    GUISettings.SMALL_PANEL_SIZE / 10,
                    Color.WHITE
            );

            attribute = new GameLabel(
                    "", SwingConstants.LEFT,
                    (int) ((GUISettings.PANEL_SIZE) * MANA_COST_TO_ALL_PROPORTION - 20),
                    GUISettings.SMALL_PANEL_SIZE / 10,
                    Color.WHITE
            );

            manaCost = new GameLabel(
                    "", SwingConstants.LEFT,
                    (int) ((GUISettings.PANEL_SIZE) * MANA_COST_TO_ALL_PROPORTION - 20),
                    GUISettings.SMALL_PANEL_SIZE / 10,
                    Color.WHITE
            );

            this.add(nameLabel);
            this.add(diceSidesPanel);
            this.add(requirements);
            this.add(target);
            this.add(attribute);
            this.add(manaCost);
        }

        public void refresh() {
            Item item = GameManager.getEquipment().getPointedItem();

            nameLabel.setText(item.name);

            if (item instanceof ActionItem aItem) {
                diceSidesPanel.setDiceSides(aItem.getAction().getDice().sides());
                manaCost.setText("");
                target.setText("Target: " + aItem.getAction().getTarget().toString());
                attribute.setText("Amplify by: " + aItem.getScaleAttribute());
            } else if (item instanceof SpellItem sItem) {
                diceSidesPanel.setDiceSides(sItem.getAction().getDice().sides());
                manaCost.setText("Mana: " + sItem.getAction().getManaCost());
                target.setText("target: " + sItem.getAction().getTarget().toString());
                attribute.setText("amplify by: " + sItem.getScaleAttribute());
            }

            StringBuilder requirementsBuilder = new StringBuilder("Requirements:");

            Tags[] tags = item.tags;
            for (Tags tag : tags) {
                String s = tag.name().toLowerCase();
                s = s.substring(0, 1).toUpperCase() + s.substring(1);
                requirementsBuilder.append(" ").append(s).append(",");
            }
            if (requirementsBuilder.toString().equals("Requirements:")) {
                requirementsBuilder.append(" None");
            } else {
                requirementsBuilder.deleteCharAt(requirementsBuilder.toString().length() - 1);
            }

            requirements.setText(requirementsBuilder.toString());
        }
    }

    private class ArmorInfoPanel extends JPanel {

        private final static String[] statsName = new String[]{"Strength", "Endurance", "Intelligence", "Charisma", "Cunning", "Luck"};
        private final GameLabel nameLabel, requirementsLabel, bonusLabel;

        public ArmorInfoPanel() {
            this.setSize(GUISettings.PANEL_SIZE, GUISettings.SMALL_PANEL_SIZE);
            this.setBackground(Color.BLACK);

            nameLabel = new GameLabel(
                    "", SwingConstants.CENTER,
                    GUISettings.PANEL_SIZE - 10, GUISettings.SMALL_PANEL_SIZE / 5,
                    Color.WHITE, SwingConstants.CENTER, SwingConstants.CENTER
            );
            nameLabel.setFont(GUISettings.BIG_FONT);

            requirementsLabel = new GameLabel(
                    "", SwingConstants.LEFT,
                    GUISettings.PANEL_SIZE - 10, GUISettings.SMALL_PANEL_SIZE / 7,
                    Color.WHITE
            );

            bonusLabel = new GameLabel(
                    "", SwingConstants.LEFT,
                    GUISettings.PANEL_SIZE - 10, GUISettings.SMALL_PANEL_SIZE / 7,
                    Color.WHITE
            );

            this.add(nameLabel);
            this.add(requirementsLabel);
            this.add(bonusLabel);
        }

        public void refresh() {
            ArmorItem item = (ArmorItem) GameManager.getEquipment().getPointedItem();

            StringBuilder statsBuilder = new StringBuilder("Statistics:");
            StringBuilder requirementsBuilder = new StringBuilder("Requirements:");

            int[] stats = item.getStats();

            for (int i = 0; i < stats.length; i++) {
                if (stats[i] != 0) {
                    statsBuilder.append(" ").append(statsName[i]).append(" ").append(stats[i]).append(",");
                }
            }
            if (statsBuilder.toString().equals("Statistics:")) {
                statsBuilder.append(" None");
            } else {
                statsBuilder.deleteCharAt(statsBuilder.toString().length() - 1);
            }

            Tags[] tags = item.tags;
            for (Tags tag : tags) {
                String s = tag.name().toLowerCase();
                s = s.substring(0, 1).toUpperCase() + s.substring(1);
                requirementsBuilder.append(" ").append(s).append(",");
            }
            if (requirementsBuilder.toString().equals("Requirements:")) {
                requirementsBuilder.append(" None");
            } else {
                requirementsBuilder.deleteCharAt(requirementsBuilder.toString().length() - 1);
            }

            nameLabel.setText(item.name);
            requirementsLabel.setText(statsBuilder.toString());
            bonusLabel.setText(requirementsBuilder.toString());
        }
    }

    private class DiceLessItemInfoPanel extends JPanel {

        private final GameLabel nameLabel, effectLabel, quantityLabel, targetLabel;

        public DiceLessItemInfoPanel() {
            this.setSize(GUISettings.PANEL_SIZE, GUISettings.SMALL_PANEL_SIZE);
            this.setBackground(Color.BLACK);
            this.setLayout(new FlowLayout(FlowLayout.CENTER));

            nameLabel = new GameLabel(
                    "", SwingConstants.CENTER,
                    GUISettings.PANEL_SIZE - 10, GUISettings.SMALL_PANEL_SIZE / 5,
                    Color.WHITE
            );
            nameLabel.setFont(GUISettings.BIG_FONT);

            effectLabel = new GameLabel(
                    "", SwingConstants.LEFT,
                    GUISettings.PANEL_SIZE - 10, GUISettings.SMALL_PANEL_SIZE / 7,
                    Color.WHITE
            );

            targetLabel = new GameLabel(
                    "", SwingConstants.LEFT,
                    GUISettings.PANEL_SIZE - 10, GUISettings.SMALL_PANEL_SIZE / 7,
                    Color.WHITE
            );

            quantityLabel = new GameLabel(
                    "", SwingConstants.LEFT,
                    GUISettings.PANEL_SIZE - 10, GUISettings.SMALL_PANEL_SIZE / 7,
                    Color.WHITE
            );

            this.add(nameLabel);
            this.add(effectLabel);
            this.add(targetLabel);
            this.add(quantityLabel);
        }

        void refresh() {
            UsableItem item = (UsableItem) GameManager.getEquipment().getPointedItem();
            targetLabel.setText("Target: " + item.getAction().getTarget().toString());
            StringBuilder builder = new StringBuilder("Effects:");

            var x = item.getAction().getActionFactories();

            for (DiceAction action : x) {
                builder.append(" ").append(action.getIdentification()).append(" ").append(action.getValue()).append(",");
            }
            if (builder.toString().equals("Effects:")) {
                builder.append(" None");
            } else {
                builder.deleteCharAt(builder.toString().length() - 1);
            }

            nameLabel.setText(item.name);
            effectLabel.setText(builder.toString());
            quantityLabel.setText("Uses: " + item.getNumberOfItems());
        }
    }

    private class DiceSidesPanel extends JPanel {

        private final JLabel[] diceSides;

        public DiceSidesPanel() {
            int diceSize = Math.min((GUISettings.PANEL_SIZE - 10) / 6, GUISettings.SMALL_PANEL_SIZE / 2);
            this.setPreferredSize(new Dimension(diceSize * 6 + 6, diceSize));
            this.setBackground(Color.BLACK);
            FlowLayout diceLayout = new FlowLayout(FlowLayout.CENTER);
            diceLayout.setHgap(1);
            this.setLayout(diceLayout);

            this.diceSides = new JLabel[6];

            for (int i = 0; i < 6; i++) {
                diceSides[i] = new JLabel();
                diceSides[i].setPreferredSize(new Dimension(diceSize, diceSize));
                this.add(diceSides[i]);
            }

        }

        void setDiceSides(DiceSide[] sides) {
            for (int i = 0; i < 6; i++)
                diceSides[i].setIcon(sides[i].getIcon());
        }
    }

}
