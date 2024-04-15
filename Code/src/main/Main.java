package main;

import GUI.GUIState;
import GUI.MainFrame;
import GUI.MainPanel;

import javax.swing.*;

public class Main {
    private static final JFrame mainFrame=new MainFrame();
    private static MainPanel mainPanel;
    private static GUIState state;

    public static void main(String[] args) {
        //TODO
        //Change place of action panel

        mainPanel =new MainPanel();
        state=new GUIState(mainPanel);
        FightModule fight = new FightModule(mainPanel,state);

        mainFrame.add(mainPanel);

        mainFrame.pack();
        mainFrame.setVisible(true);

    }
}
