package GUI.Shared.Components;

import GUI.Components.GameButton;
import GUI.GUISettings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ChangePanel extends JPanel {

    private final FlowLayout layout;
    private final GameButton next,prev;

    public ChangePanel(String nextButtonText, String prevButtonText, ActionListener nextButtonAction, ActionListener prevButtonAction) {
        this.setPreferredSize(new Dimension(GUISettings.SMALL_PANEL_SIZE - 20, GUISettings.PANEL_SIZE / 4));
        layout = new FlowLayout(FlowLayout.CENTER);
        layout.setVgap(1);
        this.setLayout(layout);
        this.setBackground(Color.BLACK);

        next = new GameButton(nextButtonText,
                GUISettings.SMALL_PANEL_SIZE - 20,
                (int) (GUISettings.PANEL_SIZE * 0.075),
                nextButtonAction
        );
        next.setMargin(new Insets(0, 0, 0, 0));

        prev = new GameButton(prevButtonText,
                GUISettings.SMALL_PANEL_SIZE - 20,
                (int) (GUISettings.PANEL_SIZE * 0.075),
                prevButtonAction
        );
        prev.setMargin(new Insets(0, 0, 0, 0));

        this.add(next);
        this.add(prev);
    }

    public void resize(){
        this.setPreferredSize(new Dimension(GUISettings.getResizedValue(GUISettings.SMALL_PANEL_SIZE - 20), GUISettings.getResizedValue(GUISettings.PANEL_SIZE / 4)));

        layout.setVgap(GUISettings.getResizedValue(1));

        next.resize();
        prev.resize();
    }
}
