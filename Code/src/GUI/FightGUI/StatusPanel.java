package GUI.FightGUI;

import GUI.GUISettings;
import Game.GameManager;
import Game.PlayerInfo;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class StatusPanel extends JPanel {

    private JProgressBar healthBar;
    private JProgressBar manaBar;
    private JLabel characterName,statusInfo;
    private JTextArea combatLog,nextMoveInfo;
    private String combatLogText;

    public StatusPanel(Border border){
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

        //Set status
        statusInfo=new JLabel();
        statusInfo.setForeground(Color.WHITE);
        this.add(statusInfo);

        //Set next move
        nextMoveInfo=new JTextArea();
        nextMoveInfo.setForeground(Color.WHITE);
        nextMoveInfo.setBackground(Color.BLACK);
        nextMoveInfo.setWrapStyleWord(true);
        nextMoveInfo.setLineWrap(true);
        nextMoveInfo.setEditable(false);
        nextMoveInfo.setPreferredSize(new Dimension(GUISettings.SMALL_PANEL_SIZE-4,GUISettings.PANEL_SIZE/3));

        this.add(nextMoveInfo);

        //Set combat log
        combatLogText = "";

        combatLog=new JTextArea();
        combatLog.setForeground(Color.WHITE);
        combatLog.setBackground(Color.BLACK);
        combatLog.setWrapStyleWord(true);
        combatLog.setLineWrap(true);
        combatLog.setEditable(false);
        combatLog.setPreferredSize(new Dimension(GUISettings.SMALL_PANEL_SIZE-4,GUISettings.PANEL_SIZE/3));
        this.add(combatLog);
    }

    public void showStatusInfo(String info){
        statusInfo.setText(info);
        this.revalidate();
        this.repaint();
    }

    public void hideStatusInfo(){
        statusInfo.setText("");
        this.revalidate();
        this.repaint();
    }

    public void showNextMove(String info){
        nextMoveInfo.setText(info);
        this.revalidate();
        this.repaint();
    }

    public void hideNextMove(){
        nextMoveInfo.setText("");
        this.revalidate();
        this.repaint();
    }

    public void refreshCombatLog(){
        String combatText = GameManager.getFight().getCombatLogInfo();
        if(combatLogText.equals(combatText)) {
            combatLogText = "";
        }else {
            combatLogText = combatText;
        }
        combatLog.setText(combatLogText);
        this.revalidate();
        this.repaint();
    }

    public void refresh(){
        healthBar.setMaximum(PlayerInfo.getParty().getMaxHealth());
        manaBar.setMaximum(PlayerInfo.getParty().getMaxMana());
        healthBar.setValue(PlayerInfo.getParty().getCurrentHealth());
        String healthString = PlayerInfo.getParty().getCurrentHealth()+"/"+ PlayerInfo.getParty().getMaxHealth();
        if(PlayerInfo.getParty().getShield()>0)
            healthString+=" +"+ PlayerInfo.getParty().getShield();
        healthBar.setString(healthString);
        manaBar.setValue(PlayerInfo.getParty().getCurrentMana());
        manaBar.setString(PlayerInfo.getParty().getCurrentMana()+"/"+ PlayerInfo.getParty().getMaxMana());
        characterName.setText(GameManager.getFight().getCharacter().getName());
        this.revalidate();
        this.repaint();
    }
}
