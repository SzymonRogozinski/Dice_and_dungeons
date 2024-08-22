package GUI.FightGUI;

import GUI.GUISettings;
import Game.GameCollection;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class StatusPanel extends JPanel {

    private JProgressBar healthBar;
    private JProgressBar manaBar;
    private JLabel characterName,statusInfo,nextMoveInfo,combatLog;

    private JPanel combatLogPanel, nextMovePanel;
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
        nextMovePanel = new JPanel();
        nextMovePanel.setPreferredSize(new Dimension(GUISettings.SMALL_PANEL_SIZE-4,GUISettings.PANEL_SIZE/5));
        nextMovePanel.setLayout(new BoxLayout(nextMovePanel , BoxLayout.X_AXIS));
        nextMovePanel.setBackground(Color.BLACK);

        nextMoveInfo=new JLabel();
        nextMoveInfo.setForeground(Color.WHITE);
        nextMoveInfo.setVerticalAlignment(SwingConstants.TOP);
        nextMoveInfo.setVerticalTextPosition(SwingConstants.TOP);
        nextMovePanel.add(nextMoveInfo);
        this.add(nextMovePanel);

        //Set combat log

        combatLogText = "";

        combatLogPanel = new JPanel();
        combatLogPanel.setPreferredSize(new Dimension(GUISettings.SMALL_PANEL_SIZE-4,GUISettings.PANEL_SIZE/3));
        combatLogPanel.setLayout(new BoxLayout(combatLogPanel, BoxLayout.X_AXIS));
        combatLogPanel.setBackground(Color.BLACK);

        combatLog=new JLabel();
        combatLog.setForeground(Color.WHITE);
        combatLog.setVerticalAlignment(SwingConstants.TOP);
        combatLog.setVerticalTextPosition(SwingConstants.TOP);
        combatLogPanel.add(combatLog);
        this.add(combatLogPanel);
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
        nextMoveInfo.setText("<html>"+info+"</html>");
        this.revalidate();
        this.repaint();
    }

    public void hideNextMove(){
        nextMoveInfo.setText("");
        this.revalidate();
        this.repaint();
    }

    public void refreshCombatLog(){
        String combatText = GameCollection.getFight().getCombatLogInfo();
        if(combatLogText.equals(combatText)) {
            combatLogText = "";
        }else {
            combatLogText = combatText;
        }
        combatLog.setText("<html>"+combatLogText+"</html>");
        this.revalidate();
        this.repaint();
    }

    public void refresh(){
        healthBar.setMaximum(GameCollection.getParty().getMaxHealth());
        manaBar.setMaximum(GameCollection.getParty().getMaxMana());
        healthBar.setValue(GameCollection.getParty().getCurrentHealth());
        String healthString = GameCollection.getParty().getCurrentHealth()+"/"+GameCollection.getParty().getMaxHealth();
        if(GameCollection.getParty().getShield()>0)
            healthString+=" +"+GameCollection.getParty().getShield();
        healthBar.setString(healthString);
        manaBar.setValue(GameCollection.getParty().getCurrentMana());
        manaBar.setString(GameCollection.getParty().getCurrentMana()+"/"+GameCollection.getParty().getMaxMana());
        characterName.setText(GameCollection.getFight().getCharacter().getName());
        this.revalidate();
        this.repaint();
    }
}
