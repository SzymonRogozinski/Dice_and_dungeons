package GUI.MenuGUI.MenuComponents;

import GUI.GUISettings;
import Game.GameManager;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CharacterInfoPanel extends JPanel {

    private static final int LABEL_WIDTH = GUISettings.PANEL_SIZE*9/10;
    private static final int LABEL_HEIGHT = GUISettings.SMALL_PANEL_SIZE/8;

    private final JLabel name, stats1,stats2, items, spells, classes;

    public CharacterInfoPanel(Border border) {
        //Set display
        this.setSize(GUISettings.PANEL_SIZE,GUISettings.SMALL_PANEL_SIZE);
        FlowLayout layout = new FlowLayout();
        layout.setHgap(0);
        this.setLayout(layout);
        this.setBackground(Color.BLACK);
        this.setBorder(border);

        name = new JLabel("", SwingConstants.CENTER);
        name.setFont(GUISettings.BIG_FONT);
        name.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
        name.setForeground(Color.WHITE);
        name.setBackground(Color.RED);

        stats1 = new JLabel();
        stats1.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
        stats1.setForeground(Color.WHITE);

        stats2 = new JLabel();
        stats2.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
        stats2.setForeground(Color.WHITE);

        items = new JLabel();
        items.setPreferredSize(new Dimension(LABEL_WIDTH/2,LABEL_HEIGHT));
        items.setForeground(Color.WHITE);

        spells = new JLabel();
        spells.setPreferredSize(new Dimension(LABEL_WIDTH/2,LABEL_HEIGHT));
        spells.setForeground(Color.WHITE);

        classes = new JLabel();
        classes.setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
        classes.setForeground(Color.WHITE);

        this.add(name);
        this.add(stats1);
        this.add(stats2);
        this.add(items);
        this.add(spells);
        this.add(classes);

        this.repaint();
        this.revalidate();
    }

    public void refresh(){
        if(GameManager.getMenuModule().getSelectedCharacter()!=null){
            name.setText(GameManager.getMenuModule().getSelectedCharacter().getName());

            StringBuilder statsString = new StringBuilder();
            statsString.append("Stats: Strength ").append(GameManager.getMenuModule().getSelectedCharacter().getStrength())
                    .append(" Endurance ").append(GameManager.getMenuModule().getSelectedCharacter().getStrength())
                    .append(" Intelligence ").append(GameManager.getMenuModule().getSelectedCharacter().getEndurance());
            stats1.setText(statsString.toString());

            statsString = new StringBuilder();
            statsString.append("Charisma ").append(GameManager.getMenuModule().getSelectedCharacter().getCharisma())
                    .append(" Cunning ").append(GameManager.getMenuModule().getSelectedCharacter().getCunning())
                    .append(" Luck ").append(GameManager.getMenuModule().getSelectedCharacter().getLuck());
            stats2.setText(statsString.toString());

            StringBuilder itemString= new StringBuilder();
            for(var item: GameManager.getMenuModule().getSelectedCharacter().getEquipment().getActionItems()) {
                if(item==null)
                    break;
                itemString.append(item.name).append(", ");
            }
            itemString.insert(0,"Item: ");
            if (itemString.length()!="Item: ".length())
                itemString.setLength(itemString.length()-2);
            items.setText(itemString.toString());

            StringBuilder spellString = new StringBuilder("");
            for(var spell: GameManager.getMenuModule().getSelectedCharacter().getEquipment().getSpellItems()) {
                if (spell == null)
                    break;
                spellString.append(spell.name).append(", ");
            }
            spellString.insert(0,"Spell: ");
            if (spellString.length()!="Spell: ".length())
                spellString.setLength(spellString.length()-2);
            spells.setText(spellString.toString());

            StringBuilder classString = new StringBuilder();
            for(var cl: GameManager.getMenuModule().getSelectedCharacter().tags)
                classString.append(cl).append(", ");
            classString.insert(0,"Class: ");
            if (classString.length()!="Class: ".length())
                classString.setLength(classString.length()-2);
            classes.setText(classString.toString());

        }else{
            name.setText("");
            stats1.setText("");
            stats2.setText("");
            items.setText("");
            spells.setText("");
            classes.setText("");
        }
        this.repaint();
        this.revalidate();
    }
}
