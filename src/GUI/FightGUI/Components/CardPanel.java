package GUI.FightGUI.Components;

import GUI.Components.GameButton;
import GUI.GUISettings;
import Game.GameActionQueue;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class CardPanel extends JPanel {

    final static int buttonWidth = GUISettings.SMALL_PANEL_SIZE * 7 / 10;
    final static int buttonHeight = GUISettings.SMALL_PANEL_SIZE / 3;
    final static int buttonHGap = GUISettings.PANEL_SIZE / 8 - buttonWidth / 2;
    final static int buttonVGap = (GUISettings.SMALL_PANEL_SIZE - buttonHeight) / 2;
    private ArrayList<GameButton> buttons;
    private final FlowLayout layout;
    private GameButton goBackButton;

    public CardPanel(Border border, ArrayList<GameButton> buttons) {
        this.buttons = buttons;
        //Set display
        this.setSize(GUISettings.PANEL_SIZE, GUISettings.SMALL_PANEL_SIZE);
        layout = new FlowLayout();
        layout.setHgap(buttonHGap);
        layout.setVgap(buttonVGap);
        this.setLayout(layout);
        this.setBorder(border);
        this.setBackground(Color.BLACK);

        for (GameButton button : buttons)
            this.add(button);
    }

    public CardPanel(Border border, ArrayList<GameButton> buttons, String goBackName, GoBackCallBack goBack) {
        this.buttons = buttons;
        //Set display
        this.setSize(GUISettings.PANEL_SIZE, GUISettings.SMALL_PANEL_SIZE);
        layout = new FlowLayout();
        layout.setHgap(buttonHGap);
        layout.setVgap(buttonVGap);

        this.setLayout(layout);
        this.setBorder(border);
        this.setBackground(Color.BLACK);

        for (GameButton button : buttons)
            this.add(button);

        goBackButton = new GameButton(
                "go back", buttonWidth, buttonHeight,
                _ -> GameActionQueue.action(()->goBack.goBack(goBackName))
        );

        this.add(goBackButton);
    }

    public void loadNewAction(ArrayList<GameButton> buttons) {
        this.removeAll();
        this.buttons=buttons;

        for (GameButton button : buttons)
            this.add(button);

        this.add(goBackButton);
        this.repaint();
        this.revalidate();
    }

    public FlowLayout getFlowLayout() {
        return layout;
    }

    public GameButton getGoBackButton() {
        return goBackButton;
    }

    public void resize(){
        this.setSize(GUISettings.getResizedValue(GUISettings.PANEL_SIZE), GUISettings.getResizedValue(GUISettings.SMALL_PANEL_SIZE));

        layout.setHgap(GUISettings.getResizedValue(buttonHGap));
        layout.setVgap(GUISettings.getResizedValue(buttonVGap));

        for (GameButton button : buttons)
            button.resize();

        if(goBackButton!=null) goBackButton.resize();
    }

}
