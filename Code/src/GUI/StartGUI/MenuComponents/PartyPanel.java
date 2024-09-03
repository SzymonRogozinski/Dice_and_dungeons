package GUI.StartGUI.MenuComponents;

import GUI.GUISettings;

import Game.Game;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class PartyPanel extends JPanel {

    private final JLabel header;
    private final JLabel[] characterLabels;
    public PartyPanel(Border border) {
        //Set display
        this.setSize(GUISettings.SMALL_PANEL_SIZE,GUISettings.PANEL_SIZE);
        FlowLayout layout = new FlowLayout();
        layout.setVgap(GUISettings.SMALL_PANEL_SIZE/10);
        this.setLayout(layout);
        this.setBackground(Color.BLACK);
        this.setBorder(border);

        //Set elements
        header = new JLabel("Party", SwingConstants.CENTER);
        header.setPreferredSize(new Dimension(GUISettings.SMALL_PANEL_SIZE,GUISettings.SMALL_PANEL_SIZE/5));
        header.setFont(GUISettings.BIG_FONT);
        header.setForeground(Color.WHITE);
        this.add(header);

        characterLabels=new JLabel[3];
        for(int i=0;i<3;i++){
            characterLabels[i]=new JLabel("-", SwingConstants.CENTER);
            characterLabels[i].setPreferredSize(new Dimension(GUISettings.SMALL_PANEL_SIZE,GUISettings.SMALL_PANEL_SIZE/8));
            characterLabels[i].setForeground(Color.WHITE);
            this.add(characterLabels[i]);
        }

        JButton goBack = new JButton("Go back");
        goBack.setPreferredSize(new Dimension(GUISettings.SMALL_PANEL_SIZE*2/3,GUISettings.SMALL_PANEL_SIZE/5));
        goBack.addActionListener(e->Game.getMenuModule().changeToStart());
        this.add(goBack);
    }

    public void refresh(){
        for(int i=0;i<3;i++){
            try {
                characterLabels[i].setText(Game.getMenuModule().getParty().get(i).getName());
            }catch (IndexOutOfBoundsException e){
                characterLabels[i].setText("-");
            }
        }
        this.repaint();
        this.revalidate();
    }
}
