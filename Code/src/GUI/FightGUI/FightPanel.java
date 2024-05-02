package GUI.FightGUI;

import Fight.ActionTarget;
import GUI.GUISettings;
import Fight.FightModule;
import Character.EnemyCharacter;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class FightPanel extends JPanel {

    private final static int enemyHeight =GUISettings.PANEL_SIZE/4;
    private final static int enemyWidth =GUISettings.PANEL_SIZE/6;
    private final Border labelBorder=BorderFactory.createLineBorder(Color.BLACK,1);
    private Color selectedColor= new Color(255,0,0);
    private final Border selectedLabelBorder=new LineBorder(selectedColor, 1) {
        @Override
        public void paintBorder(final Component c, final Graphics g, final int x, final int y, final int width, final int height) {
            super.lineColor = selectedColor;
            super.paintBorder(c, g, x, y, width, height);
        }
    };

    private final ArrayList<JLabel> enemyLabelList;
    private final ArrayList<EnemyPanel> enemyPanelList;
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
        enemyPanelList=new ArrayList<>();
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
            EnemyPanel enemy = new EnemyPanel(i);
            /*JLabel enemy = new JLabel(resizeIcon(fight.getEnemies().get(i).getImage(), enemyWidth, enemyHeight));
            enemy.setSize(enemyWidth, enemyHeight);
            enemy.setBackground(Color.BLACK);
            enemy.setBorder(labelBorder);
            enemy.addMouseListener(new EnemyMouseListener(i,true));*/
            enemyPanelList.add(enemy);
        }
        for(EnemyPanel enemy:enemyPanelList){
            this.add(enemy);
        }
        //Draw player
        for(int i=0;i<fight.getParty().getCharacters().size();i++) {
            JLabel player = new JLabel(resizeIcon(fight.getParty().getCharacters().get(i).getImage(), enemyWidth, enemyHeight));
            player.setSize(enemyWidth, enemyHeight);
            player.setBackground(Color.BLACK);
            player.setBorder(labelBorder);
            player.addMouseListener(new EnemyMouseListener(i,false));
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

    public void refresh(){
        for(EnemyPanel enemy:enemyPanelList){
            enemy.refresh();
        }
    }

    public void enemySelectable(boolean selectableFlag){
        this.selectableFlag=selectableFlag;
    }

    private static ImageIcon resizeIcon(ImageIcon image,int width,int height){
        return new ImageIcon(image.getImage().getScaledInstance(width,height,java.awt.Image.SCALE_SMOOTH));
    }

    private void setLabels(){
        int yOffSet= enemyHeight /2;
        int playerYOffSet=GUISettings.PANEL_SIZE-3* enemyHeight /2;
        //Set enemies labels. Max 3!
        int xSpace=(GUISettings.PANEL_SIZE- enemyWidth *enemyPanelList.size())/(enemyPanelList.size()+1);
        for(int i=0; i<enemyPanelList.size();i++){
            enemyPanelList.get(i).setLocation((xSpace+ enemyWidth)*i+xSpace,yOffSet);
        }
        //Set player labels. Max 3!
        xSpace=(GUISettings.PANEL_SIZE- enemyWidth *playerLabelList.size())/(playerLabelList.size()+1);
        for(int i=0; i<playerLabelList.size();i++){
            playerLabelList.get(i).setLocation((xSpace+ enemyWidth)*i+xSpace,playerYOffSet);
        }
    }

    private void notifyBorder(){
        synchronized (borderFlash){
            borderFlash.notify();
        }
    }

    private class EnemyPanel extends JPanel{
        JLabel enemyLabel;
        JProgressBar healthBar;
        EnemyCharacter enemy;

        EnemyPanel(int i){
            FlowLayout layout=new FlowLayout();
            layout.setVgap(1);
            this.setLayout(layout);
            enemy=fight.getEnemies().get(i);

            enemyLabel = new JLabel(resizeIcon(enemy.getImage(), enemyWidth-2, (int)(enemyHeight*0.85)-2));
            healthBar=new JProgressBar(0,enemy.getMaxHealth());
            healthBar.setPreferredSize(new Dimension(enemyWidth-2, (int)(enemyHeight*0.15)-1));
            healthBar.setForeground(Color.RED);
            healthBar.setStringPainted(true);

            this.add(enemyLabel);
            this.add(healthBar);

            this.setSize(enemyWidth, enemyHeight);
            this.setBackground(Color.BLACK);
            this.setBorder(labelBorder);
            this.addMouseListener(new EnemyMouseListener(i,true));
            refresh();
        }

        void refresh(){
            if(enemy.getCurrentHealth()==0 && this.isVisible())
                this.setVisible(false);
            else {
                healthBar.setValue(enemy.getCurrentHealth());
                healthBar.setString(enemy.getCurrentHealth() + "/" + enemy.getMaxHealth());
            }
        }

    }

    private class EnemyMouseListener implements MouseListener{

        private final int characterId;
        private final boolean isEnemy;
        EnemyMouseListener(int characterId, boolean isEnemy){
            this.isEnemy=isEnemy;
            this.characterId = characterId;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if(!selectableFlag || !setBorderFlashing(false))
                return;
            borderFlash.makeStop();
            fight.targetSelected(selectedEnemy);
            selectedEnemy = -1;
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {
            if(!selectableFlag)
                return;
            setBorderFlashing(true);
            notifyBorder();
            selectedEnemy = characterId;
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if(!selectableFlag)
                return;
            setBorderFlashing(false);
            borderFlash.makeStop();
            selectedEnemy = -1;
        }

        private boolean setBorderFlashing(boolean flashing){
            if(isEnemy && fight.getTargetType() == ActionTarget.ENEMY_CHARACTER) {
                enemyPanelList.get(characterId).setBorder(flashing ? selectedLabelBorder : labelBorder);
                return true;
            }else if (!isEnemy && fight.getTargetType() == ActionTarget.PLAYER_CHARACTER) {
                playerLabelList.get(characterId).setBorder(flashing ? selectedLabelBorder : labelBorder);
                return true;
            }else if (isEnemy && fight.getTargetType() == ActionTarget.ALL_ENEMIES){
                for(EnemyPanel label:enemyPanelList)
                    label.setBorder(flashing?selectedLabelBorder:labelBorder);
                return true;
            }else if (!isEnemy && fight.getTargetType() == ActionTarget.PLAYER_PARTY){
                for(JLabel label:playerLabelList )
                    label.setBorder(flashing?selectedLabelBorder:labelBorder);
                return true;
            }
            return false;
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
