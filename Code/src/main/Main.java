package main;

import GUI.MainFrame;
import GUI.MainPanel;

import javax.swing.*;

public class Main {
    private static final JFrame mainFrame=new MainFrame();
    private static MainPanel mainPanel;

    public static void main(String[] args) {
        mainPanel =new MainPanel();
        FightModule fight = new FightModule(mainPanel);

        mainFrame.add(mainPanel);

        mainFrame.pack();
        mainFrame.setVisible(true);

    }
}
