package GUI.FightGUI;

import GUI.GUISettings;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class FightPanel extends JPanel {

    private final static int labelHeight=GUISettings.PANEL_SIZE/4;
    private final static int labelWidth=GUISettings.PANEL_SIZE/6;
    private final Border labelBorder=BorderFactory.createLineBorder(Color.WHITE,1);
    private final Border selectedLabelBorder=BorderFactory.createLineBorder(Color.RED,1);
    private final ArrayList<JLabel> enemyLabelList;
    private int selectedEnemy;

    public FightPanel(Border border){
        //Set display
        this.setBounds(0,0, GUISettings.PANEL_SIZE,GUISettings.PANEL_SIZE);
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        this.setBorder(border);

        selectedEnemy=-1;
        //Add Enemy
        enemyLabelList=new ArrayList<>();
        for(int i=0;i<3;i++) {
            JLabel enemy = new JLabel();
            enemy.setSize(labelWidth, labelHeight);
            enemy.setBackground(Color.BLACK);
            enemy.setBorder(labelBorder);
            enemy.addMouseListener(new enemyMouseListener(i));
            enemyLabelList.add(enemy);
        }
        setLabels();

        for(JLabel enemy:enemyLabelList){
            this.add(enemy);
        }
    }

    private void setLabels(){
        int yOffSet=labelHeight/2;
        //Max 3!
        int xSpace=(GUISettings.PANEL_SIZE-labelWidth*enemyLabelList.size())/(enemyLabelList.size()+1);
        for(int i=0; i<enemyLabelList.size();i++){
            enemyLabelList.get(i).setLocation((xSpace+labelWidth)*i+xSpace,yOffSet);
        }
    }

    private class enemyMouseListener implements MouseListener{

        private int enemyId;
        enemyMouseListener(int enemyId){
            this.enemyId=enemyId;
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
            for(int i=0; i<enemyLabelList.size();i++){
                enemyLabelList.get(i).setBorder(i==enemyId ? selectedLabelBorder:labelBorder);
            }
            selectedEnemy=enemyId;
        }

        @Override
        public void mouseExited(MouseEvent e) {
            for(int i=0; i<enemyLabelList.size();i++){
                enemyLabelList.get(i).setBorder(labelBorder);
            }
            selectedEnemy=-1;
        }
    }

}
