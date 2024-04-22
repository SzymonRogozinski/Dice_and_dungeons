package GUI.FightGUI;

import GUI.GUISettings;
import main.FightModule;

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
    private boolean selectableFlag;
    private FightModule fight;

    public FightPanel(Border border){
        //Set display
        this.setBounds(0,0, GUISettings.PANEL_SIZE,GUISettings.PANEL_SIZE);
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        this.setBorder(border);
        selectedEnemy=-1;
        selectableFlag=false;
        //Add Enemy
        enemyLabelList=new ArrayList<>();
    }

    private static ImageIcon resizeIcon(ImageIcon image,int width,int height){
        return new ImageIcon(image.getImage().getScaledInstance(width,height,java.awt.Image.SCALE_SMOOTH));
    }

    public void setFight(FightModule fight){
        this.fight=fight;
        for(int i=0;i<fight.getEnemyCount();i++) {
            JLabel enemy = new JLabel(resizeIcon(fight.getEnemies().get(i).getImage(),labelWidth, labelHeight));
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
        this.repaint();
        this.revalidate();
    }

    public void enemySelectable(boolean selectableFlag){
        this.selectableFlag=selectableFlag;
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
            if(selectableFlag) {
                enemyLabelList.get(enemyId).setBorder(labelBorder);
                selectedEnemy = -1;
                fight.performAction();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if(selectableFlag) {
                enemyLabelList.get(enemyId).setBorder(selectedLabelBorder);
                selectedEnemy = enemyId;
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if(selectableFlag) {
                enemyLabelList.get(enemyId).setBorder(labelBorder);
                selectedEnemy = -1;
            }
        }
    }

}
