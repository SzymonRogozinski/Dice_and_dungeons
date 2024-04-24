package GUI.FightGUI;

import GUI.GUISettings;
import main.FightModule;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class StatusPanel extends JPanel {

    private FightModule fight;
    private JProgressBar healthBar;
    private JProgressBar manaBar;
    private JLabel characterName;

    public StatusPanel(Border border){
        //Set display
        this.setSize(GUISettings.SMALL_PANEL_SIZE,GUISettings.PANEL_SIZE);
        this.setLayout(new FlowLayout());
        this.setBackground(Color.BLACK);

        //Set health
        JLabel health=new JLabel("Party health");
        health.setForeground(Color.WHITE);
        this.add(health);

        healthBar=new JProgressBar();
        healthBar.setForeground(Color.RED);
        healthBar.setStringPainted(true);
        this.add(healthBar);

        //Set mana
        JLabel mana=new JLabel("Party mana");
        mana.setForeground(Color.WHITE);
        this.add(mana);

        manaBar=new JProgressBar();
        manaBar.setForeground(Color.BLUE);
        manaBar.setStringPainted(true);
        this.add(manaBar);

        //Set character
        JLabel character=new JLabel("Character turn:");
        character.setForeground(Color.WHITE);
        this.add(character);

        characterName=new JLabel();
        characterName.setForeground(Color.WHITE);
        this.add(characterName);
    }

    public void setFight(FightModule fight){
        this.fight=fight;
        healthBar.setMaximum(fight.getParty().getMaxHealth());
        manaBar.setMaximum(fight.getParty().getMaxMana());
        refresh();
    }

    public void refresh(){
        if(fight==null)
            return;
        healthBar.setValue(fight.getParty().getCurrentHealth());
        healthBar.setString(fight.getParty().getCurrentHealth()+"/"+fight.getParty().getMaxHealth());
        manaBar.setValue(fight.getParty().getCurrentMana());
        manaBar.setString(fight.getParty().getCurrentMana()+"/"+fight.getParty().getMaxMana());
        characterName.setText(fight.getCharacter().getName());
        this.revalidate();
        this.repaint();
    }
}
