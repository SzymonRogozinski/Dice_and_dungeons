package GUI.FightGUI;

import GUI.GUISettings;
import main.FightModule;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class FightPanel extends JPanel {

    private final static int labelHeight=GUISettings.PANEL_SIZE/4;
    private final static int labelWidth=GUISettings.PANEL_SIZE/6;
    private final Border labelBorder=BorderFactory.createLineBorder(Color.WHITE,1);
    private Color selectedColor= new Color(255,0,0);
    private final Border selectedLabelBorder=new LineBorder(selectedColor, 1) {
        @Override
        public void paintBorder(final Component c, final Graphics g, final int x, final int y, final int width, final int height) {
            super.lineColor = selectedColor;
            super.paintBorder(c, g, x, y, width, height);
        }
    };

    private final ArrayList<JLabel> enemyLabelList;
    private final ArrayList<JLabel> playerLabelList;
    private int selectedEnemy;
    private boolean selectableFlag;
    private FightModule fight;
    private final BorderFlashingThread borderFlash;

    public FightPanel(Border border){
        //Set display
        this.setBounds(0,0, GUISettings.PANEL_SIZE,GUISettings.PANEL_SIZE);
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        this.setBorder(border);
        selectedEnemy=-1;
        selectableFlag=false;
        //Add character labels
        enemyLabelList=new ArrayList<>();
        playerLabelList=new ArrayList<>();
        //Border changer
        borderFlash=new BorderFlashingThread(this);
        borderFlash.start();
        borderFlash.makeStop();
    }

    public void setFight(FightModule fight){
        this.fight=fight;
        //Draw enemies
        for(int i=0;i<fight.getEnemyCount();i++) {
            JLabel enemy = new JLabel(resizeIcon(fight.getEnemies().get(i).getImage(),labelWidth, labelHeight));
            enemy.setSize(labelWidth, labelHeight);
            enemy.setBackground(Color.BLACK);
            enemy.setBorder(labelBorder);
            enemy.addMouseListener(new enemyMouseListener(i,true));
            enemyLabelList.add(enemy);
        }
        for(JLabel enemy:enemyLabelList){
            this.add(enemy);
        }
        //Draw player
        for(int i=0;i<fight.getParty().getCharacters().size();i++) {
            JLabel player = new JLabel(resizeIcon(fight.getParty().getCharacters().get(i).getImage(),labelWidth, labelHeight));
            player.setSize(labelWidth, labelHeight);
            player.setBackground(Color.BLACK);
            player.setBorder(labelBorder);
            player.addMouseListener(new enemyMouseListener(i,false));
            playerLabelList.add(player);
        }

        for(JLabel player:playerLabelList){
            this.add(player);
        }
        setLabels();
        //Refresh
        this.repaint();
        this.revalidate();
    }

    public void enemySelectable(boolean selectableFlag){
        this.selectableFlag=selectableFlag;
    }

    private static ImageIcon resizeIcon(ImageIcon image,int width,int height){
        return new ImageIcon(image.getImage().getScaledInstance(width,height,java.awt.Image.SCALE_SMOOTH));
    }

    private void setLabels(){
        int yOffSet=labelHeight/2;
        int playerYOffSet=GUISettings.PANEL_SIZE-3*labelHeight/2;
        //Set enemies labels. Max 3!
        int xSpace=(GUISettings.PANEL_SIZE-labelWidth*enemyLabelList.size())/(enemyLabelList.size()+1);
        for(int i=0; i<enemyLabelList.size();i++){
            enemyLabelList.get(i).setLocation((xSpace+labelWidth)*i+xSpace,yOffSet);
        }
        //Set player labels. Max 3!
        xSpace=(GUISettings.PANEL_SIZE-labelWidth*playerLabelList.size())/(playerLabelList.size()+1);
        for(int i=0; i<playerLabelList.size();i++){
            playerLabelList.get(i).setLocation((xSpace+labelWidth)*i+xSpace,playerYOffSet);
        }
    }

    private void notifyBorder(){
        synchronized (borderFlash){
            borderFlash.notify();
        }
    }

    private class enemyMouseListener implements MouseListener{

        private final int characterId;
        private final boolean isEnemy;
        enemyMouseListener(int characterId,boolean isEnemy){
            this.isEnemy=isEnemy;
            this.characterId = characterId;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if(!selectableFlag)
                return;
            if(isEnemy)
                enemyLabelList.get(characterId).setBorder(labelBorder);
            else
                playerLabelList.get(characterId).setBorder(labelBorder);
            selectedEnemy = -1;
            borderFlash.makeStop();
            fight.performAction();
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {
            if(!selectableFlag)
                return;
            if(isEnemy)
                enemyLabelList.get(characterId).setBorder(selectedLabelBorder);
            else
                playerLabelList.get(characterId).setBorder(selectedLabelBorder);
            notifyBorder();
            selectedEnemy = characterId;
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if(!selectableFlag)
                return;
            if(isEnemy)
                enemyLabelList.get(characterId).setBorder(labelBorder);
            else
                playerLabelList.get(characterId).setBorder(labelBorder);
            borderFlash.makeStop();
            selectedEnemy = -1;
        }
    }

    private class BorderFlashingThread extends Thread{
        private boolean isRaising;
        private int colorValue;
        private final JPanel reference;
        private boolean isStop;

        BorderFlashingThread(JPanel reference){
            isRaising=false;
            isStop=false;
            colorValue=255;
            this.reference=reference;
        }

        @Override
        public void run(){
            while(true){
                try {
                    synchronized (this) {
                        if (isStop) {
                            wait();
                            isStop = false;
                        }
                    }
                    makeRound();
                    sleep(2);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        private synchronized void makeRound(){
            colorValue+=isRaising?1:-1;
            if(colorValue>=255 || colorValue<=0)
                isRaising=!isRaising;
            selectedColor=new Color(colorValue,0,0);
            reference.repaint();
        }

        private synchronized void makeStop(){
            isStop=true;
            colorValue=255;
            isRaising=false;
        }
    }

}
