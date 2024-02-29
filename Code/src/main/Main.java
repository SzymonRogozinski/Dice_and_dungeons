package main;

import Dice.Dice;
import Dice.DiceAction.DiceAction;
import Dice.DicePool;
import Dice.DiceAction.DamageAction;
import Dice.DiceAction.NullAction;
import Dice.DiceSide;
import GUI.ControlPanel;
import GUI.DicePanel;
import GUI.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static final int heightAndWidth=600;
    private static final JFrame mainFrame=new MainFrame();
    private static final JPanel basePanel=new JPanel();
    private static final DicePanel dicePanel=new DicePanel(heightAndWidth,heightAndWidth/3);
    private static final ControlPanel controlPanel=new ControlPanel(heightAndWidth,heightAndWidth/3);
    private static final DiceMaster master=new DiceMaster(dicePanel,controlPanel);


    public static void main(String[] args) {
        basePanel.setSize(heightAndWidth,heightAndWidth*2/3);
        basePanel.setLayout(new GridLayout(2,1));

        dicePanel.setMaster(master);
        controlPanel.setMaster(master);

        basePanel.add(controlPanel);
        basePanel.add(dicePanel);
        mainFrame.add(basePanel);

        mainFrame.pack();
        mainFrame.setVisible(true);

    }
}
