package GUI.DialogGUI;

import GUI.Components.GameLabel;
import GUI.Components.GameTextArea;
import GUI.GUISettings;
import Game.GameManager;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class NPCDialogPanel extends JPanel {

    private final GameLabel title;
    private final GameTextArea dialog;

    public NPCDialogPanel(Border border) {
        this.setSize(GUISettings.PANEL_SIZE, GUISettings.PANEL_SIZE);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setBorder(border);
        this.setBackground(Color.BLACK);

        title = new GameLabel(
                "", SwingConstants.CENTER,
                GUISettings.PANEL_SIZE * 4/5, GUISettings.PANEL_SIZE *2/15,
                Color.WHITE,GUISettings.BIG_FONT
        );

        this.add(title);

        dialog = new GameTextArea(GUISettings.PANEL_SIZE -20, GUISettings.PANEL_SIZE *12/15);
        this.add(dialog);
    }

    public void refresh(){
        title.setText(GameManager.getDialogModule().getPointedNPC().getName());
        dialog.setText(GameManager.getDialogModule().getPointedNPC().getCurrentResponse());
    }

    public void resize(){
        this.setSize(GUISettings.getResizedValue(GUISettings.PANEL_SIZE), GUISettings.getResizedValue(GUISettings.PANEL_SIZE));

        title.resize();
        dialog.resize();
    }
}
