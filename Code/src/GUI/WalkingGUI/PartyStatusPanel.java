package GUI.WalkingGUI;

import GUI.GUISettings;
import Game.GameManager;
import Game.GameStates;
import Game.PlayerInfo;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class PartyStatusPanel extends JPanel {

    private JProgressBar healthBar, manaBar;
    private JLabel keysLabel;


    public PartyStatusPanel(Border border){
        //Set display
        this.setSize(GUISettings.SMALL_PANEL_SIZE,GUISettings.PANEL_SIZE);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setBackground(Color.BLACK);
        this.setBorder(border);

        //Set health
        JLabel health=new JLabel("Party health");
        health.setForeground(Color.WHITE);
        this.add(health);

        healthBar=new JProgressBar();
        healthBar.setForeground(Color.RED);
        healthBar.setStringPainted(true);
        healthBar.setPreferredSize(new Dimension(GUISettings.SMALL_PANEL_SIZE-6,GUISettings.SMALL_PANEL_SIZE/8));
        this.add(healthBar);

        //Set mana
        JLabel mana=new JLabel("Party mana");
        mana.setForeground(Color.WHITE);
        this.add(mana);

        manaBar=new JProgressBar();
        manaBar.setForeground(Color.BLUE);
        manaBar.setStringPainted(true);
        manaBar.setPreferredSize(new Dimension(GUISettings.SMALL_PANEL_SIZE-6,GUISettings.SMALL_PANEL_SIZE/8));
        this.add(manaBar);

        //Set keys
        keysLabel = new JLabel("Keys: 0");
        keysLabel.setForeground(Color.WHITE);
        this.add(keysLabel);

        //Set equipment buttons
        JButton eqButton = new JButton("Equipment");
        eqButton.setPreferredSize(new Dimension(GUISettings.SMALL_PANEL_SIZE*8/10,GUISettings.SMALL_PANEL_SIZE/5));
        eqButton.addActionListener(e->{
            GameManager.getEquipment().changeViewToEquipment();
            GameManager.changeState(GameStates.EQUIPMENT);
        });
        this.add(eqButton);

        JButton backpackButton = new JButton("Backpack");
        backpackButton.setPreferredSize(new Dimension(GUISettings.SMALL_PANEL_SIZE*8/10,GUISettings.SMALL_PANEL_SIZE/5));
        backpackButton.addActionListener(e->{
            GameManager.getEquipment().changeViewToBackpack();
            GameManager.changeState(GameStates.EQUIPMENT);
        });
        this.add(backpackButton);

    }

    public void refresh(){
        healthBar.setMaximum(PlayerInfo.getParty().getMaxHealth());
        healthBar.setValue(PlayerInfo.getParty().getCurrentHealth());
        healthBar.setString(PlayerInfo.getParty().getCurrentHealth()+"/"+ PlayerInfo.getParty().getMaxHealth());

        manaBar.setMaximum(PlayerInfo.getParty().getMaxMana());
        manaBar.setValue(PlayerInfo.getParty().getCurrentMana());
        manaBar.setString(PlayerInfo.getParty().getCurrentMana()+"/"+ PlayerInfo.getParty().getMaxMana());

        keysLabel.setText("Keys: "+PlayerInfo.getKeyCollected());

        this.revalidate();
        this.repaint();
    }
}
