package GUI.MenuGUI.MenuComponents;

import GUI.Components.GameLabel;
import GUI.GUISettings;
import Game.GameManager;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CharacterInfoPanel extends JPanel {

    private static final int LABEL_WIDTH = GUISettings.PANEL_SIZE * 9 / 10;
    private static final int LABEL_HEIGHT = GUISettings.SMALL_PANEL_SIZE / 8;

    private final GameLabel name, stats1, stats2, items, spells, classes;

    public CharacterInfoPanel(Border border) {
        //Set display
        this.setSize(GUISettings.PANEL_SIZE, GUISettings.SMALL_PANEL_SIZE);
        FlowLayout layout = new FlowLayout();
        layout.setHgap(0);
        this.setLayout(layout);
        this.setBackground(Color.BLACK);
        this.setBorder(border);

        name = new GameLabel("", SwingConstants.CENTER,LABEL_WIDTH, LABEL_HEIGHT, Color.WHITE, GUISettings.BIG_FONT);
        name.setBackground(Color.RED);

        stats1 = new GameLabel("",SwingConstants.LEFT,LABEL_WIDTH, LABEL_HEIGHT,Color.WHITE);

        stats2 = new GameLabel("",SwingConstants.LEFT,LABEL_WIDTH, LABEL_HEIGHT,Color.WHITE);

        items = new GameLabel("",SwingConstants.LEFT,LABEL_WIDTH*45/100, LABEL_HEIGHT,Color.WHITE);

        spells = new GameLabel("",SwingConstants.LEFT,LABEL_WIDTH*55/100, LABEL_HEIGHT,Color.WHITE);

        classes = new GameLabel("",SwingConstants.LEFT,LABEL_WIDTH, LABEL_HEIGHT,Color.WHITE);

        this.add(name);
        this.add(stats1);
        this.add(stats2);
        this.add(items);
        this.add(spells);
        this.add(classes);

    }

    public void refresh() {
        if (GameManager.getMenuModule().getSelectedCharacter() != null) {
            name.setText(GameManager.getMenuModule().getSelectedCharacter().getName());

            StringBuilder statsString = new StringBuilder();
            statsString.append("Stats: Strength ").append(GameManager.getMenuModule().getSelectedCharacter().getStrength())
                    .append(" Endurance ").append(GameManager.getMenuModule().getSelectedCharacter().getEndurance())
                    .append(" Intelligence ").append(GameManager.getMenuModule().getSelectedCharacter().getIntelligence());
            stats1.setText(statsString.toString());

            statsString = new StringBuilder();
            statsString.append("Charisma ").append(GameManager.getMenuModule().getSelectedCharacter().getCharisma())
                    .append(" Cunning ").append(GameManager.getMenuModule().getSelectedCharacter().getCunning())
                    .append(" Luck ").append(GameManager.getMenuModule().getSelectedCharacter().getLuck());
            stats2.setText(statsString.toString());

            StringBuilder itemString = new StringBuilder();
            for (var item : GameManager.getMenuModule().getSelectedCharacter().getEquipment().getActionItems()) {
                if (item == null)
                    break;
                itemString.append(item.name).append(", ");
            }
            itemString.insert(0, "Item: ");
            if (itemString.length() != "Item: ".length())
                itemString.setLength(itemString.length() - 2);
            items.setText(itemString.toString());

            StringBuilder spellString = new StringBuilder();
            for (var spell : GameManager.getMenuModule().getSelectedCharacter().getEquipment().getSpellItems()) {
                if (spell == null)
                    break;
                spellString.append(spell.name).append(", ");
            }
            spellString.insert(0, "Spell: ");
            if (spellString.length() != "Spell: ".length())
                spellString.setLength(spellString.length() - 2);
            spells.setText(spellString.toString());

            StringBuilder classString = new StringBuilder();
            for (var cl : GameManager.getMenuModule().getSelectedCharacter().tags)
                classString.append(cl).append(", ");
            classString.insert(0, "Class: ");
            if (classString.length() != "Class: ".length())
                classString.setLength(classString.length() - 2);
            classes.setText(classString.toString());

        } else {
            name.setText("");
            stats1.setText("");
            stats2.setText("");
            items.setText("");
            spells.setText("");
            classes.setText("");
        }
    }

    public void resize(){
        this.setSize(GUISettings.getResizedValue(GUISettings.PANEL_SIZE), GUISettings.getResizedValue(GUISettings.SMALL_PANEL_SIZE));

        name.resize();
        stats1.resize();
        stats2.resize();
        items.resize();
        spells.resize();
        classes.resize();
    }
}
