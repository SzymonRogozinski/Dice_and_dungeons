package GUI.StartGUI.MenuComponents;

import GUI.GUISettings;
import Game.GameManager;
import Game.GameConst;
import Character.PlayerCharacter;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ChoosePanel extends JPanel {
    private static final int MAX_CHARACTER_IN_ROW = 4;
    private final Border labelBorder=BorderFactory.createLineBorder(Color.BLACK,1);
    private Color selectedColor= new Color(0,0,255);
    private final Border selectedLabelBorder=new LineBorder(selectedColor, 1) {
        @Override
        public void paintBorder(final Component c, final Graphics g, final int x, final int y, final int width, final int height) {
            super.lineColor = selectedColor;
            super.paintBorder(c, g, x, y, width, height);
        }
    };
    private final BorderFlashingThread borderFlash;
    public ChoosePanel(Border border) {
        //Set display
        this.setSize(GUISettings.PANEL_SIZE,GUISettings.PANEL_SIZE);
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        this.setBorder(border);

        int i=0;
        int characterInRow = Math.min(GameConst.START_CHARACTER.size(),MAX_CHARACTER_IN_ROW);
        int xSpace=(GUISettings.PANEL_SIZE- GUISettings.CHARACTER_WIDTH * characterInRow)/(characterInRow+1);
        int yOffSet = GUISettings.CHARACTER_HEIGHT /2;
        for(PlayerCharacter p : GameConst.START_CHARACTER){
            CharacterPanel panel = new CharacterPanel(p);
            panel.addMouseListener(new ChooseCharactersMouseListener(panel));
            panel.setLocation((xSpace+ GUISettings.CHARACTER_WIDTH)*(i%MAX_CHARACTER_IN_ROW)+xSpace, yOffSet);
            this.add(panel);
            i++;
            if (i%MAX_CHARACTER_IN_ROW==0){
                yOffSet+=GUISettings.CHARACTER_HEIGHT*3/2;
            }
        }
        //Border changer
        borderFlash=new BorderFlashingThread(this);
        borderFlash.start();
        borderFlash.makeStop();
        //Refresh
        this.revalidate();
        this.repaint();
    }

    private void notifyBorder(){
        synchronized (borderFlash){
            borderFlash.notify();
        }
    }

    private class ChooseCharactersMouseListener implements MouseListener {

        private final CharacterPanel reference;

        ChooseCharactersMouseListener(CharacterPanel reference){
            this.reference=reference;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            notifyBorder();
            if(GameManager.getMenuModule().getParty().size()==3 && !reference.isSelected())
                return;
            else if(reference.isSelected())
                GameManager.getMenuModule().removeFromParty(reference.getPlayerCharacter());
            else
                GameManager.getMenuModule().addToParty(reference.getPlayerCharacter());
            reference.setSelected(!reference.isSelected());
            setBorderFlashing(reference.isSelected());
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {
            GameManager.getMenuModule().setSelectedCharacter(reference.getPlayerCharacter());
        }

        @Override
        public void mouseExited(MouseEvent e) {
            GameManager.getMenuModule().setSelectedCharacter(null);
        }

        private void setBorderFlashing(boolean flashing){
            reference.setBorder(flashing ? selectedLabelBorder : labelBorder);
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
            selectedColor=new Color(0,0,colorValue);
            reference.repaint();
        }

        private synchronized void makeStop(){
            isStop=true;
            colorValue=255;
            isRaising=false;
        }
    }

}
