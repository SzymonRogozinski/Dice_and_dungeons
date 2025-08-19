package GUI.DialogGUI;

import GUI.Components.GameLabel;
import GUI.GUISettings;
import Game.GameActionQueue;
import Game.GameManager;
import Dialog.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class DialogOptionPanel extends JPanel {

    private final ArrayList<GameLabel> options;

    public DialogOptionPanel(Border border) {
        this.setSize(GUISettings.PANEL_SIZE, GUISettings.SMALL_PANEL_SIZE);
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        this.setLayout(layout);
        this.setBackground(Color.BLACK);
        this.setBorder(border);

        options=new ArrayList<>();
    }

    public void refresh(){
        //Clear
        options.clear();
        this.removeAll();
        //Load new
        int i=0;
        for(DialogLine line:GameManager.getDialogModule().getPointedNPC().getChooseAbleDialogLines()){
            options.add(new GameLabel(line.getLine(),SwingConstants.LEFT,GUISettings.PANEL_SIZE-20,GUISettings.SMALL_PANEL_SIZE/5, Color.WHITE));
            options.getLast().addMouseListener(new DialogMouseListener(i++));
            this.add(options.getLast());
        }

        resize();
    }

    public void resize(){
        this.setSize(GUISettings.getResizedValue(GUISettings.PANEL_SIZE), GUISettings.getResizedValue(GUISettings.SMALL_PANEL_SIZE));

        for(GameLabel label:options)
            label.resize();
    }

    private class DialogMouseListener implements MouseListener {

        final int dialogId;

        DialogMouseListener(int dialogId){
            this.dialogId=dialogId;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            options.get(dialogId).setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            options.get(dialogId).setBorder(null);

        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            GameActionQueue.action(()->{
                GameManager.getDialogModule().getPointedNPC().chooseDialog(dialogId);
                GameManager.getDialogModule().setRefreshDialog(true);
            });
        }

        //Ignored
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}
    }
}
